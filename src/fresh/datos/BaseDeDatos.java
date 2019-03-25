package fresh.datos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import fresh.datos.tipos.*;
import fresh.Status;

public class BaseDeDatos implements Serializable {
    private String directorioDatos;
    private long idSiguienteCancion;

    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Reporte> reportes = new ArrayList<>();
    private Map<String, Cancion> canciones = new HashMap<>();
    private Map<String, Album> albumes = new HashMap<>();

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
        directorioDatos = ruta;
        idSiguienteCancion = 0;
    }

    public Status anadirUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getNombreAutor())) return Status.USUARIO_REPETIDO; 

        usuarios.put(usuario.getNombreAutor(), usuario);
        return Status.OK;
    }

    public Usuario buscarUsuario(String nombreAutor) {
        return usuarios.get(nombreAutor);
    }

    public List<Usuario> buscarUsuarios(String nombreAutor) {
        List<Usuario> lista = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNombreAutor().contains(nombreAutor)) {
                lista.add(usuario);
            }
        }
        return lista;
    }

    public void eliminarPremiumUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            usuario.setEsPremium(false);
        }
    }

    public Status anadirCancion(Cancion cancion) {
        if (canciones.containsKey(cancion.getNombre())) return Status.CANCION_REPETIDA;

        canciones.put(cancion.getNombre(), cancion);
        idSiguienteCancion++;
        return Status.OK;
    }

    public List<Cancion> buscarCanciones(String nombre) {
        List<Cancion> lista = new ArrayList<>();
        for (Cancion cancion : canciones.values()) {
            if (cancion.getNombre().contains(nombre)) {
                lista.add(cancion);
            }
        }
        return lista;
    }

    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion.getNombre());
    }

    public Status anadirAlbum(Album album) {
        if (albumes.containsKey(album.getNombre())) return Status.ALBUM_REPETIDO;

        albumes.put(album.getNombre(), album);
        return Status.OK;
    }

    public List<Album> buscarAlbumes(String nombre) {
        List<Album> lista = new ArrayList<>();
        for (Album album : albumes.values()) {
            if (album.getNombre().contains(nombre)) {
                lista.add(album);
            }
        }
        return lista;
    }

    public void eliminarAlbum(Album album) {
        albumes.remove(album.getNombre());        
    }

    public void anadirReporte(Reporte reporte) {
        reportes.add(reporte);
    }

    public List<Reporte> obtenerReportes() {
        return Collections.unmodifiableList(reportes);
    }

    public void eliminarReporte(Reporte reporte) {
        reportes.remove(reporte);
    }

    public Status guardarEnDisco() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(directorioDatos))) {
            stream.writeObject(this);
            stream.close();
            return Status.OK;
        } catch (IOException e) {
            return Status.ERROR_CARGAR;
        }
    }

    public long getIdSiguienteCancion() {
        return idSiguienteCancion;
    }
}