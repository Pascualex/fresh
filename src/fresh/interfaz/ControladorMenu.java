package fresh.interfaz;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fresh.Status;
import fresh.sistema.Sistema;

public class ControladorMenu {
    private InterfazMenu interfazMenu;
    @SuppressWarnings("unused")
    private final Sistema sistema;

    public static void main(String[] args) {
        ControladorMenu c = new ControladorMenu(new Sistema());
    }

    public ControladorMenu(Sistema sistema) {
        this.sistema = sistema;
        interfazMenu = new InterfazMenu();
        
        interfazMenu.botonCerrarAplicacion.addMouseListener(new MouseListener(){
        
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
                interfazMenu.dispose();
                sistema.cerrarSistema();
                return;
            }
        });

        interfazMenu.botonMinimizarAplicacion.addMouseListener(new MouseListener(){
        
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
                interfazMenu.setState(Frame.ICONIFIED);
            }
        });

        interfazMenu.botonAnterior.addMouseListener(new MouseListener(){
        
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
                sistema.reiniciarCancion();
            }
        });

        interfazMenu.botonReproducir.addMouseListener(new MouseListener(){
        
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
                sistema.reanudarCancion();
                interfazMenu.botonReproducir.setVisible(false);
                interfazMenu.botonParar.setVisible(true);
            }
        });

        interfazMenu.botonParar.addMouseListener(new MouseListener(){
        
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
                sistema.pausarCancion();
                interfazMenu.botonParar.setVisible(false);
                interfazMenu.botonReproducir.setVisible(true);
            }
        });

        interfazMenu.botonCerrarSesion.addMouseListener(new MouseListener(){
        
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
                Status status = sistema.cerrarSesion();
                if (status == Status.OK) {
                    interfazMenu.dispose();
                    @SuppressWarnings("unused")
                    ControladorLogin controladorLogin = new ControladorLogin(sistema);
                    return;
                }
            }
        });
    }
}