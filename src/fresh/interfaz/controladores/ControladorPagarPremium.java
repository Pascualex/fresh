package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaPagarPremium;

public class ControladorPagarPremium {
	
	public ControladorPagarPremium(Sistema sistema, VistaMenu vistaMenu, VistaPagarPremium vistaPagarPremium) {
		
		vistaPagarPremium.botonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaPagarPremium.entradaTarjeta.getText().isEmpty()) {
                    return;
                }
                
                Status status = sistema.pagarPremium(vistaPagarPremium.entradaTarjeta.getText());
                
                if (status == Status.OK) {
                	vistaPagarPremium.textoOK.setVisible(true);
                	vistaPagarPremium.panelMensaje.setVisible(true);
                	vistaPagarPremium.panelPagarPremium.setVisible(false);
                	vistaPagarPremium.panelMensaje.repaint();
                	
                	sistema.getUsuarioActual().setPremium(true);
                	vistaMenu.textoRegistradoUsuario.setVisible(false);
                	vistaMenu.textoPremiumUsuario.setVisible(true);
                	vistaMenu.repaint();
                	
                } else if (status == Status.OPERACION_INACCESIBLE) {
                	vistaPagarPremium.textoOperacionInvalida.setVisible(true);
                	vistaPagarPremium.panelMensaje.setVisible(true);
                	vistaPagarPremium.panelPagarPremium.setVisible(false);
                	vistaPagarPremium.panelMensaje.repaint();
                } else if (status == Status.YA_ES_PREMIUM) {
                	vistaPagarPremium.textoYaPremium.setVisible(true);
                	vistaPagarPremium.panelMensaje.setVisible(true);
                	vistaPagarPremium.panelPagarPremium.setVisible(false);
                	vistaPagarPremium.panelMensaje.repaint();
                } else if (status == Status.TARJETA_INVALIDA) {
                	vistaPagarPremium.textoTarjetaInvalida.setVisible(true);
                	vistaPagarPremium.panelMensaje.setVisible(true);
                	vistaPagarPremium.panelPagarPremium.setVisible(false);
                	vistaPagarPremium.panelMensaje.repaint();
                } else if (status == Status.FALLO_INTERNET) {
                	vistaPagarPremium.textoFalloInternet.setVisible(true);
                	vistaPagarPremium.panelMensaje.setVisible(true);
                	vistaPagarPremium.panelPagarPremium.setVisible(false);
                	vistaPagarPremium.panelMensaje.repaint();
                } else if (status == Status.PAGO_RECHAZADO) {
                	vistaPagarPremium.textoPagoRechazado.setVisible(true);
                	vistaPagarPremium.panelMensaje.setVisible(true);
                	vistaPagarPremium.panelPagarPremium.setVisible(false);
                	vistaPagarPremium.panelMensaje.repaint();
                } else {
                	vistaPagarPremium.textoError.setVisible(true);
                	vistaPagarPremium.panelMensaje.setVisible(true);
                	vistaPagarPremium.panelPagarPremium.setVisible(false);
                	vistaPagarPremium.panelMensaje.repaint();
                }
            }
        });
		
		vistaPagarPremium.botonVolver.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {       
            	
            	if (vistaPagarPremium.textoOK.isVisible()) {
            		vistaPagarPremium.remove(vistaPagarPremium.panelMensaje);
            		@SuppressWarnings("unused")
            		ControladorPagarPremium controladorPagarPremium = new ControladorPagarPremium(sistema, vistaMenu, vistaPagarPremium);
					return;  
            	} else {
            		vistaPagarPremium.textoError.setVisible(false);
            		vistaPagarPremium.textoErrorEntrada.setVisible(false);
            		vistaPagarPremium.textoFalloInternet.setVisible(false);
            		vistaPagarPremium.textoOK.setVisible(false);
            		vistaPagarPremium.textoOperacionInvalida.setVisible(false);
            		vistaPagarPremium.textoPagarPremium.setVisible(false);
            		vistaPagarPremium.textoPagoRechazado.setVisible(false);
            		vistaPagarPremium.textoTarjetaInvalida.setVisible(false);
            		vistaPagarPremium.textoYaPremium.setVisible(false);
            		vistaPagarPremium.panelMensaje.setVisible(false);
            		vistaPagarPremium.panelPagarPremium.setVisible(true);
            	}
            }
        });
	}

}
