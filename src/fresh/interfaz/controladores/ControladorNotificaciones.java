package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Notificacion;
import fresh.datos.tipos.NotificacionCancion;
import fresh.datos.tipos.TipoNotificacion;
import fresh.interfaz.Estilo;
import fresh.interfaz.vistas.VistaNotificaciones;
import fresh.interfaz.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.util.List;

import javax.swing.JLabel;

public class ControladorNotificaciones {

    public ControladorNotificaciones(Sistema sistema, VistaNotificaciones vistaNotificaciones) {
        List<Notificacion> notificaciones = sistema.getUsuarioActual().getNotificaciones();

        cargarNotificaciones(sistema, vistaNotificaciones, notificaciones);
    }

    private void cargarNotificaciones(Sistema sistema, VistaNotificaciones vistaNotificaciones, List<Notificacion> notificaciones) {
        if (notificaciones.size() == 0) {
            vistaNotificaciones.scrollFrame.setVisible(false);
            vistaNotificaciones.textoSinNotificaciones.setVisible(true);
            return;
        }

        vistaNotificaciones.textoSinNotificaciones.setVisible(false);
        vistaNotificaciones.scrollFrame.setVisible(true);
        
        int tamanoTotal = 15+notificaciones.size()*100;
        
        vistaNotificaciones.scrollPanel.setPreferredSize(new Dimension(0, tamanoTotal));
        vistaNotificaciones.scrollFrame.revalidate();
        vistaNotificaciones.scrollFrame.repaint();

        int i = 0;
        for (Notificacion n : notificaciones) {            
            JLabel textoDescripcion1 = new JLabel();
            textoDescripcion1.setBounds(115, tamanoTotal-100*(i+1), 735, 40);
            textoDescripcion1.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDescripcion1.setForeground(Estilo.colorTexto);
            textoDescripcion1.setHorizontalAlignment(JLabel.LEFT);
            vistaNotificaciones.scrollPanel.add(textoDescripcion1);

            JLabel textoDescripcion2 = new JLabel();
            textoDescripcion2.setBounds(115, tamanoTotal-100*(i+1)+30, 735, 40);
            textoDescripcion2.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDescripcion2.setForeground(Estilo.colorTexto);
            textoDescripcion2.setHorizontalAlignment(JLabel.LEFT);
            vistaNotificaciones.scrollPanel.add(textoDescripcion2);            

            JCustomButton botonEliminar = new JCustomButton("✖");
            botonEliminar.setBounds(25, tamanoTotal-100*(i+1), 75, 75);
            botonEliminar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            botonEliminar.setForeground(Estilo.colorTexto);
            botonEliminar.setBackground(new Color(245, 100, 100));
            botonEliminar.setPressedBackgound(new Color(245, 100, 100).brighter());
            botonEliminar.setCornerRadius(80);
            botonEliminar.setHeight(5);       
            botonEliminar.setShadowSize(5);
            botonEliminar.setShadowOpacity(0.4f);
            vistaNotificaciones.scrollPanel.add(botonEliminar);

            if (n.getTipoNotificacion() == TipoNotificacion.CANCION_VALIDADA) {
                textoDescripcion1.setText("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\"");
                textoDescripcion2.setText("ha sido ha sido validada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_VALIDADA_EXPLICITA) {
                textoDescripcion1.setText("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\"");
                textoDescripcion2.setText("ha sido validada con contenido explícito.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_BLOQUEADA_TEMPORAL) {
                textoDescripcion1.setText("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\"");
                textoDescripcion2.setText("ha sido bloqueada temporalmente.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_DESBLOQUEADA) {
                textoDescripcion1.setText("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\"");
                textoDescripcion2.setText("ha sido desbloqueada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_ELIMINADA) {
                textoDescripcion1.setText("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\"");
                textoDescripcion2.setText("ha sido eliminada.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_RECHAZADA) {
                textoDescripcion1.setText("Tu canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\"");
                textoDescripcion2.setText("ha sido rechazada, actualízala en \"Mis canciones\".");
            } else if (n.getTipoNotificacion() == TipoNotificacion.CANCION_SEGUIDO) {
                textoDescripcion1.setText("El autor al que sigues \"" + ((NotificacionCancion) n).getCancion().getAutor().getNombre() + "\"");
                textoDescripcion2.setText("ha subido la canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\".");
            } else if (n.getTipoNotificacion() == TipoNotificacion.PREMIUM_CADUCADO) {
                textoDescripcion1.setText("Tu pase premium ha caducado.");
                textoDescripcion2.setText("Si no ha sido renovado puedes obtenerlo pagando la cuota.");
            } else if (n.getTipoNotificacion() == TipoNotificacion.PREMIUM_GRATUITO) {
                textoDescripcion1.setText("Has recibido el servicio premium de manera gratuita.");
                textoDescripcion2.setText("¡Te felicitamos por el éxito de tus canciones!");
            } else if (n.getTipoNotificacion() == TipoNotificacion.REPORTE_ACEPTADO) {
                textoDescripcion1.setText("La canción \"" + ((NotificacionCancion) n).getCancion().getNombre() + "\"");
                textoDescripcion2.setText("que reportaste ha sido bloqueada.");
            } else {
                throw new RuntimeException("Error en el tipo de la notificación.");
            }
		
            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.getUsuarioActual().eliminarNotificacion(n);
                    cargarNotificaciones(sistema, vistaNotificaciones, notificaciones);
                    vistaNotificaciones.repaint();
                }
            });

            i++;
        }
    }
}