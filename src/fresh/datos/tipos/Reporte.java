package fresh.datos.tipos;

import java.io.Serializable;

/**
 * Permite trabajar con reportes, que permiten a los usuarios notificar
 * infracciones de copyright por parte de otros usuarios.
 */
public class Reporte implements Serializable {
    private String descripcion;
    private Usuario reportador;
    private Cancion cancionReportada;

    /**
     * Instancia un reporte dadas sus características.
     * @param descripcion Descripcion de porqué se reporta una canción.
     * @param reportador Usuario que reporta una canción.
     * @param cancionReportada Canción que ha sido reportada por plagio.
     */
    public Reporte(String descripcion, Usuario reportador, Cancion cancionReportada){
        this.descripcion = descripcion;
        this.reportador = reportador;
        this.cancionReportada = cancionReportada;
    }

    /**
	 * Devuelve la descripcion del reporte.
	 * @return Descripcion del reporte.
	 */
    public String getDescripcion() { 
        return descripcion;
    }

    /**
	 * Devuelve el usuario que ha realizado el reporte.
	 * @return Usuario que ha realizado el reporte.
	 */
    public Usuario getReportador() {
        return reportador;
    }

    /**
	 * Devuelve la canción que ha sido reportada.
	 * @return Canción que ha sido reportada.
	 */
    public Cancion getCancionReportada() {
        return cancionReportada;
    }
}