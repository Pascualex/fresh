package fresh.datos.tipos;

import java.io.Serializable;

/**
 * Permite trabajar con notificaciones.
 */
public class Notificacion implements Serializable {
    private TipoNotificacion tipoNotificacion;

    /**
     * Instancia una notificación dado su tipo.
     * @param tipoNotificacion Tipo de la notificación
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