package fresh.datos.tipos;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;

/**
 * 	Clase que prueba las funciones de Usuario
 */
public class UsuarioTest {
	
	Calendar fecha = new GregorianCalendar(1999, 1, 3);
	Usuario u1 = new Usuario("Ana", "AA", "Hola1234", fecha);
	Usuario u2 = new Usuario("Roa", "RR", "Hello123", fecha);
	Notificacion n1 = new Notificacion(TipoNotificacion.CANCION_VALIDADA);
	ListaReproduccion lr = new ListaReproduccion("lista1");
	Cancion c = new Cancion("Cancion1", 18, u1, 01);
	Cancion c2 = new Cancion("Cancion2", 12, u1, 01);
	List<Cancion> s = new ArrayList<>();
	Album a = new Album("album1", u1, 2019, s);
	
	@Test
	public void testanadirNotificacion() {
		
		u1.anadirNotificacion(n1);
		u2.anadirNotificacion(n1);
		
		assertEquals(u1.getNotificaciones(), u2.getNotificaciones());
	}
	
	@Test
	public void testeliminarNotificacion() {
		
		Notificacion aux = new Notificacion(TipoNotificacion.PREMIUM_CADUCADO);
		
		u1.anadirNotificacion(n1);
		u1.anadirNotificacion(aux);
		u2.anadirNotificacion(n1);
		u2.anadirNotificacion(aux);
		
		u1.eliminarNotificacion(aux);
		u2.eliminarNotificacion(aux);
		
		assertEquals(u1.getNotificaciones(), u2.getNotificaciones());
	}
	
	@Test
	public void testanadirListaReproduccion() {
		
		u1.anadirListaReproduccion(lr);
		u2.anadirListaReproduccion(lr);
		assertEquals(u1.getListasReproducion(), u2.getListasReproducion());
	}
	
	@Test
	public void testeliminarListaReproduccion() {
		
		ListaReproduccion lraux = new ListaReproduccion("listaAux");
		
		u1.anadirListaReproduccion(lr);
		u1.anadirListaReproduccion(lraux);
		u2.anadirListaReproduccion(lr);
		u2.anadirListaReproduccion(lraux);
		
		u1.eliminarListaReproduccion(lraux);
		u2.eliminarListaReproduccion(lraux);
		
		assertEquals(u1.getListasReproducion(), u2.getListasReproducion());
	}
	
	@Test
	public void testanadirCancion() {
		
		u1.anadirCancion(c);
		u2.anadirCancion(c);
		
		assertEquals(u1.getCanciones(), u2.getCanciones());
	}
	
	@Test
	public void testeliminarCancion() {
		
		Cancion caux = new Cancion("Caux", 33, u1, 02);
		
		u1.anadirCancion(c);
		u1.anadirCancion(caux);
		u2.anadirCancion(c);
		u2.anadirCancion(caux);
		
		assertEquals(u1.getCanciones(), u2.getCanciones());
	}
	
	@Test
	public void testanadirAlbum() {
		
		
		s.add(c);
		s.add(c2);
		
		u1.anadirAlbum(a);
		u2.anadirAlbum(a);
		
		assertEquals(u1.getAlbumes(), u2.getAlbumes());
	}
	
	@Test
	public void testeliminarAlbum() {
		
		Album aux = new Album("album1", u2, 2017, s);
		
		u1.anadirAlbum(a);
		u1.anadirAlbum(aux);
		u2.anadirAlbum(a);
		u2.anadirAlbum(aux);
		
		u1.eliminarAlbum(aux);
		u2.eliminarAlbum(aux);
		assertEquals(u1.getAlbumes(), u2.getAlbumes());
	}
	
	@Test
	public void testanadirSeguidor() {
		
		Usuario uaux = new Usuario("Lola", "LL", "cll", fecha);
				
		u1.anadirSeguidor(uaux);
		u2.anadirSeguidor(uaux);
		
		assertEquals(u1.getSeguidores(), u2.getSeguidores());
	}
	
	@Test
	public void testeliminarSeguidor() {
		
		Usuario uaux = new Usuario("Lola", "LL", "cll", fecha);
		Usuario uprueba = new Usuario("Sergio", "SS", "css", fecha);
		
		u1.anadirSeguidor(uaux);
		u1.anadirSeguidor(uprueba);
		u2.anadirSeguidor(uaux);
		u2.anadirSeguidor(uprueba);
		
		u1.eliminarSeguidor(uprueba);
		u2.eliminarSeguidor(uprueba);
		assertEquals(u1.getSeguidores(), u2.getSeguidores());
	}
}