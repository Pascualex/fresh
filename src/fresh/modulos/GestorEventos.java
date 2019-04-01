package fresh.modulos;

import fresh.datos.BaseDeDatos;
import fresh.datos.tipos.*;
import fresh.sistema.Configuracion;
import fresh.Status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * Gestiona los eventos del sistema, realizando operaciones en la base de datos.
 */
public class GestorEventos implements Runnable, Serializable {
    private String ruta;
    private GregorianCalendar ultimoDiaComprobado;
    private SortedSet<ParCancionFecha> cancionesAEliminar = new TreeSet<>();
    private SortedSet<ParUsuarioFecha> usuariosADesbloquear = new TreeSet<>();
    private BaseDeDatos baseDeDatos;
    private Configuracion configuracion;
    static final long msDia = 86400000;

    private GestorEventos(BaseDeDatos baseDeDatos, Configuracion configuracion, String ruta) {
        this.baseDeDatos = baseDeDatos;
        this.configuracion = configuracion;
        this.ruta = ruta;
        ultimoDiaComprobado = new GregorianCalendar();
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
        for (ParCancionFecha p : cancionesAEliminar) {
            if (p.cancion == cancion) {
                cancionesAEliminar.remove(p);
            }
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
            while (true) {
                GregorianCalendar fecha_actual = new GregorianCalendar();
                
                if (fecha_actual.getTimeInMillis()-ultimoDiaComprobado.getTimeInMillis() < msDia) {
                    TimeUnit.SECONDS.sleep(5*60+(fecha_actual.getTimeInMillis()-msDia)/1000);
                }
                
                fecha_actual = new GregorianCalendar();
                
                if (fecha_actual.get(GregorianCalendar.MONTH) != ultimoDiaComprobado.get(GregorianCalendar.MONTH)) {                        
                    baseDeDatos.eliminarPremiumUsuarios();
                    
                    for (Usuario usuario : baseDeDatos.obtenerUsuarios()) {
                        if (usuario.getReproduccionesMensuales() >= configuracion.getMinReproduccionesPremium()) {
                            usuario.setPremium(true);
                            usuario.anadirNotificacion(new Notificacion(TipoNotificacion.PREMIUM_GRATUITO));
                        }
                        usuario.setReproduccionesMensuales(0);
                    }
                }

                for (ParCancionFecha par : cancionesAEliminar) {
                    if (fecha_actual.getTimeInMillis()-par.fecha.getTimeInMillis() > 3*msDia) {
                        baseDeDatos.eliminarCancion(par.cancion);
                        NotificacionCancion notificacion = new NotificacionCancion(TipoNotificacion.CANCION_ELIMINADA, par.cancion);
                        par.cancion.getAutor().anadirNotificacion(notificacion);
                        cancionesAEliminar.remove(par);
                    } else break;
                }

                for (ParUsuarioFecha par : usuariosADesbloquear) {
                    if (fecha_actual.getTimeInMillis()-par.fecha.getTimeInMillis() > 30*msDia) {
                        par.usuario.setBloqueado(false);
                        usuariosADesbloquear.remove(par);
                    } else break;
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
            return null;
        } catch (ClassNotFoundException e) {
            return null;
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

    private class ParCancionFecha implements Serializable, Comparable<ParCancionFecha> {
        Cancion cancion;
        GregorianCalendar fecha;
    
        public ParCancionFecha(Cancion cancion) {
            this.cancion = cancion;
            fecha = new GregorianCalendar();
        }
    
        public ParCancionFecha(Cancion cancion, GregorianCalendar fecha) {
            this.cancion = cancion;
            this.fecha = fecha;
        }
    
        @Override
        public int compareTo(ParCancionFecha par) {
            return fecha.compareTo(par.fecha);
        }
    }

    private class ParUsuarioFecha implements Serializable, Comparable<ParUsuarioFecha> {
        Usuario usuario;
        GregorianCalendar fecha;
    
        public ParUsuarioFecha(Usuario usuario) {
            this.usuario = usuario;
            fecha = new GregorianCalendar();
        }
    
        public ParUsuarioFecha(Usuario usuario, GregorianCalendar fecha) {
            this.usuario = usuario;
            this.fecha = fecha;
        }
    
        @Override
        public int compareTo(ParUsuarioFecha par) {
            return fecha.compareTo(par.fecha);
        }
    }
}