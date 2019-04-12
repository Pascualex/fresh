package fresh.interfaz;

import fresh.sistema.Sistema;

public class ControladorMenu {
    private InterfazMenu interfazMenu;
    //private final Sistema sistema;

    public ControladorMenu() {
        //this.sistema = sistema;
        interfazMenu = new InterfazMenu();
    }

    public static void main(String[] args) {
        ControladorMenu controladorMenu = new ControladorMenu();
    }
}