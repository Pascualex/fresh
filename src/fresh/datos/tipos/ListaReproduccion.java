package fresh.datos.tipos;

import java.util.List;

import fresh.Status;

import java.util.ArrayList;

/**
 * <p>Esta clase permite trabajar con listas de reproducción, que heredan de 
 * elemento reproducible.</p>
 */
public class ListaReproduccion extends ElementoReproducible {
    private List<ElementoReproducible> elementos = new ArrayList();

    /**
     * Crea una lista de reproducción dadas sus características.
     * @param nombre Nombre de la lista de reproducción
     */
    public ListaReproduccion(String nombre) {
        super(nombre, 0);
    }

    /**
     * Añade a la lista de reproducción el elemento pasado por argumento, si no 
     * está ya presente, e incrementa la duración de la lista.
     * @param elemento Elemento reproducible a añadir a la lista
     * @return Status notificando posibles problemas en la ejecución.
     */
    public Status anadirElemento(ElementoReproducible elemento) {
        if (elementos.contains(elemento)) return Status.ELEMENTO_REPETIDO;

        setDuracion(getDuracion()+elemento.getDuracion());
        elementos.add(elemento);

        return Status.OK;
    }

    /**
     * Elimina de la lista de reproducción el elemento pasado por argumento, si 
     * está presente, y decrementa la duración de la lista
     * @param elemento Elemento reproducible a eliminar de la lista
     */
    public void eliminarElemento(ElementoReproducible elemento) {
        if (elementos.contains(elemento)) {
            setDuracion(getDuracion()-elemento.getDuracion());
            elementos.remove(elemento);
        }
    }

    /** 
     * Devuelve el estado de bloqueo de la lista de reproducción. Basado en el 
     * estado de bloqueo de los elementos que la componen.
     * @return "true" si alguno de sus elementos reproducibles está bloqueado. 
     * "false" en caso contrario
     */
    @Override
    public boolean getBloqueado() {
        for (ElementoReproducible elemento : elementos) {            
            if (elemento.getBloqueado()) return true;
        }
        return false;
    }
}