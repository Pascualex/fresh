package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;
import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaCanciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorCanciones {

    public ControladorCanciones(Sistema sistema, VistaMenu vistaMenu) {
        VistaCanciones vistaCanciones = new VistaCanciones();
        if (vistaMenu.panelActual != null) {
            vistaMenu.remove(vistaMenu.panelActual);
        }

        vistaMenu.panelActual = vistaCanciones;
        vistaMenu.add(vistaCanciones);

        vistaCanciones.setVisible(true);
        vistaMenu.repaint();
    }
}