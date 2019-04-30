package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;
import fresh.interfaz.vistas.VistaAdministrador;
import fresh.interfaz.vistas.VistaResultadoCanciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAdministrador {
	
	public ControladorAdministrador(Sistema sistema, VistaVentana vistaVentana) {
		VistaAdministrador vistaAdministrador = new VistaAdministrador();
        vistaVentana.add(vistaAdministrador);
        
        vistaAdministrador.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaAdministrador.panelActual != null) {
                	vistaAdministrador.remove(vistaAdministrador.panelActual);
                }
                
                VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones();
                vistaAdministrador.panelActual = vistaResultadoCanciones;
                vistaAdministrador.add(vistaResultadoCanciones);

                vistaAdministrador.repaint();
            }
        });
        
        vistaAdministrador.botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    vistaVentana.remove(vistaAdministrador);
                    @SuppressWarnings("unused")
                    ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);
                }
            }
        });

        vistaAdministrador.botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.retrocederCancion();
            }
        });

        vistaAdministrador.botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.reanudarCancion();
                vistaAdministrador.botonReproducir.setVisible(false);
                vistaAdministrador.botonParar.setVisible(true);
            }
        });

        vistaAdministrador.botonParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.pausarCancion();
                vistaAdministrador.botonParar.setVisible(false);
                vistaAdministrador.botonReproducir.setVisible(true);
            }
        });

        vistaAdministrador.botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema.avanzarCancion();
            }
        });
        
        vistaVentana.repaint();
        
	}

}
