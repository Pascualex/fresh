package fresh.datos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import fresh.datos.tipos.*;

public class BaseDeDatos {
    private String directorioDatos;

    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Reporte> reportes = new ArrayList<>();
    private Map<String, Cancion> canciones = new HashMap<>();
    private Map<Long, Album> albumes = new HashMap<>();

    public static BaseDeDatos cargarBaseDeDatos(String ruta) throws IOException, ClassNotFoundException {
        ObjectInputStream stream;
        try {
            stream = new ObjectInputStream(new FileInputStream(ruta));
        } catch (FileNotFoundException e) {
            return new BaseDeDatos(ruta);
        }
        BaseDeDatos baseDeDatos = (BaseDeDatos) stream.readObject();
        stream.close();
        return baseDeDatos;
    }

    private BaseDeDatos(String ruta) {
        this.directorioDatos = ruta;
    }

    public Status anadirUsuario(Usuario usuario) {
        Usuario aux = usuarios.get(usuario.getNombre());
        if (aux == null) {
            usuarios.put(usuario.getNombre(), usuario);
            return Status.OK;   
        }
        return Status.USUARIO_REPETIDO;
    }

    public Usuario buscarUsuario(String nombre) {
        return usuarios.get(nombre);
    }



    public void eliminarPremiumUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            usuario.setEsPremium(false);
        }
    }

    public Status anadirCancion(Cancion cancion) {
        Cancion aux = canciones.get(cancion.getNombre());
        if (aux == null) {
            canciones.put(cancion.getNombre(), cancion);
            return Status.OK;   
        }
        return Status.CANCION_REPETIDA;
    }

    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion.getNombre());
    }
}