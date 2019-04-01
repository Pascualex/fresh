package fresh.sistema;

import fresh.datos.BaseDeDatos;
import fresh.datos.tipos.*;
import fresh.modulos.*;
import fresh.Status;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.List;
import java.util.Set;
import java.lang.Thread;

/**
 * <p>Esta es la clase principal de la funcionalidad de la aplicación. Permite
 *    interactuar con el sistema y llevar a cabo todas las acciones
 *    necesarias para su uso.</p>
 */
public class Sistema {
    public static final long numeroVersion = 1;

    private static final String rutaBaseDeDatos = "./baseDedatos/baseDeDatos.bd";
    private static final String rutaGestorEventos = "./gestorEventos/gestorEventos";
    private static final String rutaConfiguracion = "./configuracion/configuracion.txt";
    private static final String rutaFicherosMP3 = "./canciones/";

    private final Thread hiloGestorEventos;
    private final Thread hiloModuloMP3;

    private Configuracion configuracion;
    private BaseDeDatos baseDeDatos;
    private GestorEventos gestorEventos;
    private ModuloMP3 moduloMP3;
    private ModoEjecucion modoEjecucion;
    private Usuario usuarioActual;

    /**
     * Crea el sistema. Sus características vienen definidas en constantes y en
     * el fichero de configuración, por lo que no recibe argumentos.
     */
    public Sistema() {
        configuracion = new Configuracion(rutaConfiguracion);
        baseDeDatos = BaseDeDatos.cargarBaseDeDatos(rutaBaseDeDatos);        
        gestorEventos = GestorEventos.cargarGestorEventos(baseDeDatos, configuracion, rutaGestorEventos);
        hiloGestorEventos = new Thread(gestorEventos);
        hiloGestorEventos.start();
        moduloMP3 = new ModuloMP3(rutaFicherosMP3, configuracion);
        hiloModuloMP3 = new Thread(moduloMP3);
        hiloModuloMP3.start();
        modoEjecucion = ModoEjecucion.DESCONECTADO;
    }

    /**
     * Recibe los datos de inicio de sesión y comprueba si coinciden con algún
     * credencial del sistema.
     * @param nombre Nombre del usuario
     * @param contrasena Contraseña del usuario
     * @return "OPERACION_INACCESIBLE" si el sistema tiene iniciada una sesión.
     * "NOMBRE_INVALIDO" si el nombre indicado no pertenece a ningún usuario
     * registrado ni al administrador.
     * "CONTRASENA_INVALIDA" si se encuentra una coincidencia en el nombre, pero
     * la contraseña es incorrecta.
     * "USUARIO_BLOQUEADO" si es un usuario registrado y está bloqueado.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status iniciarSesion(String nombre, String contrasena) {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        if (Objects.equals(nombre, configuracion.getNombreAdministrador())) {
            if (!Objects.equals(contrasena, configuracion.getContrasenaAdministrador())) return Status.CONTRASENA_INVALIDA;

            modoEjecucion = ModoEjecucion.ADMINISTRADOR;

            return Status.OK;
        }

        Usuario usuario = baseDeDatos.buscarUsuario(nombre);
        if (usuario == null) return Status.NOMBRE_INVALIDO;
        if (usuario.getBloqueado()) return Status.USUARIO_BLOQUEADO;
        if (!Objects.equals(contrasena, usuario.getContrasena())) return Status.CONTRASENA_INVALIDA;

        modoEjecucion = ModoEjecucion.REGISTRADO;
        usuarioActual = usuario;
        moduloMP3.nuevaSesionRegistrado(usuarioActual);
        
        return Status.OK;
    }

    /**
     * Inicia sesión en el sistema en modo anónimo.
     * @return "OPERACION_INACCESIBLE" si el sistema tiene iniciada una sesión.
     * "OK" en caso contrario.
     */
    public Status iniciarSesionAnonimo() {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        modoEjecucion = ModoEjecucion.ANONIMO;
        moduloMP3.nuevaSesionAnonimo();

        return Status.OK;
    }

