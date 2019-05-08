package fresh.datos.tipos;

import fresh.sistema.Sistema;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

/**
 * <p>Esta clase permite trabajar con listas de reproducción, que heredan de 
 * elemento reproducible.</p>
 */
public class ListaReproduccion extends ElementoReproducible {
    private static final long serialVersionUID = Sistema.numeroVersion;
    
    private List<ElementoReproducible> elementos = new LinkedList<>();

    /**
     * Crea una lista de reproducción dadas sus características.
     * @param nombre Nombre de la lista de reproducción
     */
    public ListaReproduccion(String nombre) {
        super(nombre, 0);
    }

    /**
     * Añade a la lista de reproducción el elemento pasado por argumento e 
     * incrementa la duración de esta, si el elemento no está ya presente.
     * @param elemento Elemento reproducible a añadir a la lista
     */
    public void anadirElemento(ElementoReproducible elemento) {
        if (elementos.contains(elemento)) return;

        setDuracion(getDuracion()+elemento.getDuracion());
        elementos.add(elemento);
    }

    /**
     * Elimina de la lista de reproducción el elemento pasado por argumento y 
     * decrementa la duración de la lista, si el elemento está presente.
     * @param elemento Elemento reproducible a eliminar de la lista
     */
    public void eliminarElemento(ElementoReproducible elemento) {
        if (!elementos.contains(elemento)) return;

        setDuracion(getDuracion()-elemento.getDuracion());
        elementos.remove(elemento);
    }

    /**
     * Indica si el elemento pasado como argumento es esta lista o es contenido
     * de manera directa por esta.
     * @param elemento Elemento reproducible a comprobar
     * @return "true" si el elemento es esta lista o es contenido de manera
     * directa por esta. "false" en caso contrario.
     */
    public boolean contieneElemento(ElementoReproducible elemento) {
        if (elemento.equals(this)) return true;

        for (ElementoReproducible e : elementos) {
            if (e.equals(elemento)) return true;
        }

        return false;
    }

    /** 
     * Devuelve el estado de bloqueo de la lista de reproducción. Basado en el 
     * estado de bloqueo de los elementos que la componen.
     * @return "true" si alguno de sus elementos reproducibles está bloqueado o
     * si está vacía. "false" en caso contrario.
     */
    @Override
    public boolean getBloqueado() {
        if (elementos.size() == 0) return true;

        for (ElementoReproducible elemento : elementos) {            
            if (elemento.getBloqueado()) return true;
        }

        return false;
    }

    /**
     * Devuelve una lista con todas las canciones de la lista de reproducción.
     * Para ello llama al método principal crean un set de elementos excluidos.
     * @return Lista con todas las canciones de la lista de reproducción.
     */
    @Override
    public List<Cancion> getCanciones() {
        return getCanciones(new HashSet<>());
    }

    /**
     * Devuelve una lista con todas las canciones de la lista de reproducción.
     * Propaga la lista de elementos a excluir para no repetir canciones y 
     * evitar bucles con otras listas de reproducción.
     * @param elementosExcluidos Conjunto de elementos que se deben excluir
     * @return Lista con todas las canciones de la lista de reproducción.
     */
    @Override
    public List<Cancion> getCanciones(Set<ElementoReproducible> elementosExcluidos) {
        if (elementosExcluidos.contains(this)) return new LinkedList<>();
        elementosExcluidos.add(this);
        
        List<Cancion> canciones = new LinkedList<>();
        for (ElementoReproducible e : elementos) {        	
            canciones.addAll(e.getCanciones(elementosExcluidos));
        }

        return canciones;        
    }

    /** 
     * Devuelve la duración en segundos de la lista de reproducción
     * @return Duración en segundos de la lista de reproducción.
     */
    @Override
    public long getDuracion() {
        List<Cancion> canciones = getCanciones();

        long duracionTotal = 0;
        for (Cancion c : canciones) {
            duracionTotal += c.getDuracion();
        }

        return duracionTotal;
    }

    /**
     * Indica si el objeto pasado equivale a esta lista de reproducción.
     * @param obj Objecto a comparar con la lista de reproducción
     * @return "true" si tienen el mismo nombre. "false" en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListaReproduccion)) return false;

        ListaReproduccion listaReproduccion = (ListaReproduccion) obj;

        return listaReproduccion.getNombre().equals(getNombre());
    }
}