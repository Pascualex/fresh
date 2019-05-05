package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;

import fresh.datos.tipos.Notificacion;
import fresh.datos.tipos.NotificacionCancion;
import fresh.datos.tipos.TipoNotificacion;
import fresh.interfaz.Estilo;
import fresh.interfaz.vistas.VistaNotificaciones;

public class ControladorNotificaciones {

    public ControladorNotificaciones(Sistema sistema, VistaNotificaciones vistaNotificaciones) {
        List<Notificacion> notificaciones = sistema.getUsuarioActual().getNotificaciones();
        
        if (notificaciones.size() == 0) {
            vistaNotificaciones.scrollFrame.setVisible(false);
            vistaNotificaciones.textoSinNotificaciones.setVisible(true);
        }

        cargarNotificaciones(sistema, vistaNotificaciones, notificaciones);
    }

    private void cargarNotificaciones(Sistema sistema, VistaNotificaciones vistaNotificaciones, List<Notificacion> notificaciones) {
        vistaNotificaciones.scrollPanel.setPreferredSize(new Dimension(0, 15+notificaciones.size()*100));
        vistaNotificaciones.scrollFrame.revalidate();
        vistaNotificaciones.scrollFrame.repaint();

        JLabel textoDescripcion;

        int i = 0;
        for (Notificacion n : notificaciones) {
            if (n.getTipoNotificacion() == TipoNotificacion.CANCION_BLOQUEADA_TEMPORAL) {

                textoDescripcion = new JLabel("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + 
                            "\" ha sido bloqueada temporalmente.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_DESBLOQUEADA) {

                textoDescripcion = new JLabel("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + 
                            "\" ha sido desbloqueada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_ELIMINADA) {

                textoDescripcion = new JLabel("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + 
                            "\" ha sido eliminada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_RECHAZADA) {

                textoDescripcion = new JLabel("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + 
                            "\" ha sido rechazada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_SEGUIDO) {

                textoDescripcion = new JLabel("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + 
                            "\" ha sido rechazada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_VALIDADA) {

                textoDescripcion = new JLabel("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + 
                            "\" ha sido validada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_VALIDADA_EXPLICITA) {

                textoDescripcion = new JLabel("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + 
                            "\" ha sido validada con contenido explícito.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.PREMIUM_CADUCADO) {

                textoDescripcion = new JLabel("Tu pase premium ha caducado.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.PREMIUM_GRATUITO) {

                textoDescripcion = new JLabel("Tu cuenta ha obtenido premium de manera gratuita.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.REPORTE_ACEPTADO) {

                textoDescripcion = new JLabel("Uno de tus reportes a una canción ha sido aceptado.");
            } else {
                
                textoDescripcion = new JLabel("Error en el tipo de la notificación.");

                throw new RuntimeException("Error en el tipo de la notificación.");
            }

            textoDescripcion.setBounds(35, 15+100*i+20, 800, 40);
            textoDescripcion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDescripcion.setForeground(Estilo.colorTexto);
            textoDescripcion.setHorizontalAlignment(JLabel.LEFT);
            vistaNotificaciones.scrollPanel.add(textoDescripcion);

            i++;
        }
    }
}