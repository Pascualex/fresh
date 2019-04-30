package fresh;

/**
 * Esta enumeración establece los posibles resultados de una gran variedad de 
 * operaciones de la aplicación.
 */
public enum Status {

    /** 
     * Indica que no se ha producido ningún error.
     */
    OK,
       
    /**
     * Indica que el nombre de usuario no tiene los caracteres mínimos.
     */
    NOMBRE_INVALIDO,

    /**
     * Indica que el nombre de autor no tiene los caracteres mínimos.
     */
    NOMBRE_AUTOR_INVALIDO,

    /**
     * Indica que la contraseña no tiene los caracteres mínimos.
     */
    CONTRASENA_INVALIDA,

    /**
     * Indica que el usuario no tiene la edad mínima necesaria para registrarse.
     */
    EDAD_INVALIDA,

    /**
     * Indica que ya existe un usuario con el nombre especificado.
     */
    USUARIO_REPETIDO,

    /**
     * Indica que el usuario con el que se intenta iniciar sesión ha sido 
     * permanentemente bloqueado.
     */
    USUARIO_BLOQUEADO,
    
    /**
     * Indica que ya existe una canción con el nombre especificado.
     */
    CANCION_REPETIDA,

    /**
     * Indica que el fichero MP3 no existe o no es válido.
     */
    MP3_INVALIDO,

    /**
     * Indica que el álbum pasado no tiene canciones.
     */
    ALBUM_VACIO,

    /**
     * Indica que ya existe un álbum con el nombre especificado.
     */
    ALBUM_REPETIDO,

    /**
     * Indica que ya existe una lista de reproducción con el nombre 
     * especificado.
     */
    LISTA_REPRODUCCION_REPETIDA,    

    /**
     * Indica que la canción no es modificable.
     */
    CANCION_NO_MODIFICABLE,

    /**
     * Indica que la descripción del reporte no tiene los caracteres mínimos.
     */
    DESCRIPCION_INVALIDA,
    
    /**
     * Indica que la canción no es reportable.
     */
    CANCION_NO_REPORTABLE,

    /**
     * Indica que el usuario ya es premium.
     */
    YA_ES_PREMIUM,

    /**
     * Indica que la tarjeta utilizada no es válida.
     */
    TARJETA_INVALIDA,

    /**
     * Indica que se ha producido un fallo en la conexión a internet.
     */
    FALLO_INTERNET,

    /**
     * Indica que el pago ha sido rechazado.
     */
    PAGO_RECHAZADO,

    /**
     * Indica que el nuevo campo especificado en la configuración no es válido.
     */
    CONFIGURACION_INVALIDA,

    /**
     * Indica que se ha producido un error al cargar algo de un fichero.
     */
    ERROR_CARGAR,

    /**
     * Indica que la función ejecutada no es accesible desde el modo de
     * ejecución actual del sistema.
     */
    OPERACION_INACCESIBLE,

    /**
     * Indica que se ha iniciado sesión como administrador.
     */
    INICIO_ADMINISTRADOR
}