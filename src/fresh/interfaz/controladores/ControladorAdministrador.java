package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAdministrador {
	
	public ControladorAdministrador(Sistema sistema, VistaVentana vistaVentana) {
		VistaAdministrador vistaAdministrador = new VistaAdministrador();
        vistaVentana.add(vistaAdministrador);
        
        vistaAdministrador.botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entrada = vistaAdministrador.entradaBusqueda.getText();
                if (entrada.length() == 0) return;

                if (vistaAdministrador.panelActual != null) {
                    vistaAdministrador.remove(vistaAdministrador.panelActual);
                    vistaAdministrador.panelActual = null;
                }

                if (vistaAdministrador.seleccionModoBusqueda.getSelectedIndex() == 0) {
                    //Canciones
                    VistaResultadoCanciones vistaResultadoCanciones = new VistaResultadoCanciones(entrada);
                    vistaAdministrador.panelActual = vistaResultadoCanciones;
                    vistaAdministrador.add(vistaResultadoCanciones);

                    @SuppressWarnings("unused")
                    ControladorResultadoCanciones controladorResultadoCanciones = new ControladorResultadoCanciones(sistema, vistaResultadoCanciones, entrada);
                
                    vistaResultadoCanciones.setVisible(true);
                } else if (vistaAdministrador.seleccionModoBusqueda.getSelectedIndex() == 1) {
                    //Álbumes
                    VistaResultadoAlbumes vistaResultadoAlbumes = new VistaResultadoAlbumes(entrada);
                    vistaAdministrador.panelActual = vistaResultadoAlbumes;
                    vistaAdministrador.add(vistaResultadoAlbumes);

                    @SuppressWarnings("unused")
                    ControladorResultadoAlbumes controladorResultadoAlbumes = new ControladorResultadoAlbumes(sistema, vistaResultadoAlbumes, entrada);
                
                    vistaResultadoAlbumes.setVisible(true);
                } else {
                    //Autores
                }

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
