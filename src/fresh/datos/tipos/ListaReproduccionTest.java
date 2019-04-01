package fresh.datos.tipos;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
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
	ListaReproduccion lr = new ListaReproduccion("lista1");
	

	@Test
	public void testAnadirElemento() {
		List<Cancion> lista = new ArrayList<>();
		
		c.setEstado(EstadoCancion.VALIDADA);
		lr.anadirElemento(c);
		lista.add(c);
	
		System.out.println("lista:"+lista);
		System.out.println("lr:"+lr.getCanciones());
		
		assertEquals(lista, lr.getCanciones());
	}

	@Test
	public void testEliminarElemento() {
		Cancion aux = new Cancion("CancionAux", 28, u1, 02);
		List<Cancion> lista = new ArrayList<>();

		c.setEstado(EstadoCancion.VALIDADA);
		aux.setEstado(EstadoCancion.VALIDADA);
		
		lr.anadirElemento(c);
		lr.anadirElemento(aux);
		lista.add(c);
		lista.add(aux);
		
		lr.eliminarElemento(aux);
		lista.remove(aux);
		
		assertEquals(lista, lr.getCanciones());
	}

}
