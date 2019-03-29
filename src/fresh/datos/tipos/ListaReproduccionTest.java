package fresh.datos.tipos;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;

/**
 * 
 * 	Clase que prueba las funciones de Lista Reproduccion
 * 
 * 	@author Ana Roa (ana.roa@estudiante.uam.es)
 */

public class ListaReproduccionTest {
	
	Calendar fecha = new GregorianCalendar(1999, 1, 3);
	Usuario u1 = new Usuario("Ana", "AA", "Hola1234", fecha);
	Cancion c = new Cancion("Cancion1", 18, u1, 01);
	ListaReproduccion lr1 = new ListaReproduccion("lista1");
	ListaReproduccion lr2 = new ListaReproduccion("lista2");
	

	@Test
	public void testAnadirElemento() {
		
		lr1.anadirElemento(c);
		lr2.anadirElemento(c);
		
		assertEquals(lr1.getCanciones(), lr2.getCanciones());
	}

	@Test
	public void testEliminarElemento() {
		Cancion aux = new Cancion("CancionAux", 28, u1, 02);
		
		lr1.anadirElemento(c);
		lr1.anadirElemento(aux);
		lr2.anadirElemento(c);
		lr2.anadirElemento(aux);
		
		lr1.eliminarElemento(aux);
		lr2.eliminarElemento(aux);
		
		assertEquals(lr1.getCanciones(), lr2.getCanciones());
	}

}
