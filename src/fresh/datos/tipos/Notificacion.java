package fresh.datos.tipos;

/**
 * Permite trabajar con notificaciones en el sistema.
 * @author Víctor Yrazusta (victor.yrazusta@estudiante.uam.es)
 */
public class Notificacion {
    private TipoNotificacion tipoNotificacion;

    /**
     * Instancia una notificación dado su tipo.
     * @param tipoNotificacion Tipo de la notificación.
     */
    public Notificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    /**
     * Devuelve el tipo de la notificación.
     * @return Tipo de la notificación.
     */
    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }
}

