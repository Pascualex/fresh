package fresh.datos.tipos;

import fresh.sistema.Sistema;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.io.Serializable;

/**
 * <p>Esta clase abstracta define los componentes elementales de todo elemento
 *    reproducible.</p>
 */
public abstract class ElementoReproducible implements Serializable {
    private static final long serialVersionUID = Sistema.numeroVersion;
    
    private String nombre;
    private long duracion;

    /**
     * Crea un elemento reproducible dadas sus características.
     * @param nombre Nombre del elemento reproducible
     * @param duracion Duración en segundos del elemento reproducible
     */
    public ElementoReproducible(String nombre, long duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    /**
     * Devuelve el nombre del elemento reproducible.
     * @return Nombre del elemento reproducible
     */
    public String getNombre() {
        return nombre;
    }

    /** 
     * Devuelve la duración en segundos del elemento reproducible.
     * @return Duración en segundos del elemento reproducible
     */
    public long getDuracion() {
        return duracion;
    }

    /** 
     * Establece la duración en segundos del elemento reproducible.
     * @param duracion Duración en segundos del elemento reproducible
     */
    protected void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    /** 
     * Devuelve el estado de bloqueo del elemento reproducible.
     * @return Estado de bloqueo del elemento reproducible
     */
    public abstract boolean getBloqueado();

    /**
     * Devuelve una lista con las canciones del elemento reproducible.
     * @return Lista con las canciones del elemento reproducible
     */
    public abstract List<Cancion> getCanciones();

    /**
     * Devuelve una lista con las canciones del elemento reproducible.
     * @param elementosExcluidos Conjunto de elementos que se deben excluir
     * @return Lista con las canciones del elemento reproducible
     */
    public abstract List<Cancion> getCanciones(Set<ElementoReproducible> elementosExcluidos);
}