package fresh.interfaz;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fresh.Status;
import fresh.sistema.Sistema;

public class ControladorLogin {
    InterfazLogin interfazLogin = new InterfazLogin();
    private final Sistema sistema;

    public static void main(String[] args) {
    	Sistema sistema = new Sistema();
    	Calendar c = new GregorianCalendar();
    	c.set(1970, 1, 1);
    	System.out.println(sistema.registrarse("usuario1", "nombre1", "123456", c));
        ControladorLogin controladorLogin = new ControladorLogin(sistema);
    }

    public ControladorLogin(Sistema sistema) {
        this.sistema = sistema;
        interfazLogin.botonCerrarAplicacion.addMouseListener(new MouseListener(){
        
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
                interfazLogin.dispose();
            }
        });

        interfazLogin.botonMinimizarAplicacion.addMouseListener(new MouseListener(){
        
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
                interfazLogin.setState(Frame.ICONIFIED);
            }
        });

        interfazLogin.botonOK.addMouseListener(new MouseListener(){
        
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
                interfazLogin.textoIntroduceUsuario.setVisible(false);
                interfazLogin.textoIntroduceContrasena.setVisible(false);
                interfazLogin.textoUsuarioIncorrecto.setVisible(false);
                interfazLogin.textoUsuarioBloqueado.setVisible(false);
                interfazLogin.textoContrasenaIncorrecta.setVisible(false);
                interfazLogin.textoError.setVisible(false);
                interfazLogin.panelMensaje.setVisible(false);
                interfazLogin.panelInicioSesion.setVisible(true);
            }
        });

        interfazLogin.botonIniciarSesion.addMouseListener(new MouseListener(){
        
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
                if (interfazLogin.entradaNombreInicio.getText().isEmpty()) {
                    interfazLogin.textoIntroduceUsuario.setVisible(true);
                    interfazLogin.panelMensaje.setVisible(true);
                    interfazLogin.panelInicioSesion.setVisible(false);
                    interfazLogin.panelMensaje.repaint();
                } else if (interfazLogin.entradaContrasenaInicio.getPassword().length == 0) {
                    interfazLogin.textoIntroduceContrasena.setVisible(true);
                    interfazLogin.panelMensaje.setVisible(true);
                    interfazLogin.panelInicioSesion.setVisible(false);
                    interfazLogin.panelMensaje.repaint();
                } else {
                    Status status = sistema.iniciarSesion(interfazLogin.entradaNombreInicio.getText(), 
                    							String.valueOf(interfazLogin.entradaContrasenaInicio.getPassword()));
                    if (status == Status.OK) {
                    	//Pasa al menu
                    	interfazLogin.dispose();
                    	ControladorMenu controladorMenu = new ControladorMenu(sistema);
                    } else if (status == Status.CONTRASENA_INVALIDA) {
                    	interfazLogin.textoContrasenaIncorrecta.setVisible(true);
                        interfazLogin.panelMensaje.setVisible(true);
                        interfazLogin.panelInicioSesion.setVisible(false);
                        interfazLogin.panelMensaje.repaint();
                    } else if (status == Status.NOMBRE_INVALIDO) {
                    	interfazLogin.textoUsuarioIncorrecto.setVisible(true);
                        interfazLogin.panelMensaje.setVisible(true);
                        interfazLogin.panelInicioSesion.setVisible(false);
                        interfazLogin.panelMensaje.repaint();
                    } else if (status == Status.USUARIO_BLOQUEADO) {
                    	interfazLogin.textoUsuarioBloqueado.setVisible(true);
                        interfazLogin.panelMensaje.setVisible(true);
                        interfazLogin.panelInicioSesion.setVisible(false);
                        interfazLogin.panelMensaje.repaint();
                    } else {
                    	interfazLogin.textoError.setVisible(true);
                        interfazLogin.panelMensaje.setVisible(true);
                        interfazLogin.panelInicioSesion.setVisible(false);
                        interfazLogin.panelMensaje.repaint();
                    }
                }
            }
        });
    }
}