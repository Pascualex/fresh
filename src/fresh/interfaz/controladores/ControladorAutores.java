package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;
import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaAutores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAutores {

    public ControladorAutores(Sistema sistema, VistaMenu vistaMenu) {
        VistaAutores vistaAutores = new VistaAutores();
        if (vistaMenu.panelActual != null) {
            vistaMenu.remove(vistaMenu.panelActual);
        }

        vistaMenu.panelActual = vistaAutores;
        vistaMenu.add(vistaAutores);

        vistaAutores.setVisible(true);
        vistaMenu.repaint();
    }
}