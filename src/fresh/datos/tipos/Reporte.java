package fresh.datos.tipos;

/**
 * Implementacion de la clase Reporte
 *
 * @author Ana Roa (ana.roa@estudiante.uam.es)
 */
public class Reporte {

    private String descripcion;
    private Usuario reportador;
    private Cancion cancionReportada;

    /**
     * Constructor de la clase Reporte
     *
     * @param descripcion cadena con una descripcion de porque se reporta una cancion
     * @param reportador usuario que reporta una cancion
     * @param reportada cancion que ha sido reportada por plagio
     */

    public Reporte(String descripcion, Usuario reportador, Cancion cancionReportada){
        this.descripcion = descripcion;
        this.reportador = reportador;
        this.cancionReportada = cancionReportada;
    }

    /**
	 * Devuelve la descripcion de un reporte
	 * 
	 * @return cadena con la descripcion de un reporte
	 */
    public String getDescripcion() { 
        return descripcion;
    }

    /**
	 * Devuelve el usuario que ha realizado el reporte
	 * 
	 * @return usuario que realizo el reporte
	 */
    public Usuario getReportador() {
        return reportador;
    }

    /**
	 * Devuelve la cancion que ha sido reportada
	 * 
	 * @return cancion que ha sido reportada
	 */
    public Cancion getCancionReportada() {
        return cancionReportada;
    }

    
}