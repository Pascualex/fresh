package fresh.datos.tipos;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fresh.Status;

import java.util.*;

/**
 * 
 * 	Implementación de la clase Usuario
 * 
 * 	@author 
 */

public class Usuario {
	
	private String nombre;
	private String nombreAutor;
	private String contrasena;
	private Calendar fechaNacimiento;

	private boolean esPremium;
	private int reproduccionesMensuales;
	private boolean bloqueado;
	
	private List<Notificacion> notificaciones;
	private List<ListaReproduccion> listasReproduccion;
	private List<Cancion> canciones;
	private List<Album> albumes;
	private List<Usuario> seguidores;
	
	/**
	 * Constructor de la clase Usuario
	 * 
	 * @param nombre, nombre del Usuario
	 * @param nombreAutor, nombre de autor del Usuario
	 * @param contrasena, contraseña con la que se registra el Usuario
	 * @param fechaNacimiento, fecha de nacimento del usuario
	 */
	
	public Usuario(String nombre, String nombreAutor, String contrasena, Calendar fechaNacimiento) {
		this.nombre = nombre;
		this.nombreAutor = nombreAutor;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		esPremium = false;
		reproduccionesMensuales = 0;
		bloqueado = false;
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
	 * Modifica si un usuario esPremium
	 * 
	 */
	public void setEsPremium(boolean esPremium){
		this.esPremium = esPremium;
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
	 * Devuelve si el usuario esta bloqueado o no
	 * 
	 * @return boolean con el estado del usuario, bloqueado o no
	 */
	public boolean getBloqueado(){
		return bloqueado;
	}
	/**
	 * Modifica el estado del usuario, bloqueado o no
	 */
	public void setBloqueado(boolean bloqueado){
		this.bloqueado = bloqueado;
		return;
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
	 * Devuelve la lista de notificaciones de un usuario
	 * 
	 * @return Lista de notificaciones del usuario
	 */
	public List<Notificacion> getNotificaciones(){
		return notificaciones;
	}
	
	public Status anadirListaReproduccion(ListaReproduccion listaReproduccion) {
		if (listasReproduccion.contains(listaReproduccion)) return Status.LISTA_REPRODUCCION_REPETIDA;

		listasReproduccion.add(listaReproduccion);
		return Status.OK;
	}

	public void eliminarListaReproduccion(ListaReproduccion listaReproduccion) {
		listasReproduccion.remove(listaReproduccion);
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
	
	public List<Usuario> getSeguidores() {
		return seguidores;
	}

	public void anadirSeguidor(Usuario usuario) {
		if (seguidores.contains(usuario)) return;

		seguidores.add(usuario);
	}
    
    /**
     * Funcion que calcula si un Usuario es mayor de edad para reproducir contenido explicito
     * 
     * @return true si puede reproducir contenido explicito o false si no
     */
    public boolean puedeContenidoExplicito() {    	
    	Calendar fechaNacimientoMinima = new GregorianCalendar();
    	fechaNacimientoMinima.add(Calendar.YEAR, -18);
    	
    	if (fechaNacimiento.after(fechaNacimientoMinima)) return false;
    	
    	return true;   	
    }
}