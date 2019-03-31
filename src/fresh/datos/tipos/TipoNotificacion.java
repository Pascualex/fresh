package fresh.datos.tipos;

/**
 * Posibles tipos de notificación:
 * <li>{@link #LIMITE_PREMIUM}</li>
 * <li>{@link #PAGA_PREMIUM}</li>
 * <li>{@link #CANCION_VALIDADA}</li>
 * <li>{@link #CANCION_EXPLICITO}</li>
 * <li>{@link #CANCION_BLOQUEADA_TEMPORAL}</li>
 * <li>{@link #CANCION_DESBLOQUADA}</li>
 * <li>{@link #REPORTE_ACEPTADO}</li>
 * <li>{@link #CANCION_SEGUIDO}</li>
 */
public enum TipoNotificacion {
    LIMITE_PREMIUM, // Está complicado saberlo en el momento exacto
    PAGA_PREMIUM, // No sé cuando se usa esta

    /**
     * Notifica que una de las canciones del usuario ha sido validada sin
     * detectar contenido explícito.
     */
    CANCION_VALIDADA,

    /**
     * Notifica que una de las canciones del usuario ha sido validada detectando
     * contenido explícito.
     */
    CANCION_EXPLICITO,

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