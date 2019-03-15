package fresh.datos.tipos;

// ¿Cómo planteamos el bloqueo de listas de reproducción?

/**
 * <p>Esta clase abstracta define los componentes elementales de todo conjunto
 *    reproducible.</p>
 */
public abstract class ConjuntoReproducible extends ElementoReproducible {
    private int cancionesBloqueadas;

    /**
     * Crea un conjunto reproducible dadas sus características.
     * @param nombre Nombre del conjunto reproducible
     */
    public ConjuntoReproducible(String nombre) {
        super(nombre, 0);
        cancionesBloqueadas = 0;
    }

    /**
     * Indica si el conjunto reproducible está bloqueado o no.
     * @return "true" si las canciones bloqueadas son 0. "false" en caso
     *         contrario.
     */
    public boolean getBloqueado() {
        return cancionesBloqueadas == 0;
    }

    /**
     * Devuelve el número de canciones bloqueadas  en el conjunto reproducible.
     * @return Número de canciones bloqueadas  en el conjunto reproducible
     */
    public int getCancionesBloqueadas() {
        return cancionesBloqueadas;
    }

    /**
     * Establece el número de canciones bloqueadas en el conjunto reproducible.
     * @param cancionesBloqueadas Canciones bloqueadas en el conjunto 
     *                            reproducible
     */
    public void setCancionesBloqueadas(int cancionesBloqueadas) {
        this.cancionesBloqueadas = cancionesBloqueadas;
    }
}