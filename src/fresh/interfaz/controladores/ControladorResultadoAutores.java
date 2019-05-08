package fresh.interfaz.controladores;

import fresh.sistema.ModoEjecucion;
import fresh.sistema.Sistema;
import fresh.interfaz.Estilo;
import fresh.datos.tipos.*;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorResultadoAutores {

    public ControladorResultadoAutores(Sistema sistema, VistaResultadoAutores vistaResultadoAutores, JPanel vistaMenu, String entrada) {
        List<Usuario> autores = sistema.buscarAutores(entrada);
        
        if (autores.size() == 0) {
            vistaResultadoAutores.scrollFrame.setVisible(false);
            vistaResultadoAutores.textoSinResultados.setVisible(true);
        }

        cargarAutores(sistema, vistaResultadoAutores, vistaMenu, autores);
    }

    private void cargarAutores(Sistema sistema, VistaResultadoAutores vistaResultadoAutores, JPanel vistaMenu, List<Usuario> autores) {
        vistaResultadoAutores.scrollPanel.setPreferredSize(new Dimension(0, 15+autores.size()*100));
        vistaResultadoAutores.scrollFrame.revalidate();

        int i = 0;
        for (Usuario a : autores) {
            JLabel textoNombre = new JLabel(a.getNombreAutor());
            textoNombre.setBounds(200, 15+100*i+20, 650, 40);
            textoNombre.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombre.setForeground(Estilo.colorTexto);
            textoNombre.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoAutores.scrollPanel.add(textoNombre);             

            JCustomButton botonSeguir = new JCustomButton("★");
            botonSeguir.setBounds(25, 15+100*i, 75, 75);
            botonSeguir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
            botonSeguir.setForeground(Estilo.colorTexto);
            botonSeguir.setBackground(new Color(240, 240, 100));
            botonSeguir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonSeguir.setCornerRadius(80);
            botonSeguir.setHeight(5);       
            botonSeguir.setShadowSize(5);
            botonSeguir.setShadowOpacity(0.4f);
            vistaResultadoAutores.scrollPanel.add(botonSeguir);

            JCustomButton botonNoSeguir = new JCustomButton("✖");
            botonNoSeguir.setBounds(25, 15+100*i, 75, 75);
            botonNoSeguir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
            botonNoSeguir.setForeground(Estilo.colorTexto);
            botonNoSeguir.setBackground(new Color(245, 100, 100));
            botonNoSeguir.setPressedBackgound(new Color(220, 50, 50).brighter());
            botonNoSeguir.setCornerRadius(80);
            botonNoSeguir.setHeight(5);       
            botonNoSeguir.setShadowSize(5);
            botonNoSeguir.setShadowOpacity(0.4f);
            vistaResultadoAutores.scrollPanel.add(botonNoSeguir);

            if (sistema.getModoEjecucion() == ModoEjecucion.REGISTRADO && !sistema.getUsuarioActual().getAutoresSeguidos().contains(a)) {
                botonNoSeguir.setVisible(false);
            } else {
                botonSeguir.setVisible(false);
            }

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
            vistaResultadoAutores.scrollPanel.add(botonVerCanciones);

            botonSeguir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.seguirAutor(a);
                    botonSeguir.setVisible(false);
                    botonNoSeguir.setVisible(true);
                }
            });

            botonNoSeguir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.noSeguirAutor(a);
                    botonNoSeguir.setVisible(false);
                    botonSeguir.setVisible(true);
                }
            });

            botonVerCanciones.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaCancionesDe vistaCancionesDe = new VistaCancionesDe("de " + a.getNombreAutor());
                    cargarCanciones(sistema, vistaCancionesDe, vistaResultadoAutores, vistaMenu, a);
                    vistaResultadoAutores.add(vistaCancionesDe);

                    vistaResultadoAutores.separador.setVisible(false);
                    vistaResultadoAutores.scrollFrame.setVisible(false);
                    vistaCancionesDe.setVisible(true);

                    vistaResultadoAutores.repaint();
                }
            });

            i++;
        }
    }

    private void cargarCanciones(Sistema sistema, VistaCancionesDe vistaCancionesDe, VistaResultadoAutores vistaResultadoAutores, JPanel vistaMenu, Usuario a) {
        List<Cancion> canciones = a.getCancionesValidas();

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
                        vistaMenu.remove(vistaResultadoAutores);
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
                        cargarPlaylists(sistema, vistaCancionesDe, vistaAnadirALista, c);
                    }
                });
    
                botonReportar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VistaReportar vistaReportar = new VistaReportar();
                        vistaCancionesDe.add(vistaReportar);
                        cargarReporte(sistema, vistaCancionesDe, vistaReportar, c, vistaMenu, vistaResultadoAutores, a);
                    }
                });
            }

            i++;
        }

        vistaCancionesDe.botonVolver.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {

                vistaResultadoAutores.remove(vistaCancionesDe);
                vistaResultadoAutores.separador.setVisible(true);
                vistaResultadoAutores.scrollFrame.setVisible(true);

                vistaResultadoAutores.repaint();
            }
        });
    }

    private void cargarPlaylists(Sistema sistema, VistaCancionesDe vistaCancionesDe, VistaAnadirALista vistaAnadirALista, Cancion c) {        
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

    private void cargarReporte(Sistema sistema, VistaCancionesDe vistaCancionesDe, VistaReportar vistaReportar, Cancion c, JPanel vistaMenu, VistaResultadoAutores vistaResultadoAutores, Usuario a) {        
        vistaReportar.textoReportar.setText("Reportando " + c.getNombre() + " de " + c.getAutor().getNombre());

        vistaReportar.botonReportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reportar(vistaReportar.entradaDescripcion.getText(), c);
                cargarCanciones(sistema, vistaCancionesDe, vistaResultadoAutores, vistaMenu, a);

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