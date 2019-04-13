package fresh.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ControladorLogin {
    InterfazLogin interfazLogin = new InterfazLogin();

    public static void main(String[] args) {
        ControladorLogin controladorLogin = new ControladorLogin();
    }

    public ControladorLogin() {
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
                interfazLogin.setVisible(false);
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
                interfazLogin.textoContrasenaIncorrecta.setVisible(false);
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
                    System.out.println("Iniciando sesión");
                }
            }
        });
    }
}