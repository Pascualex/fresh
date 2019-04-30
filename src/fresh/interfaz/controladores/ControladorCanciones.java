package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fresh.interfaz.vistas.VistaCanciones;

public class ControladorCanciones {

    public ControladorCanciones(Sistema sistema, VistaCanciones vistaCanciones) {
        vistaCanciones.botonSubirCancion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = vistaCanciones.chooser.showOpenDialog(vistaCanciones);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("You chose to open this file: " +
                        vistaCanciones.chooser.getSelectedFile().getName());
                }
            }
        });
    }
}