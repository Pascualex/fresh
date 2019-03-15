package fresh.datos.tipos;

/**
 * <p>Esta clase permite trabajar con canciones, que heredan de elemento 
 *    reproducible.</p>
 */
public class Cancion extends ElementoReproducible {
    private long id;
    private int reproduccionesMensuales;

    /**
     * Crea una canción dadas sus características.
     * @param nombre Nombre de la canción
     * @param duracion Duración en segundos de la canción
     * @param id Identificador único de la canción
     */
    public Cancion(String nombre, long duracion, long id) {
        super(nombre, duracion);
        this.id = id;
        reproduccionesMensuales = 0;
    }

    /**
     * Devuelve el identificador único de la canción.
     * @return Identificador único de la canción
     */
    public long getId() {
        return id;
    }

    /** 
     * Devuelve el número de reproducciones mensuales de la canción.
     * @return Reproducciones mensuales de la canción
     */
    public int getReproduccionesMensuales() {
        return reproduccionesMensuales;
    }

    /**
     * Establece el número de reproducciones mensuales de la canción.
     * @param reproduccionesMensuales Reproducciones mensuales de la canción
     */
    public void setReproduccionesMensuales(int reproduccionesMensuales) {
        this.reproduccionesMensuales = reproduccionesMensuales;
    }

    /**
     * Compara el objeto pasado como argumento con la instancia de la canción.
     * @param object Objeto a comparar con la instancia de la canción
     * @return "true" si el objeto pasado como argumento es una instancia de la
     *         clase Cancion y su id es la misma que la de esta instancia.
     *         "false" en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cancion)) return false;

        Cancion cancion = (Cancion) object;
        return this.id == cancion.id;
    }
}