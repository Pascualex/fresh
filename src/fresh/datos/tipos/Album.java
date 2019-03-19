package fresh.datos.tipos;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>Esta clase permite trabajar con álbumes, que heredan de elemento
 *    reproducible.</p>
 */
public class Album extends ElementoReproducible {
    private List<Cancion> canciones = new ArrayList();
    private Usuario autor;
    private int ano;

    /**
     * Crea un álbum dadas sus características.
     * @param nombre Nombre del álbum
     * @param Autor Usuario autor del álbum
     * @param ano Año de publicación del álbum
     */
    public Album(String nombre, Usuario autor, int ano) {
        super(nombre, 0);
        this.autor = autor;
        this.ano = ano;
    }

    /**
     * Añade al álbum la canción pasada por argumento, si no está ya presente, e
     * incrementa la duración del álbum.
     * @param cancion Canción a añadir al álbum
     */
    public void anadirCancion(Cancion cancion) {
        if (!canciones.contains(cancion)) {
            setDuracion(getDuracion()+cancion.getDuracion());
            canciones.add(cancion);
        }
    }

    /**
     * Elimina del álbum la canción pasada por argumento, si está presente, y
     * decrementa la duración del álbum.
     * @param cancion Canción a eliminar del álbum
     */
    public void eliminarCancion(Cancion cancion) {
        if (canciones.contains(cancion)) {
            setDuracion(getDuracion()-cancion.getDuracion());
            canciones.remove(cancion);
        }
    }

    /** 
     * Devuelve el usuario autor del álbum.
     * @return Usuario autor del álbum
     */
    public Usuario getAutor() {
        return autor;
    }

    /**
     * Devuelve el año de publicación del álbum.
     * @return Año de publicación del álbum
     */
    public int getAno() {
        return ano;
    }

    /** 
     * Devuelve el estado de bloqueo del álbum. Basado en el estado de bloqueo
     * de las canciones que lo componen.
     * @return "true" si alguna de sus canciones está bloqueada. "false" en caso
     * contrario
     */
    @Override
    public boolean getBloqueado() {
        for (Cancion cancion : canciones) {
            if (cancion.getBloqueado()) return true;
        }
        return false;
    }
}