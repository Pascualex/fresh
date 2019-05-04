package fresh.datos.tipos;

import fresh.sistema.Sistema;
import fresh.Status;

import java.util.List;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.io.Serializable;

/**
 * Permite trabajar con usuarios, conservando sus credenciales, su propio 
 * contenido y sus preferencias en el contenido de otros usuarios.
 */
public class Usuario implements Serializable {	
	private static final long serialVersionUID = 2;
	
	private String nombre;
	private String nombreAutor;
	private String contrasena;
	private Calendar fechaNacimiento;

	private boolean premium;
	private boolean bloqueado;
	private int reproduccionesMensuales;
	
	private List<Notificacion> notificaciones = new LinkedList<>();
	private List<ListaReproduccion> listasReproduccion = new LinkedList<>();
	private List<Cancion> canciones = new LinkedList<>();
	private List<Album> albumes = new LinkedList<>();
	private List<Usuario> seguidores = new LinkedList<>();

	/**
	 * Instancia un usuario dadas sus características.
	 * @param nombre Nombre del usuario
	 * @param nombreAutor Nombre de autor del usuario
	 * @param contrasena Contraseña con la que se registra el usuario
	 * @param fechaNacimiento Fecha de nacimento del usuario
	 */
	public Usuario(String nombre, String nombreAutor, String contrasena, Calendar fechaNacimiento) {
		this.nombre = nombre;
		this.nombreAutor = nombreAutor;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		premium = false;
		reproduccionesMensuales = 0;
		bloqueado = false;
	}

	/**
	 * Indica si el usuario es premium.
	 * @return "true" si el usuario es premium y "false" si no.
	 */
	public boolean getPremium(){
		return premium;
	}

	/**
	 * Modifica el estado premium del usuario.
	 * @param premium Nuevo estado premium del usuario
	 */
	public void setPremium(boolean premium){
		this.premium = premium;
	}

	/**
	 * Devuelve el nombre del usuario.
	 * @return Nombre del usuario.
	 */
	public String getNombre(){
		return nombre;
	}

	/**
	 * Devuelve el nombre de autor del usuario.
	 * @return Nombre de autor del usuario.
	 */
	public String getNombreAutor(){
		return nombreAutor;
	}

	/**
	 * Devuelve la contraseña del usuario.
	 * @return Contraseña del usuario.
	 */
	public String getContrasena(){
		return contrasena;
	}
	
	/**
	 * Devuelve la fecha de nacimiento del usuario.
	 * @return Fecha de nacimiento del usuario.
	 */
	public Calendar getFechaNacimiento(){
		return fechaNacimiento;
	}
	
	/**
	 * Devuelve si el usuario está bloqueado o no.
	 * @return "true" si el usuario está bloqueado y "false" si no.
	 */
	public boolean getBloqueado(){
		return bloqueado;
	}
	
	/**
	 * Modifica el estado de bloqueo del usuario.
	 * @param bloqueado Nuevo estado de bloqueo del usuario
	 */
	public void setBloqueado(boolean bloqueado){
		this.bloqueado = bloqueado;

		if (bloqueado) {
			for (Cancion cancion : canciones) {
				cancion.setEstado(EstadoCancion.BLOQUEADA_PERMANENTE);
			}
		}

		return;
	}

	/**
	 * Devuelve las reproducciones mensuales del usuario.
	 * @return Reproducciones mensuales del usuario.
	 */
	public int getReproduccionesMensuales(){
		return reproduccionesMensuales;
	}
	
	/**
	 * Actualiza las reproducciones mensuales del usuario.
	 * @param reproduccionesMensuales Nuevas reproducciones mensuales
	 */
	public void setReproduccionesMensuales(int reproduccionesMensuales) {
		this.reproduccionesMensuales = reproduccionesMensuales;
	}
	
	/**
	 * Devuelve la lista de notificaciones del usuario.
	 * @return Lista de notificaciones del usuario.
	 */
	public List<Notificacion> getNotificaciones(){
		return Collections.unmodifiableList(notificaciones);
	}

