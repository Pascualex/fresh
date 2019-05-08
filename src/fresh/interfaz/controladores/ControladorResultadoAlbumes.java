package fresh.interfaz.controladores;

import fresh.sistema.ModoEjecucion;
import fresh.sistema.Sistema;
import fresh.interfaz.Estilo;
import fresh.datos.tipos.*;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControladorResultadoAlbumes {

    public ControladorResultadoAlbumes(Sistema sistema, VistaResultadoAlbumes vistaResultadoAlbumes, JPanel vistaMenu, String entrada) {
        List<Album> albumes = sistema.buscarAlbumes(entrada);
        
        if (albumes.size() == 0) {
            vistaResultadoAlbumes.scrollFrame.setVisible(false);
            vistaResultadoAlbumes.textoSinResultados.setVisible(true);
        }

        cargarAlbumes(sistema, vistaResultadoAlbumes, vistaMenu, albumes);
    }

    private void cargarAlbumes(Sistema sistema, VistaResultadoAlbumes vistaResultadoAlbumes, JPanel vistaMenu, List<Album> albumes) {
        vistaResultadoAlbumes.scrollPanel.setPreferredSize(new Dimension(0, 15+albumes.size()*100));
        vistaResultadoAlbumes.scrollFrame.revalidate();
        
        int posTextoDuracion;
        if (sistema.getModoEjecucion() == ModoEjecucion.REGISTRADO) {
            posTextoDuracion = 275;
        } else {
            posTextoDuracion = 185;
        }

        int i = 0;
        for (Album a : albumes) {
            JLabel textoDuracion = new JLabel(String.valueOf(a.getDuracion()/60) + ":" + String.format("%02d", a.getDuracion()%60));
            textoDuracion.setBounds(posTextoDuracion, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaResultadoAlbumes.scrollPanel.add(textoDuracion);

            JLabel textoNombreAlbum = new JLabel(a.getNombre());
            textoNombreAlbum.setBounds(posTextoDuracion+110, 15+100*i, 850-posTextoDuracion-110, 40);
            textoNombreAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreAlbum.setForeground(Estilo.colorTexto);
            textoNombreAlbum.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoAlbumes.scrollPanel.add(textoNombreAlbum);

            JLabel textoAnoYNombreAutor = new JLabel(a.getAno() + " - " + a.getAutor().getNombre());
            textoAnoYNombreAutor.setBounds(posTextoDuracion+110, 15+100*i+35, 850-posTextoDuracion-110, 40);
            textoAnoYNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoAnoYNombreAutor.setForeground(Estilo.colorTexto);
            textoAnoYNombreAutor.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoAlbumes.scrollPanel.add(textoAnoYNombreAutor);

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
            vistaResultadoAlbumes.scrollPanel.add(botonReproducir);

            JCustomButton botonVerCanciones = new JCustomButton("🔎");
            botonVerCanciones.setBounds(110, 15+100*i, 75, 75);
            botonVerCanciones.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
            botonVerCanciones.setForeground(Estilo.colorTexto);
            botonVerCanciones.setBackground(new Color(240, 240, 100));
            botonVerCanciones.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonVerCanciones.setCornerRadius(80);
            botonVerCanciones.setHeight(5);       
            botonVerCanciones.setShadowSize(5);
            botonVerCanciones.setShadowOpacity(0.4f);
            vistaResultadoAlbumes.scrollPanel.add(botonVerCanciones);

            if (sistema.getModoEjecucion() == ModoEjecucion.REGISTRADO) {
                JCustomButton botonAnadirPlaylist = new JCustomButton("🞦");
                botonAnadirPlaylist.setBounds(195, 15+100*i, 75, 75);
                botonAnadirPlaylist.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
                botonAnadirPlaylist.setForeground(Estilo.colorTexto);
                botonAnadirPlaylist.setBackground(new Color(10, 200, 90));
                botonAnadirPlaylist.setPressedBackgound(new Color(10, 200, 90).brighter());
                botonAnadirPlaylist.setCornerRadius(80);
                botonAnadirPlaylist.setHeight(5);       
                botonAnadirPlaylist.setShadowSize(5);
                botonAnadirPlaylist.setShadowOpacity(0.4f);
                vistaResultadoAlbumes.scrollPanel.add(botonAnadirPlaylist);

                botonAnadirPlaylist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VistaAnadirALista vistaAnadirALista = new VistaAnadirALista("este álbum");
                        vistaResultadoAlbumes.add(vistaAnadirALista);
                        cargarPlaylists(sistema, vistaResultadoAlbumes, vistaAnadirALista, a);
                    }
                });
            }

            if (sistema.getModoEjecucion() == ModoEjecucion.REGISTRADO) {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((VistaMenu) vistaMenu).botonReproducir.setVisible(false);
                        ((VistaMenu) vistaMenu).botonParar.setVisible(true);
                        sistema.reproducir(a);
                    }
                });    
            } else if (sistema.getModoEjecucion() == ModoEjecucion.ADMINISTRADOR) {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((VistaMenuAdministrador) vistaMenu).botonReproducir.setVisible(false);
                        ((VistaMenuAdministrador) vistaMenu).botonParar.setVisible(true);
                        sistema.reproducir(a);
                    }
                });
            } else if (sistema.getModoEjecucion() == ModoEjecucion.ANONIMO) {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((VistaMenuAnonimo) vistaMenu).botonReproducir.setVisible(false);
                        ((VistaMenuAnonimo) vistaMenu).botonParar.setVisible(true);
                        sistema.reproducir(a);
                    }
                });
            } else {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sistema.reproducir(a);
                    }
                });
            }

            botonVerCanciones.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaCancionesDe vistaCancionesDe = new VistaCancionesDe("del álbum \"" + a.getNombre() + "\"");
                    cargarCanciones(sistema, vistaCancionesDe, vistaResultadoAlbumes, vistaMenu, a);
                    vistaResultadoAlbumes.add(vistaCancionesDe);

                    vistaResultadoAlbumes.separador.setVisible(false);
                    vistaResultadoAlbumes.scrollFrame.setVisible(false);
                    vistaCancionesDe.setVisible(true);

                    vistaResultadoAlbumes.repaint();
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

            JCustomButton botonAnadir = new JCustomButton("🞦");
            botonAnadir.setBounds(25, 15+100*i, 75, 75);
            botonAnadir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
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

    private void cargarCanciones(Sistema sistema, VistaCancionesDe vistaCancionesDe, VistaResultadoAlbumes vistaResultadoAlbumes, JPanel vistaMenu, Album a) {
        List<Cancion> canciones = a.getCanciones();

        if (canciones.size() == 0) {
            vistaCancionesDe.textoSinCanciones.setVisible(true);
        }

        vistaCancionesDe.scrollPanel.setPreferredSize(new Dimension(0, 15+canciones.size()*100));
        vistaCancionesDe.scrollFrame.revalidate();

        int posTextoDuracion;
        if (sistema.getModoEjecucion() == ModoEjecucion.REGISTRADO) {
            posTextoDuracion = 185;
        } else {
            posTextoDuracion = 100;
        }

        int i = 0;
        for (Cancion c : canciones) {
            JLabel textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
            textoDuracion.setBounds(posTextoDuracion, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaCancionesDe.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(posTextoDuracion+110, 15+100*i, 760-posTextoDuracion-100, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaCancionesDe.scrollPanel.add(textoNombreCancion);

            JLabel textoNombreAutor = new JLabel(c.getAutor().getNombre());
            textoNombreAutor.setBounds(posTextoDuracion+110, 15+100*i+35, 760-posTextoDuracion-100, 40);
            textoNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoNombreAutor.setForeground(Estilo.colorTexto);
            textoNombreAutor.setHorizontalAlignment(JLabel.LEFT);
            vistaCancionesDe.scrollPanel.add(textoNombreAutor);

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
            vistaCancionesDe.scrollPanel.add(botonReproducir);

            if (sistema.getModoEjecucion() == ModoEjecucion.REGISTRADO) {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((VistaMenu) vistaMenu).botonReproducir.setVisible(false);
                        ((VistaMenu) vistaMenu).botonParar.setVisible(true);
                        sistema.reproducir(c);
                    }
                });    
            } else if (sistema.getModoEjecucion() == ModoEjecucion.ADMINISTRADOR) {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((VistaMenuAdministrador) vistaMenu).botonReproducir.setVisible(false);
                        ((VistaMenuAdministrador) vistaMenu).botonParar.setVisible(true);
                        sistema.reproducir(c);
                    }
                });
            } else if (sistema.getModoEjecucion() == ModoEjecucion.ANONIMO) {
                botonReproducir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vistaMenu.remove(vistaResultadoAlbumes);
                        vistaMenu.repaint();
                        ((VistaMenuAnonimo) vistaMenu).botonReproducir.setVisible(false);
                        ((VistaMenuAnonimo) vistaMenu).botonParar.setVisible(true);
                        sistema.reproducir(c);
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

            if (sistema.getModoEjecucion() == ModoEjecucion.REGISTRADO) {
                JCustomButton botonAnadirPlaylist = new JCustomButton("🞦");
                botonAnadirPlaylist.setBounds(110, 15+100*i, 75, 75);
                botonAnadirPlaylist.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 25));
                botonAnadirPlaylist.setForeground(Estilo.colorTexto);
                botonAnadirPlaylist.setBackground(new Color(10, 200, 90));
                botonAnadirPlaylist.setPressedBackgound(new Color(10, 200, 90).brighter());
                botonAnadirPlaylist.setCornerRadius(80);
                botonAnadirPlaylist.setHeight(5);       
                botonAnadirPlaylist.setShadowSize(5);
                botonAnadirPlaylist.setShadowOpacity(0.4f);
                vistaCancionesDe.scrollPanel.add(botonAnadirPlaylist);
    
                JCustomButton botonReportar = new JCustomButton("⚠");
                botonReportar.setBounds(790, 15+100*i, 75, 75);
                botonReportar.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 30));
                botonReportar.setForeground(Estilo.colorTexto);
                botonReportar.setBackground(new Color(245, 100, 100));
                botonReportar.setPressedBackgound(new Color(220, 50, 50).brighter());
                botonReportar.setCornerRadius(80);
                botonReportar.setHeight(5);       
                botonReportar.setShadowSize(5);
                botonReportar.setShadowOpacity(0.4f);
                vistaCancionesDe.scrollPanel.add(botonReportar);

                botonAnadirPlaylist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VistaAnadirALista vistaAnadirALista = new VistaAnadirALista("esta canción");
                        vistaCancionesDe.add(vistaAnadirALista);
                        cargarPlaylistsCanciones(sistema, vistaCancionesDe, vistaAnadirALista, c);
                    }
                });
    
                botonReportar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VistaReportar vistaReportar = new VistaReportar();
                        vistaCancionesDe.add(vistaReportar);
                        cargarReporte(sistema, vistaCancionesDe, vistaReportar, c, vistaMenu, vistaResultadoAlbumes, a);
                    }
                });
            }

            i++;
        }

        vistaCancionesDe.botonVolver.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {

                vistaResultadoAlbumes.remove(vistaCancionesDe);
                vistaResultadoAlbumes.separador.setVisible(true);
                vistaResultadoAlbumes.scrollFrame.setVisible(true);

                vistaResultadoAlbumes.repaint();
            }
        });
    }

    private void cargarPlaylistsCanciones(Sistema sistema, VistaCancionesDe vistaCancionesDe, VistaAnadirALista vistaAnadirALista, Cancion c) {        
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
                    sistema.anadirAListaReproduccion(l, c);
                    vistaCancionesDe.remove(vistaAnadirALista);
                    
                    vistaCancionesDe.textoFuenteCanciones.setVisible(true);
                    vistaCancionesDe.botonVolver.setVisible(true);
                    vistaCancionesDe.separador.setVisible(true);
                    vistaCancionesDe.scrollFrame.setVisible(true);

                    vistaCancionesDe.repaint();
                }
            });

            i++;
        }

        vistaAnadirALista.botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaCancionesDe.remove(vistaAnadirALista);
                    
                vistaCancionesDe.textoFuenteCanciones.setVisible(true);
                vistaCancionesDe.botonVolver.setVisible(true);
                vistaCancionesDe.separador.setVisible(true);
                vistaCancionesDe.scrollFrame.setVisible(true);

                vistaCancionesDe.repaint();
            }
        });
        
        vistaAnadirALista.scrollPanel.setPreferredSize(new Dimension(0, 15+numPlaylists*100));

        vistaCancionesDe.textoFuenteCanciones.setVisible(false);
        vistaCancionesDe.botonVolver.setVisible(false);
        vistaCancionesDe.separador.setVisible(false);
        vistaCancionesDe.scrollFrame.setVisible(false);
        vistaAnadirALista.setVisible(true);

        vistaAnadirALista.scrollFrame.revalidate();
        vistaAnadirALista.repaint();
    }

    private void cargarReporte(Sistema sistema, VistaCancionesDe vistaCancionesDe, VistaReportar vistaReportar, Cancion c, JPanel vistaMenu, VistaResultadoAlbumes vistaResultadoAlbumes, Album a) {        
        vistaReportar.textoReportar.setText("Reportando " + c.getNombre() + " de " + c.getAutor().getNombre());

        vistaReportar.botonReportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reportar(vistaReportar.entradaDescripcion.getText(), c);
                cargarCanciones(sistema, vistaCancionesDe, vistaResultadoAlbumes, vistaMenu, a);

                vistaCancionesDe.remove(vistaReportar);

                vistaCancionesDe.textoFuenteCanciones.setVisible(true);    
                vistaCancionesDe.botonVolver.setVisible(true);                
                vistaCancionesDe.separador.setVisible(true);
                vistaCancionesDe.scrollFrame.setVisible(true);

                vistaCancionesDe.repaint();
            }
        });

        vistaReportar.botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaCancionesDe.remove(vistaReportar);

                vistaCancionesDe.textoFuenteCanciones.setVisible(true);
                vistaCancionesDe.botonVolver.setVisible(true);
                vistaCancionesDe.separador.setVisible(true);
                vistaCancionesDe.scrollFrame.setVisible(true);

                vistaCancionesDe.repaint();
            }
        });

        vistaCancionesDe.textoFuenteCanciones.setVisible(false);
        vistaCancionesDe.botonVolver.setVisible(false);
        vistaCancionesDe.separador.setVisible(false);
        vistaCancionesDe.scrollFrame.setVisible(false);
        vistaReportar.setVisible(true);

        vistaReportar.repaint();
    }
}