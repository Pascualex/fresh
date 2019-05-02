package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

import fresh.datos.tipos.Cancion;
import fresh.datos.tipos.ListaReproduccion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaAnadirALista;
import fresh.interfaz.vistas.VistaResultadoCanciones;

public class ControladorResultadoCanciones {

    public ControladorResultadoCanciones(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, String entrada) {
        List<Cancion> canciones = sistema.buscarCanciones(entrada);

        vistaResultadoCanciones.scrollPanel.setPreferredSize(new Dimension(0, canciones.size()*100));

        int i = 0;
        for (Cancion c : canciones) {
            JLabel textoDuracion;
            textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
            textoDuracion.setBounds(200, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaResultadoCanciones.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion;
            textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(310, 15+100*i, 490, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoNombreAutor;
            textoNombreAutor = new JLabel(c.getAutor().getNombre());
            textoNombreAutor.setBounds(310, 15+100*i+35, 490, 40);
            textoNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoNombreAutor.setForeground(Estilo.colorTexto);
            textoNombreAutor.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoCanciones.scrollPanel.add(textoNombreAutor);

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

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.reproducir(c);
                }
            });
            vistaResultadoCanciones.scrollPanel.add(botonReproducir);

            JCustomButton botonAnadirPlaylist;
            botonAnadirPlaylist = new JCustomButton("+");
            botonAnadirPlaylist.setBounds(110, 15+100*i, 75, 75);
            botonAnadirPlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            botonAnadirPlaylist.setForeground(Estilo.colorTexto);
            botonAnadirPlaylist.setBackground(new Color(10, 200, 90));
            botonAnadirPlaylist.setPressedBackgound(new Color(10, 200, 90).brighter());
            botonAnadirPlaylist.setCornerRadius(80);
            botonAnadirPlaylist.setHeight(5);       
            botonAnadirPlaylist.setShadowSize(5);
            botonAnadirPlaylist.setShadowOpacity(0.4f);

            botonAnadirPlaylist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaAnadirALista vistaAnadirALista = new VistaAnadirALista();
                    int i = 0;
                    vistaResultadoCanciones.add(vistaAnadirALista);

                    for (ListaReproduccion l : sistema.getUsuarioActual().getListasReproducion()) {
                        JLabel nombrePlaylist;
                        nombrePlaylist = new JLabel(l.getNombre());
                        nombrePlaylist.setBounds(600, 100*i+25, 100, 40);
                        nombrePlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
                        nombrePlaylist.setForeground(Estilo.colorTexto);
                        nombrePlaylist.setHorizontalAlignment(JLabel.CENTER);
                        vistaAnadirALista.scrollPanel.add(nombrePlaylist);

                        JCustomButton botonAnadir;
                        botonAnadir = new JCustomButton("+");
                        botonAnadir.setBounds(25, 100*i, 75, 75);
                        botonAnadir.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
                        botonAnadir.setForeground(Estilo.colorTexto);
                        botonAnadir.setBackground(new Color(240, 240, 100));
                        botonAnadir.setPressedBackgound(new Color(220, 220, 95).brighter());
                        botonAnadir.setCornerRadius(80);
                        botonAnadir.setHeight(5);       
                        botonAnadir.setShadowSize(5);
                        botonAnadir.setShadowOpacity(0.4f);

                        botonAnadir.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                sistema.anadirAListaReproduccion(l, c);
                                vistaResultadoCanciones.remove(vistaAnadirALista);
                                vistaResultadoCanciones.scrollFrame.setVisible(false);
                                vistaResultadoCanciones.repaint();
                            }
                        });
                        vistaAnadirALista.scrollPanel.add(botonAnadir);
                        i++;
                    }

                    vistaResultadoCanciones.scrollFrame.setVisible(false);
                    vistaAnadirALista.setVisible(true);
                    vistaAnadirALista.repaint();
                }
            });
            vistaResultadoCanciones.scrollPanel.add(botonAnadirPlaylist);

            i++;
        }
    }
}