package fresh.datos.tipos;

/**
 * Esta enumeración define los posibles tipos de notificación que puede recibir
 * un usuario.
 */
public enum TipoNotificacion {

    /**
     * Notifica la obtención gratuita del servicio premium como recompensa por
     * las reproducciones obtenidas el mes anterior.
     */ 
    PREMIUM_GRATUITO,   

    /**
     * Notifica la pérdida del servicio premium al final del mes.
     */
    PREMIUM_CADUCADO,

    /**
     * Notifica que una de las canciones del usuario ha sido validada sin
     * detectar contenido explícito.
     */
    CANCION_VALIDADA,

    /**
     * Notifica que una de las canciones del usuario ha sido validada detectando
     * contenido explícito.
     */
    CANCION_VALIDADA_EXPLICITA,

    /**
     * Notifica que una de las canciones del usuario ha sido rechazada, dandole
     * la posibilidad de modificar el fichero ligado a dicha canción.
     */
    CANCION_RECHAZADA,

    /**
     * Notifica que una de las canciones del usuario ha sido reporta y, por lo 
     * tanto, bloqueada cautelarmente.
     */
    CANCION_BLOQUEADA_TEMPORAL,

    /**
     * Notifica que una de las canciones bloqueadas del usuario ha sido
     * desbloqueada al ser rechazado el reporte que la había bloqueado.
     */
    CANCION_DESBLOQUEADA,

    /**
     * Notifica que una de las canciones rechazadas revisables del usuario ha
     * sido eliminada automáticamente por el sistema.
     */
    CANCION_ELIMINADA,

    /**
     * Notifica que uno de los reportes realizados por el usuario ha sido 
     * aceptado, bloqueando al usuario que cometió la infracción.
     */    
    REPORTE_ACEPTADO,
    
    /**
     * Notifica la publicación de una nueva canción por parte de uno de los
     * autores seguidos por el usuario.
     */
    CANCION_SEGUIDO
}