package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.ListaReproduccion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaPlaylists;
import fresh.interfaz.vistas.VistaAnadirALista;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este controlador carga las listas de reproducción creadas por el usuario,
 * permitiéndole escucharlas, eliminarlas o añadirlas a otra lista de
 * reproducción.
 */
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
        
        if (numListasReproduccion == 0) {
            vistaPlaylists.scrollFrame.setVisible(false);
            vistaPlaylists.textoSinPlaylists.setVisible(true);
            return;
        }

        vistaPlaylists.textoSinPlaylists.setVisible(false);
        vistaPlaylists.scrollFrame.setVisible(true);
        
        vistaPlaylists.scrollPanel.setPreferredSize(new Dimension(0, numListasReproduccion*100));
        vistaPlaylists.scrollPanel.removeAll();
        vistaPlaylists.scrollFrame.revalidate();
        vistaPlaylists.scrollFrame.repaint();

        int i = 0;
        for (ListaReproduccion l : sistema.getUsuarioActual().getListasReproducion()) {
            JLabel textoDuracion = new JLabel(String.valueOf(l.getDuracion()/60) + ":" + String.format("%02d", l.getDuracion()%60));
            textoDuracion.setBounds(270, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaPlaylists.scrollPanel.add(textoDuracion);

            JLabel textoNombrePlayList = new JLabel(l.getNombre());
            textoNombrePlayList.setBounds(390, 15+100*i+20, 460, 40);
            textoNombrePlayList.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombrePlayList.setForeground(Estilo.colorTexto);
            textoNombrePlayList.setHorizontalAlignment(JLabel.LEFT);
            vistaPlaylists.scrollPanel.add(textoNombrePlayList);

            if (!l.getBloqueado()) {
                JCustomButton botonReproducir = new JCustomButton("▶");
                botonReproducir.setBounds(25, 15+100*i, 75, 75);
                botonReproducir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
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
            }

            JCustomButton botonAnadirPlaylist = new JCustomButton("🞦");
            botonAnadirPlaylist.setBounds(110, 15+100*i, 75, 75);
            botonAnadirPlaylist.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
            botonAnadirPlaylist.setForeground(Estilo.colorTexto);
            botonAnadirPlaylist.setBackground(new Color(10, 200, 90));
            botonAnadirPlaylist.setPressedBackgound(new Color(10, 200, 90).brighter());
            botonAnadirPlaylist.setCornerRadius(80);
            botonAnadirPlaylist.setHeight(5);       
            botonAnadirPlaylist.setShadowSize(5);
            botonAnadirPlaylist.setShadowOpacity(0.4f);
            vistaPlaylists.scrollPanel.add(botonAnadirPlaylist);

            botonAnadirPlaylist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaAnadirALista vistaAnadirALista = new VistaAnadirALista("esta playlist");
                    vistaPlaylists.add(vistaAnadirALista);
                    cargarAnadirAPlaylists(sistema, vistaPlaylists, vistaAnadirALista, l, vistaMenu);
                }
            });            

            JCustomButton botonEliminar = new JCustomButton("✖");
            botonEliminar.setBounds(195, 15+100*i, 75, 75);
            botonEliminar.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
            botonEliminar.setForeground(Estilo.colorTexto);
            botonEliminar.setBackground(new Color(224, 62, 98));
            botonEliminar.setPressedBackgound(new Color(224, 62, 98).brighter());
            botonEliminar.setCornerRadius(80);
            botonEliminar.setHeight(5);       
            botonEliminar.setShadowSize(5);
            botonEliminar.setShadowOpacity(0.4f);
            vistaPlaylists.scrollPanel.add(botonEliminar);

            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.eliminarListaReproduccion(l);
                    cargarPlaylists(sistema, vistaPlaylists, vistaMenu);
                    vistaPlaylists.scrollPanel.repaint();
                }
            });

            i++;
        }
    }

    private void cargarAnadirAPlaylists(Sistema sistema, VistaPlaylists vistaPlaylists, VistaAnadirALista vistaAnadirALista, ListaReproduccion l, VistaMenu vistaMenu) { 
        int numPlaylists = 0;

        int i = 0;
        for (ListaReproduccion la : sistema.getUsuarioActual().getListasReproducion()) {
            if (la.contieneElemento(l)) continue;

            numPlaylists++;

            JLabel nombrePlaylist = new JLabel(la.getNombre());
            nombrePlaylist.setBounds(115, 15+100*i+20, 685, 40);
            nombrePlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            nombrePlaylist.setForeground(Estilo.colorTexto);
            nombrePlaylist.setHorizontalAlignment(JLabel.LEFT);
            vistaAnadirALista.scrollPanel.add(nombrePlaylist);

            JCustomButton botonAnadir = new JCustomButton("🞦");
            botonAnadir.setBounds(25, 15+100*i, 75, 75);
            botonAnadir.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 25));
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
                    sistema.anadirAListaReproduccion(la, l);
                    vistaPlaylists.remove(vistaAnadirALista);

                    cargarPlaylists(sistema, vistaPlaylists, vistaMenu);

                    vistaPlaylists.entradaNombrePlaylist.setVisible(true);
                    vistaPlaylists.botonCrearPlaylist.setVisible(true);
                    vistaPlaylists.separador.setVisible(true);
                    vistaPlaylists.scrollFrame.setVisible(true);

                    vistaPlaylists.repaint();
                }
            });

            i++;
        }

        vistaAnadirALista.botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaPlaylists.remove(vistaAnadirALista);

                vistaPlaylists.entradaNombrePlaylist.setVisible(true);
                vistaPlaylists.botonCrearPlaylist.setVisible(true);
                vistaPlaylists.separador.setVisible(true);
                vistaPlaylists.scrollFrame.setVisible(true);
                
                vistaPlaylists.repaint();
            }
        });

        vistaAnadirALista.scrollPanel.setPreferredSize(new Dimension(0, 15+numPlaylists*100));
        
        vistaPlaylists.entradaNombrePlaylist.setVisible(false);
        vistaPlaylists.botonCrearPlaylist.setVisible(false);
        vistaPlaylists.separador.setVisible(false);
        vistaPlaylists.scrollFrame.setVisible(false);
        vistaAnadirALista.setVisible(true);

        vistaAnadirALista.scrollFrame.revalidate();
        vistaAnadirALista.repaint();
    }
}