    /**
     * Recibe los datos necesarios para registrarse, comprueba su validez y
     * añade al usuario a la base de datos.
     * @param nombre Nombre del usuario
     * @param nombreAutor Nombre de autor del usuario
     * @param contrasena Contraseña del usuario
     * @param fechaNacimiento Fecha de nacimiento del usuario
     * @return "OPERACION_INACCESIBLE" si el sistema tiene iniciada una sesión.
     * "NOMBRE_INVALIDO" si este no tiene el número mínimo de caracteres.
     * "NOMBRE_AUTOR_INVALIDO" si este no tiene el número mínimo de caracteres.
     * "CONTRASENA_INVALIDA" si esta no tiene el número mínimo de caracteres.
     * "EDAD_INVALIDA" si el usuario tiene menos de 14 años.
     * "USUARIO_REPETIDO" si el usuario ya está en la base de datos.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status registrarse(String nombre, String nombreAutor, String contrasena, Calendar fechaNacimiento) {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;
        if (nombreAutor.length() < configuracion.getCaracteresMinimos()) return Status. NOMBRE_AUTOR_INVALIDO;
        if (contrasena.length() < configuracion.getCaracteresMinimos()) return Status.CONTRASENA_INVALIDA;
        Calendar fechaNacimientoMinima = new GregorianCalendar();
        fechaNacimientoMinima.add(Calendar.YEAR, -configuracion.getEdadMinima());
        if (fechaNacimiento.after(fechaNacimientoMinima)) return Status.EDAD_INVALIDA;

        if (baseDeDatos.buscarUsuario(nombre) != null) return Status.USUARIO_REPETIDO;

        baseDeDatos.anadirUsuario(new Usuario(nombre, nombreAutor, contrasena, fechaNacimiento));

        return Status.OK;
    }

    /**
     * Cierra la sesión actual.
     * @return "OPERACION_INACCESIBLE" si no hay una sesión iniciada.
     * "OK" en caso contrario.
     */
    public Status cerrarSesion() {
        if (modoEjecucion == ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        modoEjecucion = ModoEjecucion.DESCONECTADO;

        return Status.OK;
    }

    /**
     * Reproduce el elemento reproducible pasado como argumento.
     * @param elemento Elemento reproducible a reproducir
     */
    public void reproducir(ElementoReproducible elemento) {
        moduloMP3.anadirCanciones(elemento.getCanciones());
        moduloMP3.reproducir();
    }

    /**
     * Reanuda la reproducción de canciones.
     */
    public void reanudarCancion() {
        moduloMP3.reproducir();
    }

    /**
     * Pausa la reproducción de canciones.
     */
    public void pausarCancion() {
        moduloMP3.pausar();
    }

    /**
     * Reiniciar la reproducción de la canción actual.
     */
    public void reiniciarCancion() {
        moduloMP3.reiniciar();
    }
    
    /**
     * Avanza, si es posible, a la siguiente canción del reproductor.
     */
    public void avanzarCancion() {
        moduloMP3.avanzar();
    }

    /**
     * Retrocede, si es posible, a la anterior canción del reproductor.
     */
    public void retrocederCancion() {
        moduloMP3.retroceder();
    }

    /**
     * Busca autores en el sistema dado un nombre.
     * @param nombreAutor Nombre a partir del que se buscará
     * @return Lista con los usuarios cuyos nombres de autor contienen el pasado
     * como argumento.
     */
    public List<Usuario> buscarAutores(String nombreAutor) {
        return baseDeDatos.buscarUsuarios(nombreAutor);
    }

    /**
     * Añade al usuario actual a la lista de seguidores del autor pasado por
     * argumento.
     * @param autor Autor que debe seguir el usuario a actual.
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "OK" en caso contrario.
     */
    public Status seguirAutor(Usuario autor) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        autor.anadirSeguidor(usuarioActual);

        return Status.OK;
    }

    /**
     * Busca canciones en el sistema dado un nombre.
     * @param nombre Nombre a partir del que se buscará
     * @return Lista con las canciones cuyos nombres contienen el pasado como 
     * argumento.
     */
    public List<Cancion> buscarCanciones(String nombre) {
        return baseDeDatos.buscarCanciones(nombre, usuarioActual.getPremium() && usuarioActual.puedeContenidoExplicito());
    }

