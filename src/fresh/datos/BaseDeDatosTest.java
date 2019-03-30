package fresh.datos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import fresh.datos.*;
import fresh.datos.tipos.*;



public class BaseDeDatosTest {
    BaseDeDatos bd = BaseDeDatos.cargarBaseDeDatos("Prueba");

    @Test
    public void testEncontrarUsuarios() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());
        Usuario u2 = new Usuario("nombre2", "nombreAutor2", "a", new GregorianCalendar());
        Usuario u3 = new Usuario("nombre3", "nombreAutor3", "a", new GregorianCalendar());
        
        List<Usuario> l1 = new ArrayList<>();
        List<Usuario> l2 = new ArrayList<>();

        l1.add(u1);
        l1.add(u2);
        l1.add(u3);

        bd.anadirUsuario(u1);
        bd.anadirUsuario(u2);
        bd.anadirUsuario(u3);

        l2.add(bd.buscarUsuario("nombreAutor1"));
        l2.add(bd.buscarUsuario("nombreAutor2"));
        l2.add(bd.buscarUsuario("nombreAutor3"));

        assertEquals(l1, l2);
    }

    @Test
    public void testEncontrarCanciones() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());

        Cancion c1 = new Cancion("nombre1", 2, u1, 8);
        Cancion c2 = new Cancion("nombre2", 2, u1, 9);
        Cancion c3 = new Cancion("nombre3", 2, u1, 10);
         
        List<Cancion> l1 = new ArrayList<>();
        List<Cancion> l2 = new ArrayList<>();

        l1.add(c1);
        l1.add(c2);
        l1.add(c3);

        bd.anadirCancion(c1);
        bd.anadirCancion(c2);
        bd.anadirCancion(c3);

        l2.add(bd.buscarCanciones("nombre1", true).get(0));
        l2.add(bd.buscarCanciones("nombre2", true).get(0));
        l2.add(bd.buscarCanciones("nombre3", true).get(0));

        assertEquals(l1, l2);
    }
}