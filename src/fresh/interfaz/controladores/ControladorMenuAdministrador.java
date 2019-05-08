package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este controlador carga la funcionalidad del menú del administrador.
 */
public class ControladorMenuAdministrador {
	
	public ControladorMenuAdministrador(Sistema sistema, VistaVentana vistaVentana) {
		VistaMenuAdministrador vistaMenuAdministrador = new VistaMenuAdministrador();
        vistaVentana.add(vistaMenuAdministrador);
        
        vistaMenuAdministrador.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = vistaMenuAdministrador.entradaBusqueda.getText();
                if (entrada.length() == 0) return;

                if (vistaMenuAdministrador.panelActual != null) {
                    vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);
                    vistaMenuAdministrador.panelActual = null;
                }

                if (vistaMenuAdministrador.seleccionModoBusqueda.getSelectedIndex() == 0) {
                    // Canciones
                    VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones(entrada);
                    vistaMenuAdministrador.panelActual = vistaResultadoCanciones;
                    vistaMenuAdministrador.add(vistaResultadoCanciones);

                    @SuppressWarnings("unused")
                    ControladorResultadoCancionesNoPlaylist controladorResultadoCancionesNoPlaylist = new ControladorResultadoCancionesNoPlaylist(sistema, vistaResultadoCanciones, vistaMenuAdministrador, entrada);
                
                    vistaResultadoCanciones.setVisible(true);
                } else if (vistaMenuAdministrador.seleccionModoBusqueda.getSelectedIndex() == 1) {
                    // Álbumes
                    VistaResultadoAlbumes vistaResultadoAlbumes = new VistaResultadoAlbumes(entrada);
                    vistaMenuAdministrador.panelActual = vistaResultadoAlbumes;
                    vistaMenuAdministrador.add(vistaResultadoAlbumes);

                    @SuppressWarnings("unused")
                    ControladorResultadoAlbumes controladorResultadoAlbumes = new ControladorResultadoAlbumes(sistema, vistaResultadoAlbumes, vistaMenuAdministrador, entrada);
                
                    vistaResultadoAlbumes.setVisible(true);
                } else {
                    // Autores
                    VistaResultadoAutores vistaResultadoAutores = new VistaResultadoAutores(entrada);
                    vistaMenuAdministrador.panelActual = vistaResultadoAutores;
                    vistaMenuAdministrador.add(vistaResultadoAutores);

                    @SuppressWarnings("unused")
                    ControladorResultadoAutores controladorResultadoAutores = new ControladorResultadoAutores(sistema, vistaResultadoAutores, vistaMenuAdministrador, entrada);
                
                    vistaResultadoAutores.setVisible(true);
                }

                vistaMenuAdministrador.repaint();
            }
        });
        
        vistaMenuAdministrador.botonReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenuAdministrador.panelActual != null) {
                    vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                    vistaMenuAdministrador.panelActual = null;
                }
                
                VistaReportes vistaReportes = new VistaReportes();
                vistaMenuAdministrador.panelActual = vistaReportes;
                vistaMenuAdministrador.add(vistaReportes);

                @SuppressWarnings("unused")
                ControladorReportes controladorReportes = new ControladorReportes(sistema, vistaReportes, vistaMenuAdministrador);

                vistaReportes.setVisible(true);

                vistaMenuAdministrador.repaint();
            }
        });

        vistaMenuAdministrador.botonNuevasCanciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenuAdministrador.panelActual != null) {
                    vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                    vistaMenuAdministrador.panelActual = null;
                }
                
                VistaNuevasCanciones vistaNuevasCanciones = new VistaNuevasCanciones();
                vistaMenuAdministrador.panelActual = vistaNuevasCanciones;
                vistaMenuAdministrador.add(vistaNuevasCanciones);

                @SuppressWarnings("unused")
                ControladorNuevasCanciones controladorNuevasCanciones = new ControladorNuevasCanciones(sistema, vistaNuevasCanciones, vistaMenuAdministrador);

                vistaNuevasCanciones.setVisible(true);

                vistaMenuAdministrador.repaint();
            }
        });

        vistaMenuAdministrador.botonConfiguracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaMenuAdministrador.panelActual != null) {
                    vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                    vistaMenuAdministrador.panelActual = null;
                }
                
                VistaConfiguracion vistaConfiguracion = new VistaConfiguracion();
                vistaMenuAdministrador.panelActual = vistaConfiguracion;
                vistaMenuAdministrador.add(vistaConfiguracion);

                @SuppressWarnings("unused")
                ControladorConfiguracion controladorConfiguracion = new ControladorConfiguracion(sistema, vistaConfiguracion);

                vistaConfiguracion.setVisible(true);

                vistaMenuAdministrador.repaint();
            }
        });

        vistaMenuAdministrador.botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaMenuAdministrador);
                    @SuppressWarnings("unused")
                    ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);
                }
            }
        });

        vistaMenuAdministrador.botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.retrocederCancion();
            }
        });

        vistaMenuAdministrador.botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema.retrocederCancion() != Status.OK) return;
                vistaMenuAdministrador.botonReproducir.setVisible(false);
                vistaMenuAdministrador.botonParar.setVisible(true);
            }
        });

        vistaMenuAdministrador.botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema.reanudarCancion() != Status.OK) return;
                vistaMenuAdministrador.botonReproducir.setVisible(false);
                vistaMenuAdministrador.botonParar.setVisible(true);
            }
        });

        vistaMenuAdministrador.botonParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.pausarCancion();
                vistaMenuAdministrador.botonParar.setVisible(false);
                vistaMenuAdministrador.botonReproducir.setVisible(true);
            }
        });
        
        vistaVentana.repaint();
        
	}

}
