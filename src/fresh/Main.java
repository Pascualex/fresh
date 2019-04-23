package fresh;

import fresh.sistema.Sistema;
import fresh.interfaz.ControladorVentana;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        @SuppressWarnings("unused")
        ControladorVentana controladorVentana = new ControladorVentana(sistema);
    }
}