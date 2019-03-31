package fresh.modulos;

import fresh.datos.tipos.*;
import fresh.sistema.Configuracion;
import fresh.sistema.ModoEjecucion;
import fresh.datos.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;


/**
 * <p>Esta clase permite trabajar con reproductores MP3.</p>
 */
public class ModuloMP3 implements Runnable {
    private Configuracion configuracion;
    private Usuario usuarioActual;
    private MediaPlayer mediaPlayer = null;
    private List<Cancion> colaReproduccion = new ArrayList<>();
    @SuppressWarnings("unused")
	private final JFXPanel fxPanel = new JFXPanel();
    private ModuloMP3 sig = null;
    private ModoEjecucion modoEjecucion;
    private int reproduccionesSesion;
    
    public ModuloMP3(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    /**
     * Añade una lista de canciones a la cola de reproducción.
     * @param canciones Lista de canciones a añadir a la cola de reproducción
     */
    public void anadirAColaReproduccion(List<Cancion> canciones) {
        if (sig != null) {
            sig.anadirAColaReproduccion(canciones);
        } else {
            colaReproduccion.addAll(canciones);
        }
    }

    /**
     * Función principal de ejecución del módulo. No debería ser llamada
     * directamente, sino en un hilo de ejecución propio.
     */
    public void run() {
        while (colaReproduccion.isEmpty());

        if (modoEjecucion == ModoEjecucion.REGISTRADO) {
            int reproduccionesMensuales = usuarioActual.getReproduccionesMensuales();
            if (!usuarioActual.getPremium()) {
                if (reproduccionesMensuales >= configuracion.getMaxReproduccionesRegistrado()) return;
            }
            usuarioActual.setReproduccionesMensuales(reproduccionesMensuales+1);
        } else if (modoEjecucion == ModoEjecucion.ANONIMO) {
            if (reproduccionesSesion >= configuracion.getMaxReproduccionesAnonimo()) return;
            reproduccionesSesion++;
        }

        Cancion cancion = colaReproduccion.get(0);
        colaReproduccion.remove(0);

        String nombre = cancion.getId() + ".mp3";
        Media media = new Media(new File(nombre).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        //Operaciones con la canción
        cancion.setReproduccionesMensuales(cancion.getReproduccionesMensuales()+1);
        cancion.getAutor().setReproduccionesMensuales(cancion.getAutor().getReproduccionesMensuales()+1);

        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(this);
    }

    /**
     * Pausa la reproducción de canciones.
     */
    public synchronized void pause() {
        if (sig != null) sig.pause();
        else if (mediaPlayer != null) mediaPlayer.pause();
    }

    /**
     * Continua la reproducción de canciones.
     */
    public synchronized void play() {
        if (sig != null) sig.play();
        else if (mediaPlayer != null) mediaPlayer.play();
    }

    /**
     * Pone a reproducir la siguiente canción, saltando la actual.
     */
    public synchronized void siguiente() {
        if (sig != null) {
            sig.siguiente();
        } else {
            sig = new ModuloMP3(configuracion);
            if (modoEjecucion == ModoEjecucion.REGISTRADO) {
                sig.nuevaSesionRegistrado(usuarioActual);
            } else if (modoEjecucion == ModoEjecucion.ANONIMO) {
                sig.nuevaSesionAnonimo(reproduccionesSesion);
            }

            mediaPlayer.stop();
            sig.anadirAColaReproduccion(colaReproduccion);
            sig.run();
        }
    }

    /**
     * Calcula la duración de un fichero de audio.
     * @param f Archivo de audio
     * @return Duración del fichero de audio (en segundos)
     */
    public long obtenerDuracion(File f) {
    	long sec;
    	try {
	    	AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(f);
	        
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
     * Valida un fichero de audio
     * @param f Archivo a validar
     * @return "true" si el archivo de audio es correcto, "false" si no lo es
     */
    public boolean validar(File f) {
    	AudioFileFormat fileFormat;
    	try {
    		fileFormat = AudioSystem.getAudioFileFormat(f);
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
    
    /**
     * Termina la ejecución del reproductor.
     */
    public synchronized void exit() {
        if (sig != null) sig.exit();
        Platform.exit();
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
     * usuario actual
     * @param usuarioActual Nuevo usuario actual
     */
    public void nuevaSesionRegistrado(Usuario usuarioActual) {
        modoEjecucion = ModoEjecucion.REGISTRADO;
        this.usuarioActual = usuarioActual;
    }
}