    /**
     * Recibe los datos necesarios para subir una canción, comprueba que sean
     * validos y añade la canción a la base de datos.
     * @param nombre Nombre de la canción
     * @param ruta Ruta del fichero MP3
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "NOMBRE_INVALIDO" si este no tiene el número mínimo de caracteres.
     * "MP3_INVALIDO" si el fichero no existe o no es válido.
     * "CANCION_REPETIDA" si esta está ya presente en la base de datos.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status subirCancion(String nombre, String ruta) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        File fichero = new File(ruta);
        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;
        if (!ModuloMP3.validar(fichero)) return Status.MP3_INVALIDO;

        long id = baseDeDatos.getIdSiguienteCancion();
        long duracion = ModuloMP3.obtenerDuracion(fichero);
        Cancion cancion = new Cancion(nombre, duracion, usuarioActual, id);

        Status status = baseDeDatos.anadirCancion(cancion);
        if (status == Status.OK) {
            usuarioActual.anadirCancion(cancion);
            fichero.renameTo(new File(rutaFicherosMP3 + id + ".mp3"));
        }

        return status;
    }

    /**
     * Elimina la canción indicada de la base de datos
     * @param cancion Canción a eliminar
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "OK" en caso contrario.
     */
    public Status eliminarCancion(Cancion cancion) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        baseDeDatos.eliminarCancion(cancion);
        usuarioActual.eliminarCancion(cancion);
        
