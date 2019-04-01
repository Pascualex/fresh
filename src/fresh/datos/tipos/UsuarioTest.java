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
	Notificacion n = new Notificacion(TipoNotificacion.CANCION_VALIDADA);
	ListaReproduccion lr = new ListaReproduccion("lista1");
	Cancion c = new Cancion("Cancion1", 18, u1, 01);
	List<Cancion> lc = new ArrayList<>();
	Album a = new Album("album1", u1, 2019, lc);

	@Test
	public void testanadirNotificacion() {
		
		Set<Notificacion> s = new HashSet<>();
		
		u1.anadirNotificacion(n);
		s.add(n);
		
		assertEquals(s, u1.getNotificaciones());
	}
	
	@Test
	public void testeliminarNotificacion() {
		
		Notificacion aux = new Notificacion(TipoNotificacion.PREMIUM_CADUCADO);
		Set<Notificacion> s = new HashSet<>();
		
		u1.anadirNotificacion(n);
		u1.anadirNotificacion(aux);
		s.add(n);
		s.add(aux);
		
		u1.eliminarNotificacion(aux);
		s.remove(aux);
		
		assertEquals(s, u1.getNotificaciones());
	}
	
	@Test
	public void testanadirListaReproduccion() {
		
		Set<ListaReproduccion> s = new HashSet<>();
		
		u1.anadirListaReproduccion(lr);
		s.add(lr);
		assertEquals(s, u1.getListasReproducion());
	}
	
	@Test
	public void testeliminarListaReproduccion() {
		
		ListaReproduccion lraux = new ListaReproduccion("listaAux");
		Set<ListaReproduccion> s = new HashSet<>();
		
		u1.anadirListaReproduccion(lr);
		u1.anadirListaReproduccion(lraux);
		s.add(lr);
		s.add(lraux);
		
		u1.eliminarListaReproduccion(lraux);
		s.remove(lraux);
		
		assertEquals(s, u1.getListasReproducion());
	}
	
	@Test
	public void testanadirCancion() {
		
		Set<Cancion> s = new HashSet<>();
		
		u1.anadirCancion(c);
		s.add(c);
		
		assertEquals(s, u1.getCanciones());
	}
	
	@Test
	public void testeliminarCancion() {
		
		Cancion caux = new Cancion("Caux", 33, u1, 02);
		Set<Cancion> s = new HashSet<>();
		
		u1.anadirCancion(c);
		u1.anadirCancion(caux);
		s.add(c);
		s.add(caux);
		
		assertEquals(s, u1.getCanciones());
	}
	
	@Test
	public void testanadirAlbum() {
		
		Set<Album> s = new HashSet<>();
				
		lc.add(c);
		
		u1.anadirAlbum(a);
		s.add(a);
		
		assertEquals(s, u1.getAlbumes());
	}
	
	@Test
	public void testeliminarAlbum() {
		
		Album aux = new Album("album1", u1, 2017, lc);
		Set<Album> s = new HashSet<>();
		
		u1.anadirAlbum(a);
		u1.anadirAlbum(aux);
		s.add(a);
		s.add(aux);
		
		u1.eliminarAlbum(aux);
		s.remove(aux);
		
		assertEquals(s, u1.getAlbumes());
	}
	
	@Test
	public void testanadirSeguidor() {
		
		Usuario uaux = new Usuario("Lola", "LL", "cll", fecha);
		Set<Usuario> s = new HashSet<>();
				
		u1.anadirSeguidor(uaux);
		s.add(uaux);
		
		assertEquals(s, u1.getSeguidores());
	}
	
	@Test
	public void testeliminarSeguidor() {
		
		Usuario uaux = new Usuario("Lola", "LL", "cll", fecha);
		Usuario uprueba = new Usuario("Sergio", "SS", "css", fecha);
		Set<Usuario> s = new HashSet<>();
		
		u1.anadirSeguidor(uaux);
		u1.anadirSeguidor(uprueba);
		s.add(uaux);
		s.add(uprueba);
		
		u1.eliminarSeguidor(uprueba);
		s.remove(uprueba);
		
		assertEquals(s, u1.getSeguidores());
	}
}