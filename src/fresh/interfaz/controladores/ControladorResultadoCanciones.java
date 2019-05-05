package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Cancion;
import fresh.datos.tipos.ListaReproduccion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaAnadirALista;
import fresh.interfaz.vistas.VistaReportar;
import fresh.interfaz.vistas.VistaResultadoCanciones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

public class ControladorResultadoCanciones {

    public ControladorResultadoCanciones(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, String entrada) {
        List<Cancion> canciones = sistema.buscarCanciones(entrada);
        
        if (canciones.size() == 0) {
            vistaResultadoCanciones.scrollFrame.setVisible(false);
            vistaResultadoCanciones.textoSinResultados.setVisible(true);
        }

        cargarCanciones(sistema, vistaResultadoCanciones, canciones);
    }

    private void cargarCanciones(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, List<Cancion> canciones) {
        vistaResultadoCanciones.scrollPanel.setPreferredSize(new Dimension(0, 15+canciones.size()*100));
        vistaResultadoCanciones.scrollFrame.revalidate();
        vistaResultadoCanciones.scrollFrame.repaint();

        int i = 0;
        for (Cancion c : canciones) {
            JLabel textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
            textoDuracion.setBounds(185, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaResultadoCanciones.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(295, 15+100*i, 485, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoNombreAutor = new JLabel(c.getAutor().getNombre());
            textoNombreAutor.setBounds(295, 15+100*i+35, 485, 40);
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

            JCustomButton botonAnadirPlaylist = new JCustomButton("+");
            botonAnadirPlaylist.setBounds(110, 15+100*i, 75, 75);
            botonAnadirPlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            botonAnadirPlaylist.setForeground(Estilo.colorTexto);
            botonAnadirPlaylist.setBackground(new Color(10, 200, 90));
            botonAnadirPlaylist.setPressedBackgound(new Color(10, 200, 90).brighter());
            botonAnadirPlaylist.setCornerRadius(80);
            botonAnadirPlaylist.setHeight(5);       
            botonAnadirPlaylist.setShadowSize(5);
            botonAnadirPlaylist.setShadowOpacity(0.4f);
            vistaResultadoCanciones.scrollPanel.add(botonAnadirPlaylist);

            JCustomButton botonReportar = new JCustomButton("R");
            botonReportar.setBounds(790, 15+100*i, 75, 75);
            botonReportar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            botonReportar.setForeground(Estilo.colorTexto);
            botonReportar.setBackground(new Color(245, 100, 100));
            botonReportar.setPressedBackgound(new Color(220, 50, 50).brighter());
            botonReportar.setCornerRadius(80);
            botonReportar.setHeight(5);       
            botonReportar.setShadowSize(5);
            botonReportar.setShadowOpacity(0.4f);
            vistaResultadoCanciones.scrollPanel.add(botonReportar);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.reproducir(c);
                }
            });

            botonAnadirPlaylist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaAnadirALista vistaAnadirALista = new VistaAnadirALista("la canción");
                    vistaResultadoCanciones.add(vistaAnadirALista);
                    cargarPlaylists(sistema, vistaResultadoCanciones, vistaAnadirALista, c);
                }
            });

            botonReportar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaReportar vistaReportar = new VistaReportar();
                    vistaResultadoCanciones.add(vistaReportar);
                    cargarReporte(sistema, vistaResultadoCanciones, vistaReportar, c);
                }
            });

            i++;
        }
    }

    private void cargarPlaylists(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, VistaAnadirALista vistaAnadirALista, Cancion c) {        
        int numPlaylists = 0;    

        int i = 0;
        for (ListaReproduccion l : sistema.getUsuarioActual().getListasReproducion()) {
            if (l.contieneElemento(c)) continue;

            numPlaylists++;

            JLabel nombrePlaylist = new JLabel(l.getNombre());
            nombrePlaylist.setBounds(115, 15+100*i+20, 685, 40);
            nombrePlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            nombrePlaylist.setForeground(Estilo.colorTexto);
            nombrePlaylist.setHorizontalAlignment(JLabel.LEFT);
            vistaAnadirALista.scrollPanel.add(nombrePlaylist);

            JCustomButton botonAnadir = new JCustomButton("+");
            botonAnadir.setBounds(25, 15+100*i, 75, 75);
            botonAnadir.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            botonAnadir.setForeground(Estilo.colorTexto);
            botonAnadir.setBackground(new Color(240, 240, 100));
            botonAnadir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonAnadir.setCornerRadius(80);
            botonAnadir.setHeight(5);       
            botonAnadir.setShadowSize(5);
            botonAnadir.setShadowOpacity(0.4f);
            vistaAnadirALista.scrollPanel.add(botonAnadir);

            botonAnadir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.anadirAListaReproduccion(l, c);
                    vistaResultadoCanciones.remove(vistaAnadirALista);
                    
                    vistaResultadoCanciones.separador.setVisible(true);
                    vistaResultadoCanciones.scrollFrame.setVisible(true);

                    vistaResultadoCanciones.repaint();
                }
            });

            i++;
        }

        vistaAnadirALista.botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaResultadoCanciones.remove(vistaAnadirALista);

                vistaResultadoCanciones.separador.setVisible(true);
                vistaResultadoCanciones.scrollFrame.setVisible(true);

                vistaResultadoCanciones.repaint();
            }
        });
        
        vistaAnadirALista.scrollPanel.setPreferredSize(new Dimension(0, 15+numPlaylists*100));

        vistaResultadoCanciones.separador.setVisible(false);
        vistaResultadoCanciones.scrollFrame.setVisible(false);
        vistaAnadirALista.setVisible(true);

        vistaAnadirALista.scrollFrame.revalidate();
        vistaAnadirALista.repaint();
    }

    private void cargarReporte(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, VistaReportar vistaReportar, Cancion c) {        

        vistaReportar.textoReportar.setText("Reportando " + c.getNombre() + " de " + c.getAutor().getNombre());

        vistaReportar.botonReportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reportar(vistaReportar.entradaDescripcion.getText(), c);
                vistaResultadoCanciones.remove(vistaReportar);
                    
                vistaResultadoCanciones.separador.setVisible(true);
                vistaResultadoCanciones.scrollFrame.setVisible(true);

                vistaResultadoCanciones.repaint();
            }
        });

        vistaResultadoCanciones.separador.setVisible(false);
        vistaResultadoCanciones.scrollFrame.setVisible(false);
        vistaReportar.setVisible(true);

        vistaReportar.repaint();
    }
}