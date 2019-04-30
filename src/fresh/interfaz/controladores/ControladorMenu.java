package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;
import fresh.interfaz.vistas.VistaAutores;
import fresh.interfaz.vistas.VistaCanciones;
import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaNotificaciones;
import fresh.interfaz.vistas.VistaPlaylists;
import fresh.interfaz.vistas.VistaResultadoCanciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu {

    public ControladorMenu(Sistema sistema, VistaVentana vistaVentana) {
        VistaMenu vistaMenu = new VistaMenu();
        vistaVentana.add(vistaMenu);

        vistaMenu.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = vistaMenu.entradaBusqueda.getText();
                if (entrada.length() == 0) return;

                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                }

                if (vistaMenu.seleccionModoBusqueda.getSelectedIndex() == 0) {
                    //Canciones
                    VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones();
                    vistaMenu.panelActual = vistaResultadoCanciones;
                    vistaMenu.add(vistaResultadoCanciones);

                    @SuppressWarnings("unused")
                    ControladorResultadoCanciones controladorResultadoCanciones = new ControladorResultadoCanciones(sistema, vistaResultadoCanciones, entrada);
                
                    vistaResultadoCanciones.setVisible(true);
                } else if (vistaMenu.seleccionModoBusqueda.getSelectedIndex() == 1) {
                    //Álbumes
                } else {
                    //Autores
                }

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                }
                
                VistaPlaylists vistaPlaylists = new VistaPlaylists();
                vistaMenu.panelActual = vistaPlaylists;
                vistaMenu.add(vistaPlaylists);

                @SuppressWarnings("unused")
                ControladorPlaylists controladorPlaylists = new ControladorPlaylists(sistema, vistaPlaylists);

                vistaPlaylists.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                }
                
                VistaAutores vistaAutores = new VistaAutores();
                vistaMenu.panelActual = vistaAutores;
                vistaMenu.add(vistaAutores);

                @SuppressWarnings("unused")
                ControladorAutores controladorAutores = new ControladorAutores(sistema, vistaAutores);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonMisCanciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                }
                
                VistaCanciones vistaCanciones = new VistaCanciones();
                vistaMenu.panelActual = vistaCanciones;
                vistaMenu.add(vistaCanciones);

                @SuppressWarnings("unused")
                ControladorCanciones controladorCanciones = new ControladorCanciones(sistema, vistaCanciones);

                vistaCanciones.setVisible(true);

                vistaMenu.repaint();
            }
        });

        vistaMenu.botonNotificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenu.panelActual != null) {
                    vistaMenu.remove(vistaMenu.panelActual);
                }
                
                VistaNotificaciones VistaNotificaciones = new VistaNotificaciones();
                vistaMenu.panelActual = VistaNotificaciones;
                vistaMenu.add(VistaNotificaciones);
                
                @SuppressWarnings("unused")
                ControladorNotificaciones controladorNotificaciones = new ControladorNotificaciones(sistema, VistaNotificaciones);

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
                sistema.reanudarCancion();
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