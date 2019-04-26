package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;
import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaNotificaciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNotificaciones {

    public ControladorNotificaciones(Sistema sistema, VistaMenu vistaMenu) {
        VistaNotificaciones vistaNotificaciones = new VistaNotificaciones();
        if (vistaMenu.panelActual != null) {
            vistaMenu.remove(vistaMenu.panelActual);
        }

        vistaMenu.panelActual = vistaNotificaciones;
        vistaMenu.add(vistaNotificaciones);

        vistaNotificaciones.setVisible(true);
        vistaMenu.repaint();
    }
}