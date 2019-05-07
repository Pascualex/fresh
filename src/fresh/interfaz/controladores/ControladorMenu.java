package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.Estilo;
import fresh.interfaz.vistas.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class ControladorMenu {

    public ControladorMenu(Sistema sistema, VistaVentana vistaVentana) {
        VistaMenu vistaMenu = new VistaMenu();
        vistaVentana.add(vistaMenu);
        
        JLabel textoAutor = new JLabel(sistema.getUsuarioActual().getNombre());
        textoAutor.setBounds(20, 550, 220, 80);
        textoAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 17));
        textoAutor.setForeground(Estilo.colorTexto);
        textoAutor.setHorizontalAlignment(JLabel.CENTER);
        vistaMenu.panelLateral.add(textoAutor);
        
        boolean usuarioPremium = sistema.getUsuarioActual().getPremium();
        
        if (usuarioPremium) {
        	vistaMenu.textoRegistradoUsuario.setVisible(false);
        	vistaMenu.textoPremiumUsuario.setVisible(true);
        	vistaMenu.botonPagarPremium.setVisible(false);
        }else {
        	vistaMenu.textoRegistradoUsuario.setVisible(true);
        	vistaMenu.textoPremiumUsuario.setVisible(false);
        	vistaMenu.botonPagarPremium.setVisible(true);
        }
        
        vistaMenu.seleccionModoBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;

                    vistaMenu.repaint();
                }
            }
        });

        vistaMenu.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = vistaMenu.entradaBusqueda.getText();
                if (entrada.length() == 0) return;

                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }

                if (vistaMenu.seleccionModoBusqueda.getSelectedIndex() == 0) {
                    //Canciones
                    VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones(entrada);
                    vistaMenu.panelActual = vistaResultadoCanciones;
                    vistaMenu.add(vistaResultadoCanciones);

                    @SuppressWarnings("unused")
                    ControladorResultadoCanciones controladorResultadoCanciones = new ControladorResultadoCanciones(sistema, vistaResultadoCanciones, entrada);
                
                    vistaResultadoCanciones.setVisible(true);
                } else if (vistaMenu.seleccionModoBusqueda.getSelectedIndex() == 1) {
                    //Álbumes
                    VistaResultadoAlbumes vistaResultadoAlbumes = new VistaResultadoAlbumes(entrada);
                    vistaMenu.panelActual = vistaResultadoAlbumes;
                    vistaMenu.add(vistaResultadoAlbumes);

                    @SuppressWarnings("unused")
                    ControladorResultadoAlbumes controladorResultadoAlbumes = new ControladorResultadoAlbumes(sistema, vistaResultadoAlbumes, entrada);
                
                    vistaResultadoAlbumes.setVisible(true);
                } else {
                    //Autores
                    VistaResultadoAutores vistaResultadoAutores = new VistaResultadoAutores(entrada);
                    vistaMenu.panelActual = vistaResultadoAutores;
                    vistaMenu.add(vistaResultadoAutores);

                    @SuppressWarnings("unused")
                    ControladorResultadoAutores controladorResultadoAutores = new ControladorResultadoAutores(sistema, vistaResultadoAutores, entrada);
                
                    vistaResultadoAutores.setVisible(true);
                }

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);                    
                    vistaMenu.panelActual = null;
                }
                
                VistaPlaylists vistaPlaylists = new VistaPlaylists();
                vistaMenu.panelActual = vistaPlaylists;
                vistaMenu.add(vistaPlaylists);

                @SuppressWarnings("unused")
                ControladorPlaylists controladorPlaylists = new ControladorPlaylists(sistema, vistaPlaylists, vistaMenu);

                vistaPlaylists.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
                
                VistaAutores vistaAutores = new VistaAutores();
                vistaMenu.panelActual = vistaAutores;
                vistaMenu.add(vistaAutores);

                @SuppressWarnings("unused")
                ControladorAutores controladorAutores = new ControladorAutores(sistema, vistaAutores, vistaMenu);

                vistaAutores.setVisible(true);
                
                vistaMenu.repaint();
            }
        });

        vistaMenu.botonMisCanciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
                
                VistaCanciones vistaCanciones = new VistaCanciones();
                vistaMenu.panelActual = vistaCanciones;
                vistaMenu.add(vistaCanciones);

                @SuppressWarnings("unused")
                ControladorCanciones controladorCanciones = new ControladorCanciones(sistema, vistaCanciones, vistaMenu);

                vistaCanciones.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonMisAlbumes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
                
                VistaAlbumes vistaAlbumes = new VistaAlbumes();
                vistaMenu.panelActual = vistaAlbumes;
                vistaMenu.add(vistaAlbumes);

                @SuppressWarnings("unused")
                ControladorAlbumes controladorAlbumes = new ControladorAlbumes(sistema, vistaAlbumes, vistaMenu);

                vistaAlbumes.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonNotificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
                
                VistaNotificaciones VistaNotificaciones = new VistaNotificaciones();
                vistaMenu.panelActual = VistaNotificaciones;
                vistaMenu.add(VistaNotificaciones);
                
                @SuppressWarnings("unused")
                ControladorNotificaciones controladorNotificaciones = new ControladorNotificaciones(sistema, VistaNotificaciones);

                VistaNotificaciones.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonPagarPremium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                    vistaMenu.panelActual = null;
                }
            	
            	 VistaPagarPremium vistaPagarPremium = new VistaPagarPremium();
                 vistaMenu.panelActual = vistaPagarPremium;
                 vistaMenu.add(vistaPagarPremium);
                 
                 @SuppressWarnings("unused")
                 ControladorPagarPremium controladorPagarPremium = new ControladorPagarPremium(sistema, vistaVentana, vistaMenu, vistaPagarPremium);

                 vistaPagarPremium.setVisible(true);
                 vistaMenu.repaint();

            }
        });

        vistaMenu.botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaMenu);
                    @SuppressWarnings("unused")
                    ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);
                }
            }
        });

        vistaMenu.botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.retrocederCancion();
                vistaMenu.botonReproducir.setVisible(false);
                vistaMenu.botonParar.setVisible(true);
            }
        });

        vistaMenu.botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema.reanudarCancion() != Status.OK) return;
                vistaMenu.botonReproducir.setVisible(false);
                vistaMenu.botonParar.setVisible(true);
            }
        });

        vistaMenu.botonParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.pausarCancion();
                vistaMenu.botonParar.setVisible(false);
                vistaMenu.botonReproducir.setVisible(true);
            }
        });

        vistaMenu.botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.avanzarCancion();
                vistaMenu.botonReproducir.setVisible(false);
                vistaMenu.botonParar.setVisible(true);
            }
        });
        
        vistaVentana.repaint();
    }
}