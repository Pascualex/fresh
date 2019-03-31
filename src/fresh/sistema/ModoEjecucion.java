package fresh.sistema;

/**
 * Está enumeración establece los posibles módulos de ejecución del sistema.
 */
public enum ModoEjecucion {

    /**
     * Modo de ejecución inicial de la aplicación. Permite registrar usuarios e
     * iniciar sesión en cualquiera de los otros 3 modos.
     */
    DESCONECTADO,

    /**
     * Se accede a este modo sin necesidad de aportar credenciales. No tiene
     * acceso a una parte importante de la funcionalidad de la aplicación.
     */
    ANONIMO,

    /**
     * Se accede a este modo al aportar los credenciales correctos de un usuario
     * presente en la base de datos. Permite tener persistencia entre distintas
     * sesiones, pudiendo subir contenido propio, crear listas de reproducción,
     * seguir a autores, etc.
     */
    REGISTRADO,

    /**
     * Se accede al modo administrador al introducir los credenciales
     * especificados en el fichero de configuración. Permite modificar dicho 
     * fichero y resolver peticiones de usuarios, como subidas de canciones o 
     * reportes por copyright.
     */
    ADMINISTRADOR
}   