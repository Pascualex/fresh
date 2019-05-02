package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.ListaReproduccion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaPlaylists;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPlaylists {

    public ControladorPlaylists(Sistema sistema, VistaPlaylists vistaPlaylists, VistaMenu vistaMenu) {
        cargarPlaylists(sistema, vistaPlaylists, vistaMenu);

        vistaPlaylists.botonCrearPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaPlaylists.entradaNombrePlaylist.getText().isEmpty()) {
                    return;
                }

                sistema.crearListaReproduccion(vistaPlaylists.entradaNombrePlaylist.getText());
                cargarPlaylists(sistema, vistaPlaylists, vistaMenu);
            }
        });
    }

    private void cargarPlaylists(Sistema sistema, VistaPlaylists vistaPlaylists, VistaMenu vistaMenu) {
        int numListasReproduccion = sistema.getUsuarioActual().getListasReproducion().size();
        vistaPlaylists.scrollPanel.setPreferredSize(new Dimension(0, numListasReproduccion*100));
        vistaPlaylists.scrollPanel.removeAll();
        vistaPlaylists.scrollFrame.revalidate();
        vistaPlaylists.scrollFrame.repaint();

        int i = 0;
        for (ListaReproduccion l : sistema.getUsuarioActual().getListasReproducion()) {
            JLabel textoDuracion;
            textoDuracion = new JLabel(String.valueOf(l.getDuracion()/60) + ":" + String.format("%02d", l.getDuracion()%60));
            textoDuracion.setBounds(115, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaPlaylists.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion;
            textoNombreCancion = new JLabel(l.getNombre());
            textoNombreCancion.setBounds(225, 15+100*i+20, 575, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaPlaylists.scrollPanel.add(textoNombreCancion);

            if (l.getBloqueado()) {
                i++;
                continue;
            }

            JCustomButton botonReproducir;
            botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(25, 15+100*i, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonReproducir.setForeground(Estilo.colorTexto);
            botonReproducir.setBackground(new Color(240, 240, 100));
            botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonReproducir.setCornerRadius(80);
            botonReproducir.setHeight(5);       
            botonReproducir.setShadowSize(5);
            botonReproducir.setShadowOpacity(0.4f);            
            vistaPlaylists.scrollPanel.add(botonReproducir);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vistaMenu.botonReproducir.setVisible(false);
                    vistaMenu.botonParar.setVisible(true);
                    sistema.reproducir(l);
                }
            });

            i++;
        }
    }
}