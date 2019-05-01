package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fresh.interfaz.vistas.VistaPlaylists;

public class ControladorPlaylists {

    public ControladorPlaylists(Sistema sistema, VistaPlaylists vistaPlaylists) {
        vistaPlaylists.botonCrearPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vistaPlaylists.entradaNombrePlaylist.getText().isEmpty()) {
                    return;
                }
                System.out.print(sistema.crearListaReproduccion(vistaPlaylists.entradaNombrePlaylist.getText()));
            }
        });
    }
}