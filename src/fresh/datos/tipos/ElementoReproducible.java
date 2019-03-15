package fresh.datos.tipos;

/**
 * <p>Esta clase abstracta define los componentes elementales de todo elemento
 *    reproducible.</p>
 */
public abstract class ElementoReproducible {
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
     * Devuelve el nombre del elemento reproducible
     * @return Nombre del elemento reproducible
     */
    public String getNombre() {
        return nombre;
    }

    /** 
     * Devuelve la duración en segundos del elemento reproducible
     * @return Duración en segundos del elemento reproducible
     */
    public long getDuracion() {
        return duracion;
    }
}