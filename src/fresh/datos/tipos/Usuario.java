package fresh.datos.tipos;
import java.util.Calendar;
import java.time.Period;
import java.time.LocalDate;

/**
 * 
 * 	Implementación de la clase Usuario
 * 
 * 	@author 
 */

public class Usuario {
	
	private boolean esPremium;
	private String nombre;
	private String nombreAutor;
	private String contrasena;
	private Calendar fechaNacimiento;
	private int reproduccionesMensuales;
	
	private List<Notificacion> notificaciones;
	private List<ListaReproduccion> listasReproduccion;
	private List<Cancion> canciones;
	private List<Album> albumes;
	
	/**
	 * Constructor de la clase Usuario
	 * 
	 * @param esPremium, indica si el usuario es Premium
	 * @param nombre, nombre del Usuario
	 * @param nombreAutor, nombre de autor del Usuario
	 * @param contrasena, contraseña con la que se registra el Usuario
	 * @param fechaNacimiento, fecha de nacimento del usuario
	 */
	
	public Usuario(boolean esPremium, String nombre, String nombreAutor, String contrasena, Date fechaNacimiento) {
		this.esPremium = esPremium;
		this.nombre = nombre;
		this.nombreAutor = nombreAutor;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		
	}
	
	public boolean getEsPremium(){
		return esPremium;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getNombreAutor(){
		return nombreAutor;
	}
	
	
	public String getContrasena(){
		return contrasena;
	}
	
	public Calendar getFechaNacimiento(){
		return fechaNacimiento;
	}
	
	public int getReproduccionesMensuales(){
		return reproduccionesMensuales;
	}
	
	public List<Notificacion> getNotificaciones(){
		return notificaciones;
	}
	
	public List<ListaReproduccion> getListasReproduccion(){
		return listasReproduccion;
	}
	
	public List<Cancion> getCanciones(){
		return canciones;
	}
	
    public List<Album> getAlbumes(){
    	return albumes;
    }
    
    /**
     * Funcion que calcula si un Usuario es mayor de edad para reproducir contenido explicito
     * 
     * @return true si puede reproducir contenido explicito o false si no
     */
    
    public boolean puedeContenidoExplicito() {
    	
    	LocalDate ahora = LocalDate.now();
    	Period periodo = Period.between(fechaNacimiento, ahora);
    	
    	if (periodo.getYears() > 18) {
    		return true;;
    	}
    	
    	return false;
    	
    }
}