package fresh.interfaz;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fresh.Status;
import fresh.sistema.Sistema;

public class ControladorInicio {

    public ControladorInicio(Sistema sistema, VistaVentana vistaVentana) {
        VistaInicio vistaInicio = new VistaInicio();
        vistaVentana.add(vistaInicio);

        vistaInicio.botonOK.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {                
                vistaInicio.textoIntroduceUsuario.setVisible(false);
                vistaInicio.textoIntroduceContrasena.setVisible(false);
                vistaInicio.textoUsuarioIncorrecto.setVisible(false);
                vistaInicio.textoUsuarioBloqueado.setVisible(false);
                vistaInicio.textoContrasenaIncorrecta.setVisible(false);
                vistaInicio.textoError.setVisible(false);
                vistaInicio.panelMensaje.setVisible(false);
                vistaInicio.panelInicioSesion.setVisible(true);
            }
        });

        vistaInicio.botonIniciarSesion.addMouseListener(new MouseListener(){
        
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
                if (vistaInicio.entradaNombreInicio.getText().isEmpty()) {
                    vistaInicio.textoIntroduceUsuario.setVisible(true);
                    vistaInicio.panelMensaje.setVisible(true);
                    vistaInicio.panelInicioSesion.setVisible(false);
                    vistaInicio.panelMensaje.repaint();
                } else if (vistaInicio.entradaContrasenaInicio.getPassword().length == 0) {
                    vistaInicio.textoIntroduceContrasena.setVisible(true);
                    vistaInicio.panelMensaje.setVisible(true);
                    vistaInicio.panelInicioSesion.setVisible(false);
                    vistaInicio.panelMensaje.repaint();
                } else {
                    Status status = sistema.iniciarSesion(vistaInicio.entradaNombreInicio.getText(), 
                    							String.valueOf(vistaInicio.entradaContrasenaInicio.getPassword()));
                    if (status == Status.OK) {
                    	//Pasa al menu
                    	vistaVentana.remove(vistaInicio);
                    	@SuppressWarnings("unused")
                        ControladorMenu controladorMenu = new ControladorMenu(sistema, vistaVentana);
                        return;
                    } else if (status == Status.CONTRASENA_INVALIDA) {
                    	vistaInicio.textoContrasenaIncorrecta.setVisible(true);
                        vistaInicio.panelMensaje.setVisible(true);
                        vistaInicio.panelInicioSesion.setVisible(false);
                        vistaInicio.panelMensaje.repaint();
                    } else if (status == Status.NOMBRE_INVALIDO) {
                    	vistaInicio.textoUsuarioIncorrecto.setVisible(true);
                        vistaInicio.panelMensaje.setVisible(true);
                        vistaInicio.panelInicioSesion.setVisible(false);
                        vistaInicio.panelMensaje.repaint();
                    } else if (status == Status.USUARIO_BLOQUEADO) {
                    	vistaInicio.textoUsuarioBloqueado.setVisible(true);
                        vistaInicio.panelMensaje.setVisible(true);
                        vistaInicio.panelInicioSesion.setVisible(false);
                        vistaInicio.panelMensaje.repaint();
                    } else {
                    	vistaInicio.textoError.setVisible(true);
                        vistaInicio.panelMensaje.setVisible(true);
                        vistaInicio.panelInicioSesion.setVisible(false);
                        vistaInicio.panelMensaje.repaint();
                    }
                }
            }
        });
        
        vistaInicio.botonRegistrarseInicio.addMouseListener(new MouseListener(){
        
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
            	vistaVentana.remove(vistaInicio);
            	@SuppressWarnings("unused")
                ControladorRegistro controladorRegistro = new ControladorRegistro(sistema, vistaVentana);
                return;
            }
        });

        vistaInicio.botonAnonimo.addMouseListener(new MouseListener(){
        
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
                Status status = sistema.iniciarSesionAnonimo();
                if (status == Status.OPERACION_INACCESIBLE) {
                    vistaInicio.textoError.setVisible(true);
                    vistaInicio.panelMensaje.setVisible(true);
                    vistaInicio.panelInicioSesion.setVisible(false);
                    vistaInicio.panelMensaje.repaint();
                } else {
                    vistaVentana.remove(vistaInicio);
                    @SuppressWarnings("unused")
                    ControladorMenu controladorMenu = new ControladorMenu(sistema, vistaVentana);
                }
            }
        });
    }
}