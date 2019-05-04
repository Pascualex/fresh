package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAnonimo {
	public ControladorAnonimo (Sistema sistema, VistaVentana vistaVentana) {
		VistaAnonimo vistaAnonimo = new VistaAnonimo();
        vistaVentana.add(vistaAnonimo);
        
        vistaAnonimo.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = vistaAnonimo.entradaBusqueda.getText();
                if (entrada.length() == 0) return;

                if (vistaAnonimo.panelActual != null) {
                    vistaAnonimo.remove(vistaAnonimo.panelActual);
                    vistaAnonimo.panelActual = null;
                }

                if (vistaAnonimo.seleccionModoBusqueda.getSelectedIndex() == 0) {
                    //Canciones
                    VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones(entrada);
                    vistaAnonimo.panelActual = vistaResultadoCanciones;
                    vistaAnonimo.add(vistaResultadoCanciones);

                    @SuppressWarnings("unused")
                    ControladorResultadoCanciones controladorResultadoCanciones = new ControladorResultadoCanciones(sistema, vistaResultadoCanciones, entrada);
                
                    vistaResultadoCanciones.setVisible(true);
                } else if (vistaAnonimo.seleccionModoBusqueda.getSelectedIndex() == 1) {
                    //Álbumes
                    VistaResultadoAlbumes vistaResultadoAlbumes = new VistaResultadoAlbumes(entrada);
                    vistaAnonimo.panelActual = vistaResultadoAlbumes;
                    vistaAnonimo.add(vistaResultadoAlbumes);

                    @SuppressWarnings("unused")
                    ControladorResultadoAlbumes controladorResultadoAlbumes = new ControladorResultadoAlbumes(sistema, vistaResultadoAlbumes, entrada);
                
                    vistaResultadoAlbumes.setVisible(true);
                } else {
                    //Autores
                }

                vistaAnonimo.repaint();
            }
        });
        
        vistaAnonimo.botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaAnonimo);
                    @SuppressWarnings("unused")
                    ControladorRegistro controladorRegistro= new ControladorRegistro(sistema, vistaVentana);
                }
            }
        });
        
        vistaAnonimo.botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaAnonimo);
                    @SuppressWarnings("unused")
                    ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);
                }
            }
        });
        
        vistaAnonimo.botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.retrocederCancion();
            }
        });

        vistaAnonimo.botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reanudarCancion();
                vistaAnonimo.botonReproducir.setVisible(false);
                vistaAnonimo.botonParar.setVisible(true);
            }
        });

        vistaAnonimo.botonParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.pausarCancion();
                vistaAnonimo.botonParar.setVisible(false);
                vistaAnonimo.botonReproducir.setVisible(true);
            }
        });

        vistaAnonimo.botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.avanzarCancion();
            }
        });
        
        vistaVentana.repaint();
        
	}
	
}
