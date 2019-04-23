package fresh.interfaz;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fresh.Status;
import fresh.sistema.Sistema;

public class ControladorRegistro {
	
	public ControladorRegistro(Sistema sistema, VistaVentana vistaVentana) {
		VistaRegistro vistaRegistro = new VistaRegistro();
		
		vistaRegistro.botonOK.addMouseListener(new MouseListener(){
	        
            @Override
            public void mouseReleased(MouseEvent e) {            
            }
        
            @Override
            public void mousePressed(MouseEvent e) {                
            }
        
            @Override
            public void mouseExited(MouseEvent e) {                
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {                
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (vistaRegistro.textoRegistroCorrecto.isVisible()) {
                	vistaVentana.remove(vistaRegistro);
                	@SuppressWarnings("unused")
					ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);
					return;                	
            	} else {
            		vistaRegistro.textoIntroduceUsuario.setVisible(false);
                	vistaRegistro.textoIntroduceAutor.setVisible(false);
                	vistaRegistro.textoIntroduceContrasena.setVisible(false);
                	vistaRegistro.textoIntroduceFecha.setVisible(false);
                	vistaRegistro.textoUsuarioExistente.setVisible(false);
                	vistaRegistro.textoUsuarioIncorrecto.setVisible(false);
                	vistaRegistro.textoNombreUsuarioIncorrecto.setVisible(false);
                	vistaRegistro.textoContrasenaIncorrecta.setVisible(false);
                	vistaRegistro.textoFechaIncorrecta.setVisible(false);
                	vistaRegistro.textoError.setVisible(false);
                	vistaRegistro.panelMensaje.setVisible(false);
                	vistaRegistro.panelRegistrarse.setVisible(true);
            	}
            }
        });
		
		vistaRegistro.botonRegistrarseInicio.addMouseListener(new MouseListener(){
	        
            @Override
            public void mouseReleased(MouseEvent e) {            
            }
        
            @Override
            public void mousePressed(MouseEvent e) {                
            }
        
            @Override
            public void mouseExited(MouseEvent e) {                
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {                
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vistaRegistro.entradaNombreInicio.getText().isEmpty()) {
                	vistaRegistro.textoIntroduceUsuario.setVisible(true);
                	vistaRegistro.textoIntroduceFecha.setVisible(false);
                	vistaRegistro.textoIntroduceContrasena.setVisible(false);
                	vistaRegistro.textoIntroduceAutor.setVisible(false);
                	vistaRegistro.panelMensaje.setVisible(true);
                	vistaRegistro.panelRegistrarse.setVisible(false);
                	vistaRegistro.panelMensaje.repaint();
                } else if (vistaRegistro.entradaNombreAutor.getText().isEmpty()) {
                	vistaRegistro.textoIntroduceAutor.setVisible(true);
                	vistaRegistro.textoIntroduceUsuario.setVisible(false);
                	vistaRegistro.textoIntroduceContrasena.setVisible(false);
                	vistaRegistro.textoIntroduceFecha.setVisible(false);
                	vistaRegistro.panelMensaje.setVisible(true);
                	vistaRegistro.panelRegistrarse.setVisible(false);
                	vistaRegistro.panelMensaje.repaint();
                } else if (vistaRegistro.entradaContrasenaInicio.getPassword().length == 0) {
                	vistaRegistro.textoIntroduceContrasena.setVisible(true);
                	vistaRegistro.textoIntroduceUsuario.setVisible(false);
                	vistaRegistro.textoIntroduceAutor.setVisible(false);
                	vistaRegistro.textoIntroduceFecha.setVisible(false);
                	vistaRegistro.panelMensaje.setVisible(true);
                	vistaRegistro.panelRegistrarse.setVisible(false);
                	vistaRegistro.panelMensaje.repaint();
                } else if (vistaRegistro.entradaFechaNacimientoDia.getText().isEmpty() ||
                			vistaRegistro.entradaFechaNacimientoMes.getText().isEmpty() ||
                			vistaRegistro.entradaFechaNacimientoAno.getText().isEmpty()) {
                	vistaRegistro.textoIntroduceFecha.setVisible(true);
                	vistaRegistro.textoIntroduceUsuario.setVisible(false);
                	vistaRegistro.textoIntroduceAutor.setVisible(false);
                	vistaRegistro.textoIntroduceContrasena.setVisible(false);
                	vistaRegistro.panelMensaje.setVisible(true);
                	vistaRegistro.panelRegistrarse.setVisible(false);
                	vistaRegistro.panelMensaje.repaint();
                } else {
                	int dia = Integer.parseInt(vistaRegistro.entradaFechaNacimientoDia.getText());
                	int mes = Integer.parseInt(vistaRegistro.entradaFechaNacimientoMes.getText());
                	int ano = Integer.parseInt(vistaRegistro.entradaFechaNacimientoAno.getText());
                	
                	Calendar c = new GregorianCalendar();
                	c.set(ano, mes, dia);
                	
                    Status status = sistema.registrarse(vistaRegistro.entradaNombreInicio.getText(), 
        													vistaRegistro.entradaNombreAutor.getText(), 
        														String.valueOf(vistaRegistro.entradaContrasenaInicio.getPassword()), 
        																c);
                    if (status == Status.OK) {
                    	//Pasa al login
                    	vistaRegistro.textoRegistroCorrecto.setVisible(true);
                    	vistaRegistro.panelMensaje.setVisible(true);
                    	vistaRegistro.panelRegistrarse.setVisible(false);
                    	vistaRegistro.panelMensaje.repaint();
                    } else if (status == Status.CONTRASENA_INVALIDA) {
                    	vistaRegistro.textoContrasenaIncorrecta.setVisible(true);
                    	vistaRegistro.panelMensaje.setVisible(true);
                    	vistaRegistro.panelRegistrarse.setVisible(false);
                    	vistaRegistro.panelMensaje.repaint();
                    } else if (status == Status.NOMBRE_INVALIDO) {
                    	vistaRegistro.textoUsuarioIncorrecto.setVisible(true);
                    	vistaRegistro.panelMensaje.setVisible(true);
                    	vistaRegistro.panelRegistrarse.setVisible(false);
                    	vistaRegistro.panelMensaje.repaint();
                    } else if (status == Status.NOMBRE_AUTOR_INVALIDO) {
                    	vistaRegistro.textoNombreUsuarioIncorrecto.setVisible(true);
                    	vistaRegistro.panelMensaje.setVisible(true);
                    	vistaRegistro.panelRegistrarse.setVisible(false);
                    	vistaRegistro.panelMensaje.repaint();
                    } else if (status == Status.EDAD_INVALIDA) {
                    	vistaRegistro.textoFechaIncorrecta.setVisible(true);
                    	vistaRegistro.panelMensaje.setVisible(true);
                    	vistaRegistro.panelRegistrarse.setVisible(false);
                    	vistaRegistro.panelMensaje.repaint();
                    } else if (status == Status.USUARIO_REPETIDO) {
                    	vistaRegistro.textoUsuarioExistente.setVisible(true);
                    	vistaRegistro.panelMensaje.setVisible(true);
                    	vistaRegistro.panelRegistrarse.setVisible(false);
                    	vistaRegistro.panelMensaje.repaint();
                    } else {
                    	vistaRegistro.textoError.setVisible(true);
                    	vistaRegistro.panelMensaje.setVisible(true);
                    	vistaRegistro.panelRegistrarse.setVisible(false);
                    	vistaRegistro.panelMensaje.repaint();
                    }
                }
            }
        });
		
	}

}
