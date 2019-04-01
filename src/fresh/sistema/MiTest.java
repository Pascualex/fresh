package fresh.sistema;

public class MiTest {
	private static final String rutaTest = "./configuracion.txt";
    
    public static void main(String[] args) {
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
        System.out.println("fin");
    }
}