	/**
	 * Añade una notificacion a la lista de notificaciones del usuario.
	 * @param notificacion Notificación a añadir a la lista
	 */
	public void anadirNotificacion(Notificacion notificacion) {
		notificaciones.add(notificacion);
	}
	
	/**
	 * Elimina una notificacion de la lista de notificaciones del usuario.
	 * @param notificacion Notificación a eliminar de la lista
	 */
	public void eliminarNotificacion(Notificacion notificacion) {
		notificaciones.remove(notificacion);
	}

	/**
	 * Devuelve la lista de listas de reproduccion del usuario.
	 * @return Lista de listas de reproduccion del usuario.
	 */
	public List<ListaReproduccion> getListasReproducion() {
		return Collections.unmodifiableList(listasReproduccion);
	}

	/**
	 * Añade una lista de reproduccion a la lista de listas del usuario.
	 * @param listaReproduccion Lista de reproducción a añadir
	 * @return "LISTA_REPRODUCCION_REPETIDA" si la lista ya está presente.
	 * "OK" en caso contario.
	 */
	public Status anadirListaReproduccion(ListaReproduccion listaReproduccion) {
		if (listasReproduccion.contains(listaReproduccion)) return Status.LISTA_REPRODUCCION_REPETIDA;

		listasReproduccion.add(listaReproduccion);
		return Status.OK;
	}

	/**
	 * Elimina una lista de reproducción de la lista de listas del usuario.
	 * @param listaReproduccion Lista de reproducción a eliminar
	 */
	public void eliminarListaReproduccion(ListaReproduccion listaReproduccion) {
		listasReproduccion.remove(listaReproduccion);
	}

	/**
	 * Devuelve la lista de canciones del usuario
	 * @return Lista de canciones del usuario
	 */
	public List<Cancion> getCanciones(){
		return Collections.unmodifiableList(canciones);
	}

	/**
	 * Añade una cancion a la lista de canciones del usuario
	 * @param cancion Canción a añadir
	 */
	public void anadirCancion(Cancion cancion) {
		canciones.add(cancion);
	}

	/**
	 * Elimina una cancion de la lista de canciones del usuario
	 * @param cancion Canción a eliminar
	 */
	public void eliminarCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

	/**
	 * Devuelve la lista de álbumes del usuario
	 * @return Lista de álbumes del usuario
	 */
    public List<Album> getAlbumes(){
    	return Collections.unmodifiableList(albumes);
	}

	/**
	 * Añade un album a la lista de álbumes del usuario
	 * @param album Album a añadir
	 */
	public void anadirAlbum(Album album) {
		albumes.add(album);
	}

	/**
	 * Elimina un album del conjunto de álbumes del usuario
	 * @param album Album a eliminar
	 */
	public void eliminarAlbum(Album album) {
		albumes.remove(album);
	}

	/**
	 * Devuelve el conjunto de seguidores del usuario
	 * @return Conjunto de seguidores del usuario
	 */
	public List<Usuario> getSeguidores() {
		return Collections.unmodifiableList(seguidores);
	}

	/**
	 * Añade a un seguidor al conjunto de seguidores del usuario
	 * @param usuario Seguidor a añadir
	 */
	public void anadirSeguidor(Usuario usuario) {
		seguidores.add(usuario);
	}

	/**
	 * Elimina a un seguidor del conjunto de seguidores del usuario
	 * @param usuario Seguidor a eliminar
	 */
	public void eliminarSeguidor(Usuario usuario) {
		seguidores.remove(usuario);
	}

    /**
     * Calcula si un usuario puede reproducir contenido explicito.
     * @return "true" si el usuario tiene más de 18 años.
	 * "false" en caso contrario.
     */
    public boolean puedeContenidoExplicito() {    	
    	Calendar fechaNacimientoMinima = new GregorianCalendar();
    	fechaNacimientoMinima.add(Calendar.YEAR, -18);
    	
    	if (fechaNacimiento.after(fechaNacimientoMinima)) return false;
    	
    	return true;
	}	
}