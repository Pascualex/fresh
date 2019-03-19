package fresh.datos.tipos;

/**
 * Permite trabajar con notificaciones relacionadas con una canción 
 * en el sistema.
 * @author Víctor Yrazusta (victor.yrazusta@estudiante.uam.es)
 */
public class NotificacionCancion extends Notificacion {
    private Cancion cancion;

    /**
     * Instancia una notificación dado su tipo y canción relacionada.
     * @param tipoNotificacion Tipo de la notificación.
     * @param cancion Canción relacionada con la notificación.
     */
    public NotificacionCancion(TipoNotificacion tipoNotificacion, Cancion cancion) {
        super(tipoNotificacion);
        this.cancion = cancion;
    }

    /**
     * Devuelve la canción con la que está relacionada la notificación.
     * @return Canción relacionada con la notificación.
     */
    public Cancion getCancion() {
        return cancion;
    }
}