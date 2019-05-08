package fresh;

import fresh.sistema.Sistema;
import fresh.interfaz.controladores.ControladorVentana;

import java.lang.Runnable;
import javax.swing.SwingUtilities;

public class MainPrueba {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Sistema sistema = new Sistema(true);
                @SuppressWarnings("unused")
                ControladorVentana controladorVentana = new ControladorVentana(sistema);
            }
        });
    }
}