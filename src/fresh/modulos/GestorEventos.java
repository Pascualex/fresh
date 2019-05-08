package fresh.modulos;

import fresh.sistema.Sistema;
import fresh.datos.BaseDeDatos;
import fresh.datos.tipos.*;
import fresh.sistema.Configuracion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * Gestiona los eventos del sistema, realizando operaciones en la base de datos.
 */
public class GestorEventos implements Runnable, Serializable {
    private static final long serialVersionUID = 2;
    
    private String ruta;
    private GregorianCalendar ultimoDiaComprobado;
    private SortedSet<ParCancionFecha> cancionesAEliminar = new TreeSet<>();
    private SortedSet<ParUsuarioFecha> usuariosADesbloquear = new TreeSet<>();
    private BaseDeDatos baseDeDatos;
    private Configuracion configuracion;
    static final long msDia = 86400000;
    private boolean parar;

    private GestorEventos(BaseDeDatos baseDeDatos, Configuracion configuracion, String ruta) {
        this.baseDeDatos = baseDeDatos;
        this.configuracion = configuracion;
        this.ruta = ruta;
        ultimoDiaComprobado = new GregorianCalendar();
        parar = false;
    }

    /**
     * Programa la eliminación de una canción.
     * @param cancion Canción cuya eliminación se va a programar
     */
    public void programarEliminacionCancion(Cancion cancion) {
        cancionesAEliminar.add(new ParCancionFecha(cancion));
    }

    /**
     * Cancela la eliminación de una canción..
     * @param cancion Canción cuya eliminación quiere ser cancelada
     */
    public void cancelarEliminacionCancion(Cancion cancion) {
        Set<ParCancionFecha> canciones = new HashSet<>();
        for (ParCancionFecha p : cancionesAEliminar) {
            if (p.cancion == cancion) {
                canciones.add(p);
            }
        }
        for (ParCancionFecha par : canciones) {
            cancionesAEliminar.remove(par);
        }
    }
    
    /**
     * Programa el desbloqueo de un usuario.
     * @param usuario Usuario a desbloquear
     */
    public void programarDesbloqueoUsuario(Usuario usuario) {
        usuariosADesbloquear.add(new ParUsuarioFecha(usuario));
    }

    /**
     * Inicia el proceso de comprobar las canciones a eliminar y degradar a los
     * usuarios a usuarios normales quitándoles el premium a inicio de mes. No
     * debería ser ejecutada directamente, sino en su propio hilo.
     */
    public void run() {
        try {
            while (!parar) {
                GregorianCalendar fecha_actual = new GregorianCalendar();
                
                if (fecha_actual.getTimeInMillis()-ultimoDiaComprobado.getTimeInMillis() < msDia) {
                    if (!Sistema.modoPrueba) TimeUnit.SECONDS.sleep(5*60+(fecha_actual.getTimeInMillis()-msDia)/1000);
                    if (parar) break;
                }
                
                fecha_actual = new GregorianCalendar();
                
                if (fecha_actual.get(GregorianCalendar.MONTH) != ultimoDiaComprobado.get(GregorianCalendar.MONTH) || Sistema.modoPrueba) {                        
                    baseDeDatos.eliminarPremiumUsuarios();
                    
                    for (Usuario usuario : baseDeDatos.obtenerUsuarios()) {

                        int reproduccionesUsuario = 0;
                        for (Cancion c : usuario.getCanciones()) {
                            reproduccionesUsuario += c.getReproduccionesMensuales();
                        }

                        if (reproduccionesUsuario >= configuracion.getMinReproduccionesPremium()) {
                            usuario.setPremium(true);
                            usuario.anadirNotificacion(new Notificacion(TipoNotificacion.PREMIUM_GRATUITO));
                        }
                        usuario.setReproduccionesMensuales(0);
                    }
                }

                Set<ParCancionFecha> canciones = new HashSet<>();
                for (ParCancionFecha par : cancionesAEliminar) {
                    if (fecha_actual.getTimeInMillis()-par.fecha.getTimeInMillis() > 3*msDia || Sistema.modoPrueba) {
                        baseDeDatos.eliminarCancion(par.cancion);
                        NotificacionCancion notificacion = new NotificacionCancion(TipoNotificacion.CANCION_ELIMINADA, par.cancion);
                        par.cancion.getAutor().anadirNotificacion(notificacion);
                        canciones.add(par);
                    } else break;
                }
                for (ParCancionFecha par : canciones) {
                    cancionesAEliminar.remove(par);
                }

                Set<ParUsuarioFecha> usuarios = new HashSet<>();
                for (ParUsuarioFecha par : usuariosADesbloquear) {
                    if (fecha_actual.getTimeInMillis()-par.fecha.getTimeInMillis() > 30*msDia || Sistema.modoPrueba) {
                        par.usuario.setBloqueado(false);
                        usuarios.add(par);
                    } else break;
                }
                for (ParUsuarioFecha par : usuarios) {
                    usuariosADesbloquear.remove(par);
                }

                ultimoDiaComprobado = new GregorianCalendar();
            }
        } catch (InterruptedException e) {
            guardarInformacion();
        } 
    }

    /**
     * Inicializa un gestor de eventos dada la ruta de la que debería cargar los 
     * datos y la base de datos asociada al gestor. Si no puede cargar los datos 
     * en la ruta especificada devuelve un nuevo gestor de eventos.
     * @param baseDeDatos Base de datos asociada con el gestor
     * @param configuracion Configuración del sistema
     * @param ruta Ruta del gestor de eventos
     * @return Gestor de eventos con la información cargada.
     */
    public static GestorEventos cargarGestorEventos(BaseDeDatos baseDeDatos, Configuracion configuracion, String ruta) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(ruta))) {
            GestorEventos gestorEventos = (GestorEventos) stream.readObject();
            stream.close();
            return gestorEventos;
        } catch (FileNotFoundException e) {
            return new GestorEventos(baseDeDatos, configuracion, ruta);
        } catch (IOException e) {
        	return new GestorEventos(baseDeDatos, configuracion, ruta);
        } catch (ClassNotFoundException e) {
        	return new GestorEventos(baseDeDatos, configuracion, ruta);
        }
    }

    /**
     * Guarda la información relacionada con el gestor de eventos en la ruta 
     * especificada para que pueda ser más tarde cargada.
     */
    public void guardarInformacion() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            stream.writeObject(this);
            stream.close();
        } catch (IOException e) {
            return;
        }
    }

    /**
     * Finaliza la ejecución del hilo.
     */
    public void finalizar() {
    	parar = true;
    }
    
    private class ParCancionFecha implements Serializable, Comparable<ParCancionFecha> {
        private static final long serialVersionUID = Sistema.numeroVersion;

        Cancion cancion;
        GregorianCalendar fecha;
    
        public ParCancionFecha(Cancion cancion) {
            this.cancion = cancion;
            fecha = new GregorianCalendar();
        }
    
        @Override
        public int compareTo(ParCancionFecha par) {
            return fecha.compareTo(par.fecha);
        }
    }

    private class ParUsuarioFecha implements Serializable, Comparable<ParUsuarioFecha> {
    private static final long serialVersionUID = Sistema.numeroVersion;

        Usuario usuario;
        GregorianCalendar fecha;
    
        public ParUsuarioFecha(Usuario usuario) {
            this.usuario = usuario;
            fecha = new GregorianCalendar();
        }
    
        @Override
        public int compareTo(ParUsuarioFecha par) {
            return fecha.compareTo(par.fecha);
        }
    }
}