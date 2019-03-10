package fresh.datos.tipos;

public class NotificacionCancionAutor extends NotificacionCancion {
    private Usuario autor;

    public NotificacionCancionAutor(TipoNotificacion tipoNotificacion, 
                                    Cancion cancion, Usuario autor) {
        super(tipoNotificacion, cancion);
        this.autor = autor;
    }

    public Usuario getAutor() {
        return autor;
    }
}