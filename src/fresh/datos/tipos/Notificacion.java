package fresh.datos.tipos;

public class Notificacion {
    private TipoNotificacion tipoNotificacion;

    public Notificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }
}

