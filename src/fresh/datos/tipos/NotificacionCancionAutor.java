package fresh.datos.tipos;

/**
 * Permite trabajar con notificaciones relacionadas con una canción y un autor.
 */
public class NotificacionCancionAutor extends NotificacionCancion {
    private Usuario autor;

    /**
     * Instancia una notificación dado su tipo, canción y autor relacionados.
     * @param tipoNotificacion Tipo de la notificación.
     * @param cancion Canción relacionada con la notificación.
     * @param autor Autor relacionado con la notificación.
     */
    public NotificacionCancionAutor(TipoNotificacion tipoNotificacion, 
                                    Cancion cancion, Usuario autor) {
        super(tipoNotificacion, cancion);
        this.autor = autor;
    }

    /**
     * Devuelve el autor relacionado con la notificación.
     * @return Autor relacionado con la notifiación.
     */
    public Usuario getAutor() {
        return autor;
    }
}