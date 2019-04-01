package fresh.datos.tipos;

import fresh.sistema.Sistema;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>Esta clase permite trabajar con álbumes, que heredan de elemento
 *    reproducible.</p>
 */
public class Album extends ElementoReproducible {
    private static final long serialVersionUID = Sistema.numeroVersion;
    
    private List<Cancion> canciones;
    private Usuario autor;
    private int ano;

    /**
     * Crea un álbum dadas sus características.
     * @param nombre Nombre del álbum
     * @param Autor Usuario autor del álbum
     * @param ano Año de publicación del álbum
     * @param canciones Lista de canciones que componen el álbum
     */
    public Album(String nombre, Usuario autor, int ano, List<Cancion> canciones) {
        super(nombre, 0);
        this.autor = autor;
        this.ano = ano;
        this.canciones = new ArrayList<>();

        for (Cancion cancion : canciones) {
            if (this.canciones.contains(cancion)) continue;
            setDuracion(getDuracion()+cancion.getDuracion());            
            this.canciones.add(cancion);
        }
    }

    /** 
     * Devuelve el usuario autor del álbum.
     * @return Usuario autor del álbum.
     */
    public Usuario getAutor() {
        return autor;
    }

    /**
     * Devuelve el año de publicación del álbum.
     * @return Año de publicación del álbum.
     */
    public int getAno() {
        return ano;
    }

    /** 
     * Devuelve el estado de bloqueo del álbum. Basado en el estado de bloqueo
     * de las canciones que lo componen.
     * @return "true" si alguna de sus canciones está bloqueada. "false" en caso
     * contrario.
     */
    @Override
    public boolean getBloqueado() {
        for (Cancion cancion : canciones) {
            if (cancion.getBloqueado()) return true;
        }
        return false;
    }

    /**
     * Devuelve una lista con todas las canciones del álbum.
     * @return Lista con todas las canciones del álbum.
     */
    @Override
    public List<Cancion> getCanciones() {
        return Collections.unmodifiableList(canciones);
    }

    /**
     * Elimina una canción del album.
     * @param cancion Canción a eliminar
     */
    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion);
    }
}