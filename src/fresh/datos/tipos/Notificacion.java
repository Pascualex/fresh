package fresh.datos.tipos;

import fresh.sistema.Sistema;

import java.io.Serializable;

/**
 * Permite trabajar con notificaciones.
 */
public class Notificacion implements Serializable {
    private static final long serialVersionUID = Sistema.numeroVersion;
    
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