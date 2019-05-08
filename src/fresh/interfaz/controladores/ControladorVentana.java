package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;

import java.io.File;
import java.io.IOException;

public class ControladorVentana {

    public ControladorVentana(Sistema sistema) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("noto-emoji-regular.ttf")));
        } catch(IOException e) {
            System.out.println("No se ha encontrado el archivo de la fuente.");
        } catch(Exception e) {
            System.out.println("Se ha producido un error al registrar la fuente.");
        }

        VistaVentana vistaVentana = new VistaVentana();

        @SuppressWarnings("unused")
        ControladorInicio controladorInicio = new ControladorInicio(sistema, vistaVentana);

        vistaVentana.setVisible(true);

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
        vistaVentana.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!e.isMetaDown()) {
                    click.x = e.getX();
                    click.y = e.getY();
                }
            }
        });
        vistaVentana.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!e.isMetaDown()) {
                    if (click.getY() < 30) {
                        Point p = vistaVentana.getLocation();
                        int x = p.x+e.getX()-click.x;
                        int y = p.y+e.getY()-click.y;
                        vistaVentana.setLocation(x, y);
                    }
                }
            }
        });
    }
}