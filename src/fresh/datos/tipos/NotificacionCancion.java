package fresh.datos.tipos;

public class NotificacionCancion extends Notificacion {
    private Cancion cancion;

    public NotificacionCancion(TipoNotificacion tipoNotificacion, Cancion cancion) {
        super(tipoNotificacion);
        this.cancion = cancion;
    }

    public Cancion getCancion() {
        return cancion;
    }
}