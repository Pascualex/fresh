package fresh.datos.tipos;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Esta clase permite trabajar con álbumes, que heredan de elemento
 *    reproducible.</p>
 */
public class Album extends ElementoReproducible {
    private List<Cancion> canciones;
    private Usuario autor;
    private int ano;

    /**
     * Crea un álbum dadas sus características.
     * @param nombre Nombre del álbum
     * @param Autor Usuario autor del álbum
     * @param ano Año de publicación del álbum
     */
    public Album(String nombre, Usuario autor, int ano, List<Cancion> canciones) {
        super(nombre, 0);
        this.autor = autor;
        this.ano = ano;
        this.canciones = canciones;
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

    /**
     * Devuelve una lista de canciones con todas las canciones del álbum.
     * @return Lista de canciones con todas las canciones del álbum
     */
    @Override
    public List<Cancion> getCanciones() {
        List<Cancion> lista = new ArrayList<>();
        lista.addAll(canciones);
        return lista;
    }
}