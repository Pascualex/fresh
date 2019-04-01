package fresh.sistema;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfiguracionTest {
	private static final String rutaTest = "./configuracionTest.txt";

	@Test
	public void testGuardarConfiguracion() {
		Configuracion configuracion1 = new Configuracion(rutaTest);

		configuracion1.setNombreAdministrador("Campo1");
		configuracion1.setContrasenaAdministrador("Campo2");
		configuracion1.setEdadMinima(3);
		configuracion1.setMaxReproduccionesAnonimo(4);
		configuracion1.setMaxReproduccionesRegistrado(5);
		configuracion1.setCuotaPremium(6);
		configuracion1.setMinReproduccionesPremium(7);
		configuracion1.setCaracteresMinimos(8);

		configuracion1.guardarConfiguracion();
		configuracion1 = null;

		Configuracion configuracion2 = new Configuracion(rutaTest);
		
		assertEquals("Fallo nombre.", "Campo1", configuracion2.getNombreAdministrador());
		assertEquals("Fallo contraseña.", "Campo2", configuracion2.getContrasenaAdministrador());
		assertEquals("Fallo edad mínima.", 3, configuracion2.getEdadMinima());
		assertEquals("Fallo máximas reproducciones anónimo.", 4, configuracion2.getMaxReproduccionesAnonimo());
		assertEquals("Fallo máximas reproducciones registrado.", 5, configuracion2.getMaxReproduccionesRegistrado());
		assertEquals("Fallo cuota premium.", 6, configuracion2.getCuotaPremium(), 0);
		assertEquals("Fallo mínimas reproducciones premium.", 7, configuracion2.getMinReproduccionesPremium());
		assertEquals("Fallo caracteres mínimos.", 8, configuracion2.getCaracteresMinimos());
	}
}
