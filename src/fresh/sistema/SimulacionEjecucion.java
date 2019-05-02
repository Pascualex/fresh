package fresh.sistema;

import fresh.Status;
import fresh.datos.tipos.*;

import java.util.List;
import java.io.File;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Este test hace una comprobación general de la funcionalidad del sistema,
 * simulando una serie de interacciones y revisando su correcta ejecución.
 */
public class SimulacionEjecucion {
    
    public static void main(String[] args) {
    	File fichero = new File("./baseDeDatos.ser");
        fichero.delete();
    	
        fichero = new File("./gestorEventos.ser");
        fichero.delete();
    	
        Sistema sistema = new Sistema();

        System.out.println("Se registra al usuario 1.");
        if (sistema.registrarse("Usuario 1", "Autor 1", "Contrasena 1", new GregorianCalendar(2000, 1, 1)) != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Se inicia sesión con el usuario 1.");
        if (sistema.iniciarSesion("Usuario 1", "Contrasena 1") != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Se sube una canción.");
        if (sistema.subirCancion("All Star", "./all_star.mp3") != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Cierra la sesión.");
        if (sistema.cerrarSesion() != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Se inicia la sesión del administrador.");
        if (sistema.iniciarSesion("admin", "admin") != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Obtiene la lista de canciones pendientes de validar.");
        List<Cancion> nuevasCanciones = sistema.obtenerNuevasCanciones();
        if (nuevasCanciones.size() == 0) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Valida la canción del usuario 1.");
        
        Cancion nuevaCancion = null;
        for (Cancion cancion : nuevasCanciones) {
            nuevaCancion = cancion;
        }
        if (sistema.cambiarEstadoCancion(nuevaCancion, EstadoCancion.VALIDADA) != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Cierra la sesión.");
        if (sistema.cerrarSesion() != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Se registra al usuario 2.");
        if (sistema.registrarse("Usuario 2", "Autor 2", "Contrasena 2", new GregorianCalendar(2000, 1, 1)) != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Se inicia sesión con el usuario 2.");
        if (sistema.iniciarSesion("Usuario 2", "Contrasena 2") != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Busca la canción del usuario 1.");
        List<Cancion> canciones = sistema.buscarCanciones("All Star");
        if (canciones.size() == 0) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Reproduce la canción del usuario.");
        sistema.reproducir(canciones.get(0));
        try {
        	TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
        	System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Pausa la canción del usuario.");
        sistema.pausarCancion();
        try {
        	TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
        	System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Reanuda la canción del usuario.");
        sistema.reanudarCancion();
        try {
        	TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
        	System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        
        System.out.println("Reinicia la canción del usuario.");
        sistema.reiniciarCancion();
        try {
        	TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
        	System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }
        sistema.pausarCancion();
        
        System.out.println("Cierra la sesión.");
        if (sistema.cerrarSesion() != Status.OK) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }

        System.out.println("Cierra el sistema.");
        sistema.cerrarSistema();
    }
}