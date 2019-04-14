package fresh.interfaz;

import fresh.sistema.Sistema;

public class ControladorMenu {
    private InterfazMenu interfazMenu;
    private final Sistema sistema;
    //private final Sistema sistema;

    public ControladorMenu(Sistema sistema) {
        this.sistema = sistema;
        interfazMenu = new InterfazMenu();
    }

    public static void main(String[] args) {
        ControladorMenu controladorMenu = new ControladorMenu(new Sistema());
    }
}