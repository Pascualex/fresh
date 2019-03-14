package fresh.datos.tipos;

// Clase no comentada porque confío en que haya una mejor manera de gestionar
// los bloqueos - Alejandro

public abstract class ConjuntoReproducible extends ElementoReproducible {
    private int cancionesBloqueadas;

    public ConjuntoReproducible(String name, int duracion) {
        super(name, duracion);
        cancionesBloqueadas = 0;
    }

    public int getCancionesBloqueadas() {
        return cancionesBloqueadas;
    }

    public void setCancionesBloqueadas(int cancionesBloqueadas) {
        this.cancionesBloqueadas = cancionesBloqueadas;
    }
}