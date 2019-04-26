package fresh.interfaz.controladores;

import fresh.Status;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaVentana;
import fresh.interfaz.vistas.VistaMenu;
import fresh.interfaz.vistas.VistaPlaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPlaylist {

    public ControladorPlaylist(Sistema sistema, VistaMenu vistaMenu) {
        VistaPlaylist vistaPlaylist = new VistaPlaylist();
        if (vistaMenu.panelActual != null) {
            vistaMenu.remove(vistaMenu.panelActual);
        }

        vistaMenu.panelActual = vistaPlaylist;
        vistaMenu.add(vistaPlaylist);

        vistaPlaylist.setVisible(true);
        vistaMenu.repaint();
    }
}