        return Status.OK;
    }

    /**
     * Actualiza el fichero MP3 de la canción indicada
     * @param cancion Cancion a modificar
     * @param ruta Ruta del nuevo fichero MP3
     * @return "NO_MODIFICABLE" si la canción no se puede modificar.
     * "MP3_INVALIDO" si el nuevo fichero MP3 no existe o no es válido.
     * "OK" si no se da ninguna de las anteriores
     */
    public Status actualizarCancion(Cancion cancion, String ruta) {
        if (!cancion.getModificable()) return Status.CANCION_NO_MODIFICABLE;

        File fichero = new File(ruta);
        if (!ModuloMP3.validar(fichero)) return Status.MP3_INVALIDO;

        fichero.renameTo(new File(rutaFicherosMP3 + cancion.getId()));
        cancion.setEstado(EstadoCancion.PENDIENTE_VALIDACION);

        return Status.OK;
    }

    /**
     * Busca álbumes en el sistema dado un nombre.
     * @param nombre Nombre a partir del que se buscará
     * @return Lista con los álbumes cuyos nombres contienen el pasado como
     * argumento.
     */
    public List<Album> buscarAlbumes(String nombre) {
        return baseDeDatos.buscarAlbumes(nombre);
    }

    /**
     * Recibe los datos necesarios para crear un álbum, comprueba que son 
     * válidos y lo añade a la base de datos.
     * @param nombre Nombre del álbum
     * @param ano Año de publicación del álbum
     * @param canciones Lista de canciones que componen el álbum
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "NOMBRE_INVALIDO" si este no tiene el número mínimo de caracteres.
     * "ALBUM_VACIO" si el lista de canciones está vacía.
     * "ALBUM_REPETIDO" si el album ya está presente en la base de datos.
     * "OK" si no se da ninguna de las anteriores. 
     */
    public Status crearAlbum(String nombre, int ano, List<Cancion> canciones) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;
        if (canciones.size() == 0) return Status.ALBUM_VACIO;

        Album album = new Album(nombre, usuarioActual, ano, canciones);

        Status status = baseDeDatos.anadirAlbum(album);
        if (status == Status.OK) usuarioActual.anadirAlbum(album);

        return Status.OK;
    }

    /**
     * Eliminar el álbum indicado de la base de datos y del usuario actual.
     * @param album Álbum a eliminar
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "OK" en caso contrario.
     */
    public Status eliminarAlbum(Album album) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        baseDeDatos.eliminarAlbum(album);
        usuarioActual.eliminarAlbum(album);

        return Status.OK;
    }

    /**
     * Devuelve las listas de reproducción del usuario actual.
     * @return Listas de reproducción del usuario actual.
     */
    public Set<ListaReproduccion> obtenerListasReproduccion() {
        return usuarioActual.getListasReproducion();
    }

    /**
     * Recibe el nombre de una nueva lista, comprueba que sea válido y la añade
     * al usuario actual.
     * @param nombre Nombre de la lista de reproducción
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "NOMBRE_INVALIDO" si este no tiene el número mínimo de caracteres.
     * "LISTA_REPRODUCCION_REPETIDA" si la lista de reproducción ya está 
     * presente en el usuario actual.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status crearListaReproduccion(String nombre) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;

        ListaReproduccion listaReproduccion = new ListaReproduccion(nombre);
        
        return usuarioActual.anadirListaReproduccion(listaReproduccion);
    }

    /**
     * Añade un nuevo elemento reproducible a la lista de reproducción indicada.
     * @param listaReproduccion Lista de reproducción a modificar
     * @param elemento Elemento reproducible a añadir a la lista
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "OK" en caso contrario.
     */ 
    public Status anadirAListaReproduccion(ListaReproduccion listaReproduccion, ElementoReproducible elemento) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;
        
        listaReproduccion.anadirElemento(elemento);

        return Status.OK;
    }

    /**
     * Elimina un elemento reproducible de la lista de reproducción indicada.
     * @param listaReproduccion Lista de reproducción a modificar
     * @param elemento Elemento reproducible a eleminar de la lista
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "OK" en caso contrario.
     */
    public Status eliminarDeListaReproduccion(ListaReproduccion listaReproduccion, ElementoReproducible elemento) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        listaReproduccion.eliminarElemento(elemento);

        return Status.OK;
    }

    /**
     * Elimina una lista de reproducción del usuario actual.
     * @param listaReproduccion Lista de reproducción a eliminar
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "OK" en caso contrario.
     */
    public Status eliminarListaReproduccion(ListaReproduccion listaReproduccion) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        usuarioActual.eliminarListaReproduccion(listaReproduccion);

        return Status.OK;
    }

    /**
     * Devuelve el set de notificaciones del usuario actual.
     * @return El set de notifcaciones del usuario actual si la sesión es de 
     * usuario registrado y "null" en caso contrario.
     */
    public Set<Notificacion> obtenerNotificaciones() {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return null;
        
        return usuarioActual.getNotificaciones();
    }

    /**
     * Modifica el estado de la canción indicada, tomando las medidas
     * correspondientes a ese cambio y notificando al autor y, si corresponde,
     * a los seguidores del autor.
     * @param cancion Canción cuyo estado se ha de modificar
     * @param estado Nuevo estado de la canción
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "OK" en caso contrario.
     */
    public Status cambiarEstadoCancion(Cancion cancion, EstadoCancion estado) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        cancion.setEstado(estado);
        gestorEventos.cancelarEliminacionCancion(cancion);
        
        if (estado == EstadoCancion.VALIDADA || estado == EstadoCancion.VALIDADA_EXPLICITA) {
            NotificacionCancion notificacionAutor;
            if (estado == EstadoCancion.VALIDADA) {
                notificacionAutor = new NotificacionCancion(TipoNotificacion.CANCION_VALIDADA, cancion);
            } else {
                notificacionAutor = new NotificacionCancion(TipoNotificacion.CANCION_VALIDADA_EXPLICITA, cancion);
            }
            cancion.getAutor().anadirNotificacion(notificacionAutor);

            for (Usuario seguidor : cancion.getAutor().getSeguidores()) {
                NotificacionCancion notificacionSeguidor = new NotificacionCancion(TipoNotificacion.CANCION_SEGUIDO, cancion);
                seguidor.anadirNotificacion(notificacionSeguidor);
            }
        } else if (estado == EstadoCancion.RECHAZADA_REVISABLE) {
            gestorEventos.programarEliminacionCancion(cancion);
            NotificacionCancion notificacion = new NotificacionCancion(TipoNotificacion.CANCION_RECHAZADA, cancion);
            cancion.getAutor().anadirNotificacion(notificacion);
        } else if (estado == EstadoCancion.BLOQUEADA_TEMPORAL || estado == EstadoCancion.BLOQUEADA_TEMPORAL_EXPLICITA) {
            NotificacionCancion notificacion = new NotificacionCancion(TipoNotificacion.CANCION_BLOQUEADA_TEMPORAL, cancion);
            cancion.getAutor().anadirNotificacion(notificacion);
        }

        return Status.OK;
    }

    /**
     * Devuelve la lista con todos los reportes pendientes de valorar.
     * @return Lista con todos los reportes pendientes de valorar.
     */
    public List<Reporte> obtenerReportes() {
        return baseDeDatos.obtenerReportes();
    }

    /**
     * Recibe la información necesaria para generar un reporte, comprueba que es
     * válida y lo añade a la base de datos.
     * @param descripcion Descripción del motivo del reporte
     * @param cancion Canción reportada
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "DESCRIPCION_INVALIDA" si esta no tiene los caracteres mínimos.
     * "CANCION_NO_REPORTABLE" si la canción ya está bloqueada.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status reportar(String descripcion, Cancion cancion) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        if (descripcion.length() < configuracion.getCaracteresMinimos()) return Status.DESCRIPCION_INVALIDA;
        if (cancion.getBloqueado()) return Status.CANCION_NO_REPORTABLE;

        Reporte reporte = new Reporte(descripcion, usuarioActual, cancion);
        if (cancion.getEstado() == EstadoCancion.VALIDADA) {
            cancion.setEstado(EstadoCancion.BLOQUEADA_TEMPORAL);
        } else {
            cancion.setEstado(EstadoCancion.BLOQUEADA_TEMPORAL_EXPLICITA);
        }
        
        baseDeDatos.anadirReporte(reporte);

        return Status.OK;
    }

    /**
     * Toma las medidas correspondientes a aceptar un reporte, bloqueando al
     * autor de la canción reportada, así como a todas sus canciones.
     * @param reporte Reporte a aceptar
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "OK" en caso contrario
     */
    public Status aceptarReporte(Reporte reporte) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        Usuario reportado = reporte.getCancionReportada().getAutor();
        reportado.setBloqueado(true);

        NotificacionCancion notificacion = new NotificacionCancion(TipoNotificacion.REPORTE_ACEPTADO, reporte.getCancionReportada());
        reporte.getReportador().anadirNotificacion(notificacion);

        return Status.OK;
    }

    /**
     * Toma las medidas correspondientes a rechazar un reporte, bloqueando al
     * autor del reporte durante un mes.
     * @param reporte Reporte a rechazar
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "OK" en caso contrario
     */
    public Status rechazarReporte(Reporte reporte) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        Cancion cancion = reporte.getCancionReportada();
        if (cancion.getEstado() == EstadoCancion.BLOQUEADA_TEMPORAL) {
            cancion.setEstado(EstadoCancion.VALIDADA);
        } else if (cancion.getEstado() == EstadoCancion.BLOQUEADA_TEMPORAL_EXPLICITA) {
            cancion.setEstado(EstadoCancion.VALIDADA_EXPLICITA);
        }

        reporte.getReportador().setBloqueado(true);
        gestorEventos.programarDesbloqueoUsuario(reporte.getReportador());

        return Status.OK;
    }

    /**
     * Realiza la transacción a través del módulo externo de pagos.
     * @param tarjeta Tarjeta con la que realiza el pago
     * @return "OPERACION_INACCESIBLE" si la sesión no es de usuario registrado.
     * "YA_ES_PREMIUM" si el usuario actual ya es premium.
     * "TARJETA_INVALIDA" si el módulo indica que la tarjeta es invalida.
     * "FALLO_INTERNET" si el módulo notifica un problema en la conexión.
     * "PAGO_RECHAZADO" si el módulo notifica que el pago ha sido rechazado.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status pagarPremium(String tarjeta) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        if (usuarioActual.getPremium()) return Status.YA_ES_PREMIUM;

        try {
            TeleChargeAndPaySystem.charge(tarjeta, "Pago premium", configuracion.getCuotaPremium());
        } catch (InvalidCardNumberException e) {
            return Status.TARJETA_INVALIDA;
        } catch (FailedInternetConnectionException e) {
            return Status.FALLO_INTERNET;
        } catch (OrderRejectedException e) {
            return Status.PAGO_RECHAZADO;
        }
        
        return Status.OK;
    }

    /**
     * Modifica el archivo de configuración, estableciendo unos nuevos
     * credenciales para iniciar sesión como administrador.
     * @param nombre Nuevo nombre del administrador
     * @param contrasena Nueva contraseña del administrador
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "NOMBRE_INVALIDO" si este no tiene el número mínimo de caracteres.
     * "CONTRASENA_INVALIDA" si esta no tiene el número mínimo de caracteres.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status modificarCredencialesAdministrador(String nombre, String contrasena) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;
        if (contrasena.length() < configuracion.getCaracteresMinimos()) return Status.CONTRASENA_INVALIDA;

        configuracion.setNombreAdministrador(nombre);
        configuracion.setContrasenaAdministrador(contrasena);

        return Status.OK;
    }

    /**
     * Modifica el archivo de configuración, estableciendo un nuevo límite
     * de reproducciones máximas por sesión para un usuario anónimo.
     * @param maxReproduccionesAnonimo Nuevo límite
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "CONFIGURACION_INVALIDA" si el nuevo límite es menor o igual a 0.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status modificarMaxReproduccionesAnonimo(int maxReproduccionesAnonimo) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        if (maxReproduccionesAnonimo <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setMaxReproduccionesAnonimo(maxReproduccionesAnonimo);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    /**
     * Modifica el archivo de configuración, estableciendo un nuevo límite
     * de reproducciones mensuales máximas para un usuario registrado.
     * @param maxReproduccionesRegistrado Nuevo límite
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "CONFIGURACION_INVALIDA" si el nuevo límite es menor o igual a 0.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status modificarMaxReproduccionesRegistrado(int maxReproduccionesRegistrado) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        if (maxReproduccionesRegistrado <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setMaxReproduccionesRegistrado(maxReproduccionesRegistrado);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    /**
     * Modifica el archivo de configuración, estableciendo una nueva cuota
     * mensual para el servicio premium.
     * @param cuotaPremium Nueva cuota mensual
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "CONFIGURACION_INVALIDA" si la nueva cuota mensual es menor o igual a 0.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status modificarCuotaPremium(float cuotaPremium) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        if (cuotaPremium <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setCuotaPremium(cuotaPremium);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    /**
     * Modifica el archivo de configuración, estableciendo un nuevo límite
     * de reproducciones mensuales mínimas, en las canciones propias, para
     * recibir gratuitamente el servicio premium.
     * @param minReproduccionesPremium Nuevo límite
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "CONFIGURACION_INVALIDA" si el nuevo límite es menor o igual a 0.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status modificarMinReproduccionesPremium(int minReproduccionesPremium) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        if (minReproduccionesPremium <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setMinReproduccionesPremium(minReproduccionesPremium);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    /**
     * Modifica el archivo de configuración, estableciendo un nuevo límite
     * de caracteres mínimos que deben tener los campos de texto.
     * @param caracteresMinimos Nuevo límite
     * @return "OPERACION_INACCESIBLE" si la sesión no es de administrador.
     * "CONFIGURACION_INVALIDA" si el nuevo límite es menor o igual a 0.
     * "OK" si no se da ninguna de las anteriores.
     */
    public Status modificarCaracteresMinimos(int caracteresMinimos) {
        if (modoEjecucion != ModoEjecucion.ADMINISTRADOR) return Status.OPERACION_INACCESIBLE;

        if (caracteresMinimos <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setCaracteresMinimos(caracteresMinimos);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    public void cerrarSistema() {
        baseDeDatos.guardarEnDisco();
        gestorEventos.guardarInformacion();
        hiloGestorEventos.stop();
        hiloModuloMP3.stop();        
    }
}