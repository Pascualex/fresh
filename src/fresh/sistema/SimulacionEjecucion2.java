package fresh.sistema;

import fresh.Status;

import java.util.GregorianCalendar;

public class SimulacionEjecucion {
    
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        System.out.println("Se registra al usuario 1.");
        if (sistema.registrarse("Usuario 1", "Autor 1", "Contrasena 1", new GregorianCalendar(2000, 1, 1)) != Status.OK) {
            System.out.println("Error");
            return;
        }
        
        System.out.println("Se inicia sesión con el usuario 1.");
        if (sistema.iniciarSesion("Usuario 1", "Contrasena 1") != Status.OK) {
            System.out.println("Error");
            return;
        }

        sistema.cerrarSistema();
    }
}