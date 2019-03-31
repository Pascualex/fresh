package fresh.datos;

import fresh.datos.tipos.*;
import fresh.Status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * <p>Esta clase permite trabajar con una base de datos de la mayoría de información 
 * relavante de la aplicación.</p>
 */
public class BaseDeDatos implements Serializable {
    static final long serialVersionUID = 0;
    
    private String ruta;
    private long idSiguienteCancion;

    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Reporte> reportes = new ArrayList<>();
    private Map<String, Cancion> canciones = new HashMap<>();
    private Map<String, Album> albumes = new HashMap<>();

    /**
     * Inicializa una base de datos dada la ruta de la que debería cargar los 
     * datos. Si no puede cargar los datos en la ruta especificada devuelve
     * una base de datos nueva.
     * @param ruta Ruta de la base de datos
     * @return Base de datos con la información cargada
     */
    public static BaseDeDatos cargarBaseDeDatos(String ruta) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(ruta))) {
            BaseDeDatos baseDeDatos = (BaseDeDatos) stream.readObject();
            stream.close();
            return baseDeDatos;
        } catch (FileNotFoundException e) {
            return new BaseDeDatos(ruta);
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private BaseDeDatos(String ruta) {
        this.ruta = ruta;
        idSiguienteCancion = 0;
    }

    /**
     * Añade un usuario a la base de datos
     * @param usuario Usuario a añadir
     */
    public Status anadirUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getNombreAutor())) return Status.USUARIO_REPETIDO; 

        usuarios.put(usuario.getNombreAutor(), usuario);
        return Status.OK;
    }

    /**
     * Busca un usuario en la base de datos.
     * @param nombreAutor Nombre de autor del usuario a buscar
     * @return Usuario con el nombre de autor especificado
     */
    public Usuario buscarUsuario(String nombreAutor) {
        return usuarios.get(nombreAutor);
    }

    /**
     * Busca usuarios en la base de datos dado su nombre
     * @param nombreAutor Nombre a partir del que se buscará
     * @return Lista con los usuarios cuyos nombres de autor contienen el pasado
     * por argumento 
     */
    public List<Usuario> buscarUsuarios(String nombreAutor) {
        List<Usuario> lista = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getBloqueado()) continue;
            if (!usuario.getNombreAutor().contains(nombreAutor)) continue;
            lista.add(usuario);
        }
        return lista;
    }

    /**
     * Elimina el premium a todos los usuarios de la base de datos.
     */
    public void eliminarPremiumUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            usuario.setPremium(false);
            usuario.anadirNotificacion(new Notificacion(TipoNotificacion.PREMIUM_CADUCADO));
        }
    }

    /**
     * Añade una canción a la base de datos.
     * @param cancion Cancion a añadir
     * @return Status marcando el resultado de la operación. Este puede se OK si
     * la operación se ha efectuado con éxito, o CANCION_REPETIDA, si la canción
     * ya estaba contenida en la base de datos, y por lo tanto no se ha vuelto 
     * a añadir
     */
    public Status anadirCancion(Cancion cancion) {
        if (canciones.containsKey(cancion.getNombre())) return Status.CANCION_REPETIDA;

        canciones.put(cancion.getNombre(), cancion);
        idSiguienteCancion++;
        return Status.OK;
    }

    /**
     * Busca canciones en la base de datos a partir de su nombre.
     * @param nombre Nombre a partir del cual buscar
     * @param explicito "true" si los resultados pueden ser de contenido explícito,
     * "false" si no
     * @return Lista de canciones con las canciones cuyo nombre contiene el pasado
     * por argumento
     */
    public List<Cancion> buscarCanciones(String nombre, boolean explicito) {
        List<Cancion> lista = new ArrayList<>();
        for (Cancion cancion : canciones.values()) {
            if (cancion.getBloqueado()) continue;
            if (!cancion.getNombre().contains(nombre)) continue;
            if (cancion.getEstado() == EstadoCancion.VALIDADA_EXPLICITA && !explicito) continue;
            lista.add(cancion);
        }
        return lista;
    }

    /**
     * Elimina una cancion de la base de datos, borrando completamente sus referencias
     * de los álbumes y las playlists que la contuviesen.
     * @param cancion Cancion a eliminar
     */
    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion.getNombre());
        cancion.getAutor().eliminarCancion(cancion);
        for (Album album : cancion.getAutor().getAlbumes()) {
            album.eliminarCancion(cancion);
        }
        for (Usuario usuario : usuarios.values()) {
            for (ListaReproduccion listaReproduccion : usuario.getListasReproducion()) {
                listaReproduccion.eliminarElemento(cancion);
            }
        }
    }

    /**
     * Añade un álbum a la base de datos
     * @param album Álbum a añadir
     * @return STATUS que puede ser OK, si el álbum se ha podido añadir correctamente,
     * o ALBUM_REPETIDO, si el álbum ya estaba contenido en la base de datos y por
     * lo tanto no se ha vuelto a añadir 
     */
    public Status anadirAlbum(Album album) {
        if (albumes.containsKey(album.getNombre())) return Status.ALBUM_REPETIDO;

        albumes.put(album.getNombre(), album);
        return Status.OK;
    }

    /**
     * Busca álbumes en la base de datos por su nombre.
     * @param nombre Nombre con el que efectuar la búsqueda
     * @return Lista con los álbumes cuyo nombre contiene el pasado por argumento
     */
    public List<Album> buscarAlbumes(String nombre) {
        List<Album> lista = new ArrayList<>();
        for (Album album : albumes.values()) {
            if (album.getNombre().contains(nombre)) {
                lista.add(album);
            }
        }
        return lista;
    }

    /**
     * Elimina un álbum de la base de datos, eliminando a su vez todas las referencias
     * que otros elementos tienen de él.
     * @param album Álbum a eliminar
     */
    public void eliminarAlbum(Album album) {
        albumes.remove(album.getNombre());
        album.getAutor().eliminarAlbum(album);
        for (Usuario usuario : usuarios.values()) {
            for (ListaReproduccion listaReproduccion : usuario.getListasReproducion()) {
                listaReproduccion.eliminarElemento(album);
            }
        }
    }

    /**
     * Añade un reporte a la base de datos.
     * @param reporte Reporte a añadir
     */
    public void anadirReporte(Reporte reporte) {
        reportes.add(reporte);
    }

    /**
     * Devuelve una lista con los reportes.
     * @return Reportes de la base de datos
     */
    public List<Reporte> obtenerReportes() {
        return Collections.unmodifiableList(reportes);
    }

    /**
     * Elimina un reporte de la base de datos
     * @param reporte Reporte a eliminar
     */
    public void eliminarReporte(Reporte reporte) {
        reportes.remove(reporte);
    }

    /**
     * Guarda en disco la información de la base de datos. Lo hace en la ruta 
     * especificada en el momento de la construcción de la misma.
     * @return Status que puede ser OK en caso de que el guardado se haya realizado
     * correctamente, o ERROR_CARGAR, en caso de que se haya producido una 
     * excepción en el guardado 
     */
    public Status guardarEnDisco() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            stream.writeObject(this);
            stream.close();
            return Status.OK;
        } catch (IOException e) {
            return Status.ERROR_CARGAR;
        }
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios.values();
    }

    /**
     * Devuelve la id de la siguiente canción.
     * @return Id de la siguiente canción
     */
    public long getIdSiguienteCancion() {
        return idSiguienteCancion;
    }
}