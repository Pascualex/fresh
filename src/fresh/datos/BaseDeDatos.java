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
        this.directorioDatos = ruta;
    }

    public Status anadirUsuario(Usuario usuario) {
        if (usuarios.get(usuario.getNombre()) != null) 
            return Status.USUARIO_REPETIDO; 

        usuarios.put(usuario.getNombre(), usuario);
        return Status.OK;
    }

    public Usuario buscarUsuario(String nombre) {
        return usuarios.get(nombre);
    }

    public List<Usuario> buscaUsuarios(String nombre) {
        //Por implementar
    }


    public void eliminarPremiumUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            usuario.setEsPremium(false);
        }
    }

    public Status anadirCancion(Cancion cancion) {
        if (canciones.get(cancion.getNombre()) != null) 
            return Status.CANCION_REPETIDA;

        canciones.put(cancion.getNombre(), cancion);
        return Status.OK;
    }

    public List<Cancion> buscarCanciones(String nombre) {
        //Por implementar
    }

    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion.getNombre());
    }

    public Status anadirAlbum(Album album) {
        if (albumes.get(album.getNombre()) != null) 
            return Status.ALBUM_REPETIDO;

        albumes.put(album.getNombre(), album);
        return Status.OK;
    }

    public List<Album> buscarAlbumes(String nombre) {
        //Por implementar
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
}