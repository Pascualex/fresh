package fresh.datos.tipos;

import java.util.Objects;

public class Cancion extends ElementoReproducible {
    private String nombreCompatible;
    // A lo mejor si que sería mejor tener una id numérica       
    private int reproduccionesMensuales;

    /** 
     * 
     */
    public Cancion(String nombre, int duracion) {
        super(nombre, duracion);
        // nombreCompatible = funcion que convierte nombre en nombre compatible
        reproduccionesMensuales = 0;
    }

    public String getNombreCompatible() {
        return nombreCompatible;
    }

    public int getReproduccionesMensuales() {
        return reproduccionesMensuales;
    }

    public void setReproduccionesMensuales(int reproduccionesMensuales) {
        this.reproduccionesMensuales = reproduccionesMensuales;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cancion)) return false;

        Cancion cancion = (Cancion) object;
        return Objects.equals(this.nombreCompatible, cancion.nombreCompatible);
    }
}