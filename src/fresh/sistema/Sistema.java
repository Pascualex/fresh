package fresh.sistema;

import fresh.datos.BaseDeDatos;
import fresh.datos.tipos.*;
import fresh.modulos.*;
import fresh.Status;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.List;
import java.util.Set;

public class Sistema {
    private static final String rutaBaseDeDatos = "./baseDedatos/baseDeDatos.bd";
    private static final String rutaGestorEventos = "./gestorEventos/gestorEventos";
    private static final String rutaConfiguracion = "./configuracion/configuracion.txt";
    private static final String rutaFicherosMP3 = "./canciones/";

    private ModoEjecucion modoEjecucion;
    private Usuario usuarioActual;
    private BaseDeDatos baseDeDatos;
    private GestorEventos gestorEventos;
    private ModuloMP3 moduloMP3;    
    private Configuracion configuracion;    
    private int reproduccionesSesion; // Falta controlar el número de reproducciones por sesión (si es anónimo)
                                      // También falta controlar las reproducciones mensuales (si está registrado)

    /**
     * Crea el sistema. Sus características vienen definidas en constantes y en
     * el fichero de configuración, por lo que no recibe argumentos.
     */
    public Sistema() {
        modoEjecucion = ModoEjecucion.DESCONECTADO;
        baseDeDatos = BaseDeDatos.cargarBaseDeDatos(rutaBaseDeDatos);
        gestorEventos = GestorEventos.cargarGestorEventos(baseDeDatos, rutaGestorEventos);
        moduloMP3 = new ModuloMP3();
        configuracion = new Configuracion(rutaConfiguracion);
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
        reproduccionesSesion = 0;

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
        fechaNacimientoMinima.add(Calendar.YEAR, -14);
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

    public void reproducir(ElementoReproducible elemento) {
        moduloMP3.anadirAColaReproduccion(elemento.getCanciones());
        moduloMP3.run();
    }

    public void reanudarCancion() {
        moduloMP3.run();
    }

    public void pausarCancion() {
        moduloMP3.pause();
    }

    public void pararCancion() {
        moduloMP3.stop(); // No sé cómo está implementado esto - Alex
    }

    /**
     * Busca autores en el sistema dado un nombre nombre.
     * @param nombreAutor Nombre a partir del que se buscará
     * @return Lista con los usuarios cuyos nombres de autor contienen el pasado
     * por argumento 
     */
    public List<Usuario> buscarAutores(String nombreAutor) {
        return baseDeDatos.buscarUsuarios(nombreAutor);
    }

    /**
     * Añade al usuario actual a la lista de seguidores del autor pasado por
     * argumento.
     * @param autor Autor que debe seguir el usuario a actual.
     * @return "OPERACION_INACCESIBLE" si el sistema no tiene abierta una sesión
     * de un usuario registrado.
     * "OK" en caso contrario.
     */
    public Status seguirAutor(Usuario autor) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        autor.anadirSeguidor(usuarioActual);

        return Status.OK;
    }

    public List<Cancion> buscarCanciones(String nombre) {
        return baseDeDatos.buscarCanciones(nombre);
    }

    public Status subirCancion(String nombre, String ruta) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        File fichero = new File(ruta);
        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;
        if (!moduloMP3.validar(fichero)) return Status.FICHERO_INVALIDO;

        long id = baseDeDatos.getIdSiguienteCancion();
        long duracion = moduloMP3.obtenerDuracion(fichero);
        Cancion cancion = new Cancion(nombre, duracion, usuarioActual, id);

        Status status = baseDeDatos.anadirCancion(cancion);
        if (status == Status.OK) {
            usuarioActual.anadirCancion(cancion);
            fichero.renameTo(new File(rutaFicherosMP3 + id));
        }

