package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Album;
import fresh.datos.tipos.ListaReproduccion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaAnadirALista;
import fresh.interfaz.vistas.VistaResultadoAlbumes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

public class ControladorResultadoAlbumes {

    public ControladorResultadoAlbumes(Sistema sistema, VistaResultadoAlbumes vistaResultadoAlbumes, String entrada) {
        List<Album> albumes = sistema.buscarAlbumes(entrada);
        
        if (albumes.size() == 0) {
            vistaResultadoAlbumes.scrollFrame.setVisible(false);
            vistaResultadoAlbumes.textoSinResultados.setVisible(true);
        }

        cargarCanciones(sistema, vistaResultadoAlbumes, albumes);
    }

    private void cargarCanciones(Sistema sistema, VistaResultadoAlbumes vistaResultadoAlbumes, List<Album> albumes) {
        vistaResultadoAlbumes.scrollPanel.setPreferredSize(new Dimension(0, 15+albumes.size()*100));
        vistaResultadoAlbumes.scrollFrame.revalidate();
        vistaResultadoAlbumes.scrollFrame.repaint();

        int i = 0;
        for (Album a : albumes) {
            JLabel textoDuracion = new JLabel(String.valueOf(a.getDuracion()/60) + ":" + String.format("%02d", a.getDuracion()%60));
            textoDuracion.setBounds(185, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaResultadoAlbumes.scrollPanel.add(textoDuracion);

            JLabel textoNombreAlbum = new JLabel(a.getNombre());
            textoNombreAlbum.setBounds(295, 15+100*i, 505, 40);
            textoNombreAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreAlbum.setForeground(Estilo.colorTexto);
            textoNombreAlbum.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoAlbumes.scrollPanel.add(textoNombreAlbum);

            JLabel textoAnoYNombreAutor = new JLabel(a.getAno() + " - " + a.getAutor().getNombre());
            textoAnoYNombreAutor.setBounds(295, 15+100*i+35, 505, 40);
            textoAnoYNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoAnoYNombreAutor.setForeground(Estilo.colorTexto);
            textoAnoYNombreAutor.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoAlbumes.scrollPanel.add(textoAnoYNombreAutor);

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
            vistaResultadoAlbumes.scrollPanel.add(botonReproducir);

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
            vistaResultadoAlbumes.scrollPanel.add(botonAnadirPlaylist);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.reproducir(a);
                }
            });

            botonAnadirPlaylist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaAnadirALista vistaAnadirALista = new VistaAnadirALista("el álbum");
                    vistaResultadoAlbumes.add(vistaAnadirALista);
                    cargarPlaylists(sistema, vistaResultadoAlbumes, vistaAnadirALista, a);
                }
            });

            i++;
        }
    }

    private void cargarPlaylists(Sistema sistema, VistaResultadoAlbumes vistaResultadoAlbumes, VistaAnadirALista vistaAnadirALista, Album a) { 
        int numPlaylists = 0;

        int i = 0;
        for (ListaReproduccion l : sistema.getUsuarioActual().getListasReproducion()) {
            if (l.contieneElemento(a)) continue;

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
                    sistema.anadirAListaReproduccion(l, a);
                    vistaResultadoAlbumes.remove(vistaAnadirALista);

                    vistaResultadoAlbumes.separador.setVisible(true);
                    vistaResultadoAlbumes.scrollFrame.setVisible(true);
                    
                    vistaResultadoAlbumes.repaint();
                }
            });

            i++;
        }

        vistaAnadirALista.botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaResultadoAlbumes.remove(vistaAnadirALista);

                vistaResultadoAlbumes.separador.setVisible(true);
                vistaResultadoAlbumes.scrollFrame.setVisible(true);

                vistaResultadoAlbumes.repaint();
            }
        });

        vistaAnadirALista.scrollPanel.setPreferredSize(new Dimension(0, 15+numPlaylists*100));

        vistaResultadoAlbumes.separador.setVisible(false);
        vistaResultadoAlbumes.scrollFrame.setVisible(false);
        vistaAnadirALista.setVisible(true);

        vistaAnadirALista.scrollFrame.revalidate();
        vistaAnadirALista.repaint();
    }
}