package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaAnonimo;
import fresh.interfaz.vistas.VistaResultadoCanciones;
import fresh.interfaz.vistas.VistaVentana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAnonimo {
	public ControladorAnonimo (Sistema sistema, VistaVentana vistaVentana) {
		VistaAnonimo vistaAnonimo = new VistaAnonimo();
        vistaVentana.add(vistaAnonimo);
        
        vistaAnonimo.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaAnonimo.panelActual != null) {
                	vistaAnonimo.remove(vistaAnonimo.panelActual);
                }
                
                VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones();
                vistaAnonimo.panelActual = vistaResultadoCanciones;
                vistaAnonimo.add(vistaResultadoCanciones);

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
