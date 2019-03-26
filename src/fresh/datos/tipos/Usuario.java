package fresh.datos.tipos;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;

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
	
	private Set<Notificacion> notificaciones = new HashSet<>();
	private Set<ListaReproduccion> listasReproduccion = new HashSet<>();
	private Set<Cancion> canciones = new HashSet<>();
	private Set<Album> albumes = new HashSet<>();
	private Set<Usuario> seguidores = new HashSet<>();
	
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
	 * Devuelve la lista de notificaciones de un usuario
	 * 
	 * @return Lista de notificaciones del usuario
	 */
	public Set<Notificacion> getNotificaciones(){
		return Collections.unmodifiableSet(notificaciones);
	}

	public void anadirNotificacion(Notificacion notificacion) {
		notificaciones.add(notificacion);
	}

	public void eliminarNotificacion(Notificacion notificacion) {
		notificaciones.remove(notificacion);
	}

	public Set<ListaReproduccion> getListasReproducion() {
		return Collections.unmodifiableSet(listasReproduccion);
	}
	
	public void anadirListaReproduccion(ListaReproduccion listaReproduccion) {
		listasReproduccion.add(listaReproduccion);
	}

	public void eliminarListaReproduccion(ListaReproduccion listaReproduccion) {
		listasReproduccion.remove(listaReproduccion);
	}
	
	/**
	 * Devuelve la lista de canciones del usuario
	 * 
	 * @return Lista de canciones del usuario
	 */
	public Set<Cancion> getCanciones(){
		return Collections.unmodifiableSet(canciones);
	}

	public void anadirCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	public void eliminarCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

	/**
	 * Devuelve la lista de canciones de un usuario
	 * 
	 * @return Lista de Albumes de un usuario
	 */
    public Set<Album> getAlbumes(){
    	return Collections.unmodifiableSet(albumes);
	}
 
	public void anadirAlbum(Album album) {
		albumes.add(album);
	}

	public void eliminarAlbum(Album album) {
		albumes.remove(album);
	}
	
	public Set<Usuario> getSeguidores() {
		return Collections.unmodifiableSet(seguidores);
	}

	public void anadirSeguidor(Usuario usuario) {
		seguidores.add(usuario);
	}

	public void eliminarSeguidor(Usuario usuario) {
		seguidores.remove(usuario);
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