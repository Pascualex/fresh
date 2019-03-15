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
	
	/**
	 * Devuelve si el usuario es premium
	 * 
	 * @return true si el usuario es premium, false si no
	 */
	public boolean getEsPremium(){
		return esPremium;
	}
	
	/**
	 * Devuelve el nombre del usuario
	 * 
	 * @return String con el nombre del usuario
	 */
	public String getNombre(){
		return nombre;
	}
	
	/**
	 * Devuelve el nombre de autor del usuario
	 * 
	 * @return String con el nombre de autor del usuario
	 */
	public String getNombreAutor(){
		return nombreAutor;
	}
	
	/**
	 * Devuelve la contraseña del usuario
	 * 
	 * @return String con la contraseña del usuario
	 */
	public String getContrasena(){
		return contrasena;
	}
	
	/**
	 * Devuelve la fecha de nacimiento del usuario
	 * 
	 * @return Calendar con la fecha de nacimiento del usuario
	 */
	public Calendar getFechaNacimiento(){
		return fechaNacimiento;
	}
	
	/**
	 * Devuelve las reproducciones mensuales de un usuario
	 * 
	 * @return int con las reproducciones mensuales del usuario
	 */
	public int getReproduccionesMensuales(){
		return reproduccionesMensuales;
	}
	
	/**
	 * Modifica las reproducciones mensuales de un usuario
	 * 
	 * @return Usuario modificado
	 */
	public Usuario setReproduccionesMensuales(int reproduccionesMensuales) {
		this.reproduccionesMensuales = reproduccionesMensuales;
		return this;
	}
	
	/**
	 * Devuelve la lista de notificaiones de un usuario
	 * 
	 * @return Lista de notificaciones del usuario
	 */
	public List<Notificacion> getNotificaciones(){
		return notificaciones;
	}
	
	/**
	 * Devuelve la lista de playlists del usuario
	 * 
	 * @return Listas de reproduccion de un usuario
	 */
	public List<ListaReproduccion> getListasReproduccion(){
		return listasReproduccion;
	}
	
	/**
	 * Devuelve la lista de canciones del usuario
	 * 
	 * @return Lista de canciones del usuario
	 */
	public List<Cancion> getCanciones(){
		return canciones;
	}
	
	/**
	 * Devuelve la lista de canciones de un usuario
	 * 
	 * @return Lista de Albumes de un usuario
	 */
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