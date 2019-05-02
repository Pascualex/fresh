package fresh;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Cancion;
import fresh.datos.tipos.EstadoCancion;

import java.util.List;

public class MainValidarTodo {

    public static void main(String[] args) {    
        Sistema sistema = new Sistema();
        
        if (sistema.iniciarSesion("admin", "admin") != Status.INICIO_ADMINISTRADOR) {
            System.out.println("Error");
            sistema.cerrarSistema();
            return;
        }

        List<Cancion> nuevasCanciones = sistema.obtenerNuevasCanciones();

        while (!nuevasCanciones.isEmpty()) {
            sistema.cambiarEstadoCancion(nuevasCanciones.get(0), EstadoCancion.VALIDADA);
        }

        sistema.cerrarSistema();
    }
}