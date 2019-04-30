package fresh;

import fresh.sistema.Sistema;
import fresh.interfaz.controladores.ControladorVentana;

import java.lang.Runnable;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Sistema sistema = new Sistema();
                @SuppressWarnings("unused")
                ControladorVentana controladorVentana = new ControladorVentana(sistema);
            }
        });
    }
}