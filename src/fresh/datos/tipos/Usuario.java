package fresh.datos.tipos;

/**
 * 
 * 	Implementación de la clase Usuario
 * 
 * 	@author 
 */

public class Usuario {
	
	private boolean esPremium;
	private String nombre;
	private String contrasena;
	private Date fechaNacimiento;
	private int reproduccionesMensuales;
	
	private List<Notificacion> notificaciones;
	private List<ListaReproduccion> listasReproduccion;
	private List<Cancion> canciones;
	private List<Album> albumes;
	
	public Usuario(boolean esPremium, String nombre, String contrasena, Date fechaNacimiento) {
		this.esPremium = esPremium;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		
	}
	
	public boolean getEsPremium(){
		return esPremium;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getContrasena(){
		return contrasena;
	}
	
	public Date getFechaNacimiento(){
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
}