        return status;
    }

    public void eliminarCancion(Cancion cancion) {        
        baseDeDatos.eliminarCancion(cancion);
        usuarioActual.eliminarCancion(cancion);
    }

    public Status actualizarCancion(Cancion cancion, String ruta) {
        if (!cancion.getModificable()) return Status.CANCION_NO_MODIFICABLE;

        File fichero = new File(ruta);
        if (!moduloMP3.validar(fichero)) return Status.FICHERO_INVALIDO;

        fichero.renameTo(new File(rutaFicherosMP3 + cancion.getId()));

        return Status.OK;
    }

    public List<Album> buscarAlbumes(String nombre) {
        return baseDeDatos.buscarAlbumes(nombre);
    }

    public Status crearAlbum(String nombre, int ano, Cancion[] canciones) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;
        if (canciones.length == 0) return Status.ALBUM_VACIO;

        Album album = new Album(nombre, usuarioActual, ano, canciones);

        Status status = baseDeDatos.anadirAlbum(album);
        if (status == Status.OK) usuarioActual.anadirAlbum(album);

        return Status.OK;
    }

    public void eliminarAlbum(Album album) {
        baseDeDatos.eliminarAlbum(album);
        usuarioActual.eliminarAlbum(album);
    }

    public Set<ListaReproduccion> obtenerListasReproduccion() {
        return usuarioActual.getListasReproducion();
    }

    public Status crearListaReproduccion(String nombre) {
        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;

        ListaReproduccion listaReproduccion = new ListaReproduccion(nombre);

        usuarioActual.anadirListaReproduccion(listaReproduccion);
        return Status.OK;
    }

    public void anadirAListaReproduccion(ListaReproduccion listaReproduccion, ElementoReproducible elemento) {
        listaReproduccion.anadirElemento(elemento);
    }

    public void eliminarDeListaReproduccion(ListaReproduccion listaReproduccion, ElementoReproducible elemento) {
        listaReproduccion.eliminarElemento(elemento);
    }

    public void eliminarListaReproduccion(ListaReproduccion listaReproduccion) {
        usuarioActual.eliminarListaReproduccion(listaReproduccion);
    }

    public void cambiarEstadoCancion(Cancion cancion, EstadoCancion estado) {
        cancion.setEstado(estado);
        gestorEventos.cancelarEliminacionCancion(cancion);

        if (estado == EstadoCancion.RECHAZADA_REVISABLE) {
            gestorEventos.programarEliminacionCancion(cancion);
        }
    }

    public List<Reporte> obtenerReportes() {
        return baseDeDatos.obtenerReportes();
    }

    public Status reportar(String descripcion, Cancion cancion) {
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

    public void aceptarReporte(Reporte reporte) {
        Usuario reportado = reporte.getCancionReportada().getAutor();
        reportado.setBloqueado(true);
        for (Cancion cancion : reportado.getCanciones()) {
            cancion.setEstado(EstadoCancion.BOQUEADA_PERMANENTE);
        }
    }

    public void rechazarReporte(Reporte reporte) {
        Cancion cancion = reporte.getCancionReportada();
        if (cancion.getEstado() == EstadoCancion.BLOQUEADA_TEMPORAL) {
            cancion.setEstado(EstadoCancion.VALIDADA);
        } else if (cancion.getEstado() == EstadoCancion.BLOQUEADA_TEMPORAL_EXPLICITA) {
            cancion.setEstado(EstadoCancion.VALIDADA_EXPLICITA);
        }
        reporte.getReportador().setBloqueado(true);
        gestorEventos.programarDesbloqueoUsuario(reporte.getReportador());
    }

    // public Status pagarPremium();

    public Status modificarCredencialesAdministrador(String nombre, String contrasena) {
        if (nombre.length() < configuracion.getCaracteresMinimos()) return Status.NOMBRE_INVALIDO;
        if (contrasena.length() < configuracion.getCaracteresMinimos()) return Status.CONTRASENA_INVALIDA;

        configuracion.setNombreAdministrador(nombre);
        configuracion.setContrasenaAdministrador(contrasena);

        return Status.OK;
    }

    public Status modificarMaxReproduccionesAnonimo(int maxReproduccionesAnonimo) {
        if (maxReproduccionesAnonimo <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setMaxReproduccionesAnonimo(maxReproduccionesAnonimo);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    public Status modificarMaxReproduccionesRegistrado(int maxReproduccionesRegistrado) {
        if (maxReproduccionesRegistrado <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setMaxReproduccionesRegistrado(maxReproduccionesRegistrado);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    public Status modificarCuotaPremium(float cuotaPremium) {
        if (cuotaPremium <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setCuotaPremium(cuotaPremium);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    public Status modificarMinReproduccionesPremium(int minReproduccionesPremium) {
        if (minReproduccionesPremium <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setMinReproduccionesPremium(minReproduccionesPremium);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }

    public Status modificarCaracteresMinimos(int caracteresMinimos) {
        if (caracteresMinimos <= 0) return Status.CONFIGURACION_INVALIDA;

        configuracion.setCaracteresMinimos(caracteresMinimos);
        configuracion.guardarConfiguracion();

        return Status.OK;
    }
}