package fresh.interfaz.controladores;

import fresh.sistema.ModoEjecucion;
import fresh.sistema.Sistema;
import fresh.datos.tipos.Cancion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaResultadoCanciones;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

public class ControladorResultadoCancionesNoPlaylist {

    public ControladorResultadoCancionesNoPlaylist(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, JPanel vistaMenu, String entrada) {
        List<Cancion> canciones = sistema.buscarCanciones(entrada);
        
        if (canciones.size() == 0) {
            vistaResultadoCanciones.scrollFrame.setVisible(false);
            vistaResultadoCanciones.textoSinResultados.setVisible(true);
        }

        cargarCanciones(sistema, vistaResultadoCanciones, vistaMenu, canciones);
    }

    private void cargarCanciones(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, JPanel vistaMenu, List<Cancion> canciones) {
        vistaResultadoCanciones.scrollPanel.setPreferredSize(new Dimension(0, 15+canciones.size()*100));
        vistaResultadoCanciones.scrollFrame.revalidate();
        vistaResultadoCanciones.scrollFrame.repaint();

        int i = 0;
        for (Cancion c : canciones) {
            JLabel textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
            textoDuracion.setBounds(115, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaResultadoCanciones.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(225, 15+100*i, 505, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoNombreAutor = new JLabel(c.getAutor().getNombre());
            textoNombreAutor.setBounds(225, 15+100*i+35, 505, 40);
            textoNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoNombreAutor.setForeground(Estilo.colorTexto);
            textoNombreAutor.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoCanciones.scrollPanel.add(textoNombreAutor);

            JCustomButton botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(25, 15+100*i, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonReproducir.setForeground(Estilo.colorTexto);
            botonReproducir.setBackground(new Color(240, 240, 100));
            botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonReproducir.setCornerRadius(80);
            botonReproducir.setHeight(5);       
            botonReproducir.setShadowSize(5);
            botonReproducir.setShadowOpacity(0.4f);
            vistaResultadoCanciones.scrollPanel.add(botonReproducir);

            if (sistema.getModoEjecucion() == ModoEjecucion.ANONIMO) {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sistema.reproducir(c);
                        vistaMenu.remove(vistaResultadoCanciones);
                        vistaMenu.repaint();
                    }
                });
            } else {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sistema.reproducir(c);
                    }
                });
            }

            i++;
        }
    }
}