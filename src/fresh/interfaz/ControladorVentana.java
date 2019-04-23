package fresh.interfaz;
import fresh.sistema.Sistema;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Frame;

public class ControladorVentana {

    public ControladorVentana(Sistema sistema) {
        VistaVentana vistaVentana = new VistaVentana();

        @SuppressWarnings("unused")
        ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);

        vistaVentana.botonMinimizarAplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaVentana.setState(Frame.ICONIFIED);
            }
        });

        vistaVentana.botonCerrarAplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaVentana.dispose();
                sistema.cerrarSistema();
            }
        });

        Point click = new Point();
        vistaVentana.barraSuperior.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!e.isMetaDown()) {
                    click.x = e.getX();
                    click.y = e.getY();
                }
            }
        });
        vistaVentana.barraSuperior.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!e.isMetaDown()) {
                    Point p = vistaVentana.getLocation();
                    int x = p.x+e.getX()-click.x;
                    int y = p.y+e.getY()-click.y;
                    vistaVentana.setLocation(x, y);
                }
            }
        });
    }
}