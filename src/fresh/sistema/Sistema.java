package fresh.sistema;

import fresh.datos.BaseDeDatos;
import fresh.datos.tipos.*;
import fresh.Status;
import java.time.LocalDate;
import java.util.Objects;

public class Sistema {
    private ModoEjecucion modoEjecucion;
    private Usuario usuarioActual;
    private BaseDeDatos baseDeDatos;
    private int reproduccionesSesion;

    public Sistema() {
        modoEjecucion = ModoEjecucion.DESCONECTADO;
        baseDeDatos = BaseDeDatos.cargarBaseDeDatos("./baseDedatos/baseDeDatos.bd");
    }

    // FALTA COMPROBAR CREDENCIALES DEL ADMINISTRADOR
    public Status iniciarSesion(String nombre, String contrasena) {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        Usuario usuario = baseDeDatos.buscarUsuario(nombre);
        if (usuario == null) return Status.NOMBRE_INVALIDO;
        if (!Objects.equals(contrasena, usuario.getContrasena())) return Status.CONTRASENA_INVALIDA;

        modoEjecucion = ModoEjecucion.REGISTRADO;
        usuarioActual = usuario;
        
        return Status.OK;
    }

    public Status iniciarSesionAnonimo() {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        modoEjecucion = ModoEjecucion.ANONIMO;
        reproduccionesSesion = 0;

        return Status.OK;
    }

    public Status registrarse(String nombre, String nombreAutor, String contrasena, LocalDate fechaNacimiento) {
        if (modoEjecucion != ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        if (nombre.length() < 4) return Status.NOMBRE_INVALIDO;
        if (nombreAutor.length() < 4) return Status. NOMBRE_AUTOR_INVALIDO;
        if (contrasena.length() < 4) return Status.CONTRASENA_INVALIDA;
        if (false /*comprobación edad*/) return Status.EDAD_INVALIDA;

        if (baseDeDatos.buscarUsuario(nombre) != null) return Status.USUARIO_REPETIDO;

        baseDeDatos.anadirUsuario(new Usuario(nombre, nombreAutor, contrasena, fechaNacimiento));

        return Status.OK;
    }

    public Status cerrarSesion() {
        if (modoEjecucion == ModoEjecucion.DESCONECTADO) return Status.OPERACION_INACCESIBLE;

        modoEjecucion = ModoEjecucion.DESCONECTADO;

        return Status.OK;
    }

    // public Status reproducir();

    // public Status reanudarCancion();

    // public Status pausarCancion();

    // public Status pararCancion();

    // public Status buscarAutores();

    public Status seguirAutor(Usuario autor) {
        if (modoEjecucion != ModoEjecucion.REGISTRADO) return Status.OPERACION_INACCESIBLE;

        autor.anadirSeguidor(usuarioActual);

        return Status.OK;
    }

    // public Status buscarCanciones();

    // public Status subirCancion();

    // public Status eliminarCancion();

    // public Status actualizarCancion();

    // public Status buscarAlbumes();

    // public Status crearAlbum();

    // public Status eliminarAlbum();

    // public Status crearListaReproduccion();

    // public Status anadirAListaReproduccion();

    // public Status eliminarDeListaReproduccion();

    // public Status eliminarListaReproduccion();

    // public Status validarCancion();

    // public Status reportar();

    // public Status valorarReporte();

    // public Status pagarPremium();

    // public Status modificarConfiguracion();
}