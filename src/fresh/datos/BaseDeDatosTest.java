package fresh.datos;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import fresh.datos.*;
import fresh.datos.tipos.*;



public class BaseDeDatosTest {
	final String ruta = "Prueba";
	
    BaseDeDatos bd = BaseDeDatos.cargarBaseDeDatos(ruta);
    
    
    @Test
    public void testEncontrarUsuarios1() {
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
    public void testEncontrarUsuarios2() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());

        u1.setBloqueado(false);
        
        List<Usuario> l1 = new ArrayList<>();
        List<Usuario> l2 = new ArrayList<>();

        l1.add(u1);
        
        bd.anadirUsuario(u1);
        
        l2.addAll(bd.buscarAutores("nombreAutor"));

        assertEquals(l1, l2);
    }

    
    @Test
    public void testEncontrarCanciones1() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());

        Cancion c1 = new Cancion("nombre1", 2, u1, 8);
        Cancion c2 = new Cancion("nombre2", 2, u1, 9);
        Cancion c3 = new Cancion("nombre3", 2, u1, 10);
        
        c1.setEstado(EstadoCancion.VALIDADA);
        c2.setEstado(EstadoCancion.VALIDADA);
        c3.setEstado(EstadoCancion.VALIDADA);
         
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
    
    @Test
    public void testEncontrarCanciones2() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());

        Cancion c1 = new Cancion("nombre1", 2, u1, 8);
        Cancion c2 = new Cancion("nombre2", 2, u1, 9);
        Cancion c3 = new Cancion("nombre3", 2, u1, 10);
        
        c1.setEstado(EstadoCancion.BLOQUEADA_PERMANENTE);
        c2.setEstado(EstadoCancion.BLOQUEADA_PERMANENTE);
        c3.setEstado(EstadoCancion.BLOQUEADA_PERMANENTE);
         
        List<Cancion> l1 = new ArrayList<>();
        List<Cancion> l2 = new ArrayList<>();

        bd.anadirCancion(c1);
        bd.anadirCancion(c2);
        bd.anadirCancion(c3);

        l2.addAll(bd.buscarCanciones("nombre1", true));
        l2.addAll(bd.buscarCanciones("nombre2", true));
        l2.addAll(bd.buscarCanciones("nombre3", true));

        assertEquals(l1, l2);
    }
    
    @Test 
    public void testEliminarPremiumUsuarios() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());
        
        u1.setPremium(true);
        
        bd.anadirUsuario(u1);
        
        bd.eliminarPremiumUsuarios();
        
        assertEquals(u1.getPremium(), false);
    }
    
    @Test
    public void testEliminarCanciones() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());

        Cancion c1 = new Cancion("nombre1", 2, u1, 8);
        Cancion c2 = new Cancion("nombre2", 2, u1, 9);
        
        c1.setEstado(EstadoCancion.VALIDADA);
        c2.setEstado(EstadoCancion.VALIDADA);
         
        List<Cancion> l1 = new ArrayList<>();
        List<Cancion> l2 = new ArrayList<>();

        l1.add(c1);

        bd.anadirCancion(c1);
        bd.anadirCancion(c2);
        
        bd.eliminarCancion(c2);

        l2.addAll(bd.buscarCanciones("nombre", true));

        assertEquals(l1, l2);
    }
    
    @Test
    public void testEncontrarAlbumes() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());

        Cancion c1 = new Cancion("nombre1", 2, u1, 8);
        Cancion c2 = new Cancion("nombre2", 2, u1, 9);
        Cancion c3 = new Cancion("nombre3", 2, u1, 10);
        
        List<Cancion> l1 = new ArrayList<>();

        l1.add(c1);
        l1.add(c2);
        l1.add(c3);
        
        Album a1 = new Album("album1", u1, 0, l1);
        
        c1.setEstado(EstadoCancion.VALIDADA);
        c2.setEstado(EstadoCancion.VALIDADA);
        c3.setEstado(EstadoCancion.VALIDADA);
        
        bd.anadirCancion(c1);
        bd.anadirCancion(c2);
        bd.anadirCancion(c3);
        
        bd.anadirAlbum(a1);

        assertEquals(l1, bd.buscarAlbumes("album1").get(0).getCanciones());
    }
    
    @Test
    public void testReportes() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());
        Cancion c1 = new Cancion("nombre1", 2, u1, 8);
        Reporte r1 = new Reporte("reporte1", u1, c1);
        
        bd.anadirReporte(r1);

        assertEquals(r1, bd.obtenerReportes().get(0));
    }
    
    @Test
    public void testCargarInformacion() {
        Usuario u1 = new Usuario("nombre1", "nombreAutor1", "a", new GregorianCalendar());
        
        bd.anadirUsuario(u1);
        
        bd.guardarEnDisco();
        
        bd = BaseDeDatos.cargarBaseDeDatos(ruta);
        
        File file = new File(ruta);
        file.delete();

        assertEquals(u1.getNombre(), bd.buscarUsuario("nombreAutor1").getNombre());
    }
}