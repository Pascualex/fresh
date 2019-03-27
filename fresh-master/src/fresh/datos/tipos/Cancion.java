package fresh.datos.tipos;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

/**
 * <p>Esta clase permite trabajar con canciones, que heredan de elemento 
 *    reproducible.</p>
 */
public class Cancion extends ElementoReproducible {
    private long id;
    private int reproduccionesMensuales;
    private Usuario autor;
    private boolean bloqueado;
    private EstadoCancion estado;

    /**
     * Crea una canción dadas sus características.
     * @param nombre Nombre de la canción
     * @param duracion Duración en segundos de la canción
     * @param autor Usuario autor de la cancion
     * @param id Identificador único de la canción
     */
    public Cancion(String nombre, long duracion, Usuario autor, long id) {
        super(nombre, duracion);
        this.autor = autor;
        this.id = id;
        reproduccionesMensuales = 0;
        bloqueado = false;
        estado = EstadoCancion.PENDIENTE_VALIDACION;
    }

    /**
     * Establece el estado de bloqueo de la canción.
     * @param bloqueado Nuevo estado de bloqueo de la canción
     */
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * Devuelve el usuario autor de la canción.
     * @return Usuario autor de la canción
     */
    public Usuario getAutor() {
        return autor;
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
     * Devuelve el estado de bloqueo de la canción.
     * @return Estado de bloqueo de la canción
     */
    @Override
    public boolean getBloqueado() {
        return bloqueado;
    }

    /**
     * Establece el número de reproducciones mensuales de la canción.
     * @param reproduccionesMensuales Reproducciones mensuales de la canción
     */
    public void setReproduccionesMensuales(int reproduccionesMensuales) {
        this.reproduccionesMensuales = reproduccionesMensuales;
    }

    /**
     * Devuelve una lista con la canción.
     * @return Lista de canciones con la canción
     */
    @Override
    public Set<Cancion> getCanciones() {
        Set<Cancion> canciones = new HashSet<>();
        canciones.add(this);
        return canciones;
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
        return Objects.equals(cancion.getNombre(), getNombre());
    }
}