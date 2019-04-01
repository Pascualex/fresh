package fresh.modulos;

import fresh.sistema.Configuracion;
import fresh.sistema.ModoEjecucion;
import fresh.datos.tipos.*;

import javazoom.jl.player.Player;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

public class ModuloMP3 implements Runnable {
    private String ruta;
    private Configuracion configuracion;
    private ModoEjecucion modoEjecucion;
    private Usuario usuarioActual;
    private int reproduccionesSesion;
    private List<Cancion> canciones;
    private boolean reproduciendo;
    private boolean nuevaCancion;
    private int posicionActual;
    private Cancion cancionActual;
    private Player player;

    /**
     * Instancia un módulo MP3 con la configuración especificada.
     * @param configuracion Configuración de la aplicación
     */
    public ModuloMP3(String ruta, Configuracion configuracion) {
        this.ruta = ruta;
        this.configuracion = configuracion;
        canciones = new ArrayList<>();
        posicionActual = -1;
        reproduciendo = false;
        nuevaCancion = false;
    }

    @Override
    public void run() {
        try {
            while (true) {       
                if (reproduciendo) {
                    if (player.play(1)) {
                        reproduciendo = false;
                        nuevaCancion = true;
                    }
                } else {
                    if (nuevaCancion) {
                        if (posicionActual < canciones.size()-1) {
                            if (modoEjecucion == ModoEjecucion.REGISTRADO) {
                                int reproduccionesMensuales = usuarioActual.getReproduccionesMensuales();
                                if (!usuarioActual.getPremium()) {
                                    if (reproduccionesMensuales >= configuracion.getMaxReproduccionesRegistrado()) continue;
                                }
                                usuarioActual.setReproduccionesMensuales(reproduccionesMensuales+1);
                            } else if (modoEjecucion == ModoEjecucion.ANONIMO) {
                                if (reproduccionesSesion >= configuracion.getMaxReproduccionesAnonimo()) continue;
                                reproduccionesSesion++;
                            }

                            posicionActual++;
                            cancionActual = canciones.get(posicionActual);

                            cancionActual.setReproduccionesMensuales(cancionActual.getReproduccionesMensuales()+1);
                            cancionActual.getAutor().setReproduccionesMensuales(cancionActual.getAutor().getReproduccionesMensuales()+1);

                            String rutaCancion = ruta + cancionActual.getId() + ".mp3";                      
                            player = new Player(new FileInputStream(rutaCancion));
                            nuevaCancion = false;
                            reproduciendo = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void anadirCanciones(List<Cancion> canciones) {
        this.canciones.addAll(canciones);
    }

    public void reproducir() {
        if (player == null) return;
        reproduciendo = true;
    }

    public void pausar() {
        reproduciendo = false;
    }

    public void reiniciar() {
        if (posicionActual < 0) return;
        reproduciendo = false;
        nuevaCancion = true;
        posicionActual--;
    }

    public void avanzar() {
        reproduciendo = false;
        nuevaCancion = true;
    }

    public void retroceder() {
        if (posicionActual < 1) return;
        reproduciendo = false;
        nuevaCancion = true;
        posicionActual = posicionActual-2;
    }

    /**
     * Establece el modo de ejecución de usuario anónimo y reinicia las
     * reproducciones de la sesión.
     */
    public void nuevaSesionAnonimo() {
        modoEjecucion = ModoEjecucion.ANONIMO;
        reproduccionesSesion = 0;
    }

    /**
     * Establece el modo de ejecución de usuario anónimo y establece las
     * reproducciones actuales de la sesión.
     * @param reproduccionesSesion Reproducciones actuales de la sesión
     */
    public void nuevaSesionAnonimo(int reproduccionesSesion) {
        modoEjecucion = ModoEjecucion.ANONIMO;
        this.reproduccionesSesion = reproduccionesSesion;
    }

    /**
     * Establece el modo de ejecución de usuario registrado y actualiza al
     * usuario actual.
     * @param usuarioActual Nuevo usuario actual
     */
    public void nuevaSesionRegistrado(Usuario usuarioActual) {
        modoEjecucion = ModoEjecucion.REGISTRADO;
        this.usuarioActual = usuarioActual;
    }

    /**
     * Calcula la duración de un fichero de audio.
     * @param fichero Archivo de audio
     * @return Duración del fichero de audio en segundos.
     */
    public long obtenerDuracion(File fichero) {
    	long sec;
    	try {
	    	AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(fichero);
	        
	        if (fileFormat instanceof TAudioFileFormat) {
	            Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
	            String key = "duration";
	            Long microseconds = (Long) properties.get(key);
	            long mili = (long) (microseconds / 1000);
	            sec = (mili / 1000) % 60;
	        } else {
	            throw new UnsupportedAudioFileException();
	        }
        } catch (UnsupportedAudioFileException e) {
        	return -1;
        } catch (IOException e) {
        	return -1;
        }
        return sec;
    }
    
    /**
     * Valida un fichero de audio.
     * @param fichero Archivo a validar
     * @return "true" si el archivo de audio es correcto y "false" si no lo es.
     */
    public boolean validar(File fichero) {
    	AudioFileFormat fileFormat;
    	try {
    		fileFormat = AudioSystem.getAudioFileFormat(fichero);
    	} catch (IOException e) {
    		return false;
    	} catch (UnsupportedAudioFileException e) {
    		return false;
    	}
        
        if (fileFormat instanceof TAudioFileFormat) {
            return true;
        } else {
            return false;
        }
    }
}