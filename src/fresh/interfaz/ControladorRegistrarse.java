package fresh.interfaz;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fresh.Status;
import fresh.sistema.Sistema;

public class ControladorRegistrarse {
	
	InterfazRegistrarse interfazRegistrarse = new InterfazRegistrarse();
	private final Sistema sistema;
	
	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		Calendar c = new GregorianCalendar();
    	c.set(1970, 1, 1);
    	System.out.println(sistema.registrarse("usuario1", "nombre1", "123456", c));
    	ControladorRegistrarse controladorRegistrarse = new ControladorRegistrarse(sistema);
	}
	
	public ControladorRegistrarse(Sistema sistema) {
		this.sistema = sistema;
		
		interfazRegistrarse.botonCerrarAplicacion.addMouseListener(new MouseListener(){
	        
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
            	interfazRegistrarse.dispose();
            }
        });
		
		interfazRegistrarse.botonMinimizarAplicacion.addMouseListener(new MouseListener(){
	        
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
            	interfazRegistrarse.setState(Frame.ICONIFIED);
            }
        });
		
		interfazRegistrarse.botonOK.addMouseListener(new MouseListener(){
	        
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
            	interfazRegistrarse.textoIntroduceUsuario.setVisible(false);
            	interfazRegistrarse.textoIntroduceContrasena.setVisible(false);
            	interfazRegistrarse.textoUsuarioExistente.setVisible(false);
            	interfazRegistrarse.textoContrasenaIncorrecta.setVisible(false);
            	interfazRegistrarse.textoError.setVisible(false);
            	interfazRegistrarse.panelMensaje.setVisible(false);
            	interfazRegistrarse.panelRegistrarse.setVisible(true);
            }
        });
		
		interfazRegistrarse.botonRegistrarseInicio.addMouseListener(new MouseListener(){
	        
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
                if (interfazRegistrarse.entradaNombreInicio.getText().isEmpty()) {
                	interfazRegistrarse.textoIntroduceUsuario.setVisible(true);
                	interfazRegistrarse.textoIntroduceFecha.setVisible(false);
                	interfazRegistrarse.textoIntroduceContrasena.setVisible(false);
                	interfazRegistrarse.textoIntroduceAutor.setVisible(false);
                	interfazRegistrarse.panelMensaje.setVisible(true);
                	interfazRegistrarse.panelRegistrarse.setVisible(false);
                	interfazRegistrarse.panelMensaje.repaint();
                } else if (interfazRegistrarse.entradaNombreAutor.getText().isEmpty()) {
                	interfazRegistrarse.textoIntroduceAutor.setVisible(true);
                	interfazRegistrarse.textoIntroduceUsuario.setVisible(false);
                	interfazRegistrarse.textoIntroduceContrasena.setVisible(false);
                	interfazRegistrarse.textoIntroduceFecha.setVisible(false);
                	interfazRegistrarse.panelMensaje.setVisible(true);
                	interfazRegistrarse.panelRegistrarse.setVisible(false);
                	interfazRegistrarse.panelMensaje.repaint();
                } else if (interfazRegistrarse.entradaContrasenaInicio.getPassword().length == 0) {
                	interfazRegistrarse.textoIntroduceContrasena.setVisible(true);
                	interfazRegistrarse.textoIntroduceUsuario.setVisible(false);
                	interfazRegistrarse.textoIntroduceAutor.setVisible(false);
                	interfazRegistrarse.textoIntroduceFecha.setVisible(false);
                	interfazRegistrarse.panelMensaje.setVisible(true);
                	interfazRegistrarse.panelRegistrarse.setVisible(false);
                	interfazRegistrarse.panelMensaje.repaint();
                } else if (interfazRegistrarse.entradaFechaNacimientoDia.getText().isEmpty() ||
                			interfazRegistrarse.entradaFechaNacimientoMes.getText().isEmpty() ||
                			interfazRegistrarse.entradaFechaNacimientoAno.getText().isEmpty()) {
                	interfazRegistrarse.textoIntroduceFecha.setVisible(true);
                	interfazRegistrarse.textoIntroduceUsuario.setVisible(false);
                	interfazRegistrarse.textoIntroduceAutor.setVisible(false);
                	interfazRegistrarse.textoIntroduceContrasena.setVisible(false);
                	interfazRegistrarse.panelMensaje.setVisible(true);
                	interfazRegistrarse.panelRegistrarse.setVisible(false);
                	interfazRegistrarse.panelMensaje.repaint();
                }else {
                	int dia = Integer.parseInt(interfazRegistrarse.entradaFechaNacimientoDia.getText());
                	int mes = Integer.parseInt(interfazRegistrarse.entradaFechaNacimientoMes.getText());
                	int ano = Integer.parseInt(interfazRegistrarse.entradaFechaNacimientoAno.getText());
                	
                	Calendar c = new GregorianCalendar();
                	c.set(ano, mes, dia);
                	
                    Status status = sistema.registrarse(interfazRegistrarse.entradaNombreInicio.getText(), 
        													interfazRegistrarse.entradaNombreAutor.getText(), 
        														String.valueOf(interfazRegistrarse.entradaContrasenaInicio.getPassword()), 
        																c);
                    if (status == Status.OK) {
                    	//Pasa al menu
                    	interfazRegistrarse.dispose();
                    	ControladorMenu controladorMenu = new ControladorMenu(sistema);
                    } else if (status == Status.CONTRASENA_INVALIDA) {
                    	interfazRegistrarse.textoContrasenaIncorrecta.setVisible(true);
                    	interfazRegistrarse.panelMensaje.setVisible(true);
                    	interfazRegistrarse.panelRegistrarse.setVisible(false);
                    	interfazRegistrarse.panelMensaje.repaint();
                    } else if (status == Status.NOMBRE_INVALIDO) {
                    	interfazRegistrarse.textoUsuarioIncorrecto.setVisible(true);
                    	interfazRegistrarse.panelMensaje.setVisible(true);
                    	interfazRegistrarse.panelRegistrarse.setVisible(false);
                    	interfazRegistrarse.panelMensaje.repaint();
                    } else if (status == Status.NOMBRE_AUTOR_INVALIDO) {
                    	interfazRegistrarse.textoNombreUsuarioIncorrecto.setVisible(true);
                    	interfazRegistrarse.panelMensaje.setVisible(true);
                    	interfazRegistrarse.panelRegistrarse.setVisible(false);
                    	interfazRegistrarse.panelMensaje.repaint();
                    } else if (status == Status.EDAD_INVALIDA) {
                    	interfazRegistrarse.textoFechaIncorrecta.setVisible(true);
                    	interfazRegistrarse.panelMensaje.setVisible(true);
                    	interfazRegistrarse.panelRegistrarse.setVisible(false);
                    	interfazRegistrarse.panelMensaje.repaint();
                    } else if (status == Status.USUARIO_REPETIDO) {
                    	interfazRegistrarse.textoUsuarioExistente.setVisible(true);
                    	interfazRegistrarse.panelMensaje.setVisible(true);
                    	interfazRegistrarse.panelRegistrarse.setVisible(false);
                    	interfazRegistrarse.panelMensaje.repaint();
                    } else {
                    	interfazRegistrarse.textoError.setVisible(true);
                    	interfazRegistrarse.panelMensaje.setVisible(true);
                    	interfazRegistrarse.panelRegistrarse.setVisible(false);
                    	interfazRegistrarse.panelMensaje.repaint();
                    }
                }
            }
        });
		
	}

}
