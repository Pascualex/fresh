package fresh.modulos;

import fresh.datos.tipos.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;

/**
 * <p>Esta clase permite trabajar con reproductores MP3.</p>
 * @author Víctor Yrazusta (victor.yrazusta@estudiante.uam.es)
 */
public class ModuloMP3 implements Runnable {
    private MediaPlayer mediaPlayer = null;
    private List<Cancion> colaReproduccion = new ArrayList<>();
    private final JFXPanel fxPanel = new JFXPanel();
    private ModuloMP3 sig = null;

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
        while(colaReproduccion.isEmpty());

        Cancion cancion = colaReproduccion.get(0);
        colaReproduccion.remove(0);

        String nombre = cancion.getId() + ".mp3";
        Media media = new Media(new File(nombre).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        //Operaciones con la canción

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
            sig = new ModuloMP3();
            mediaPlayer.stop();
            sig.anadirAColaReproduccion(colaReproduccion);
            sig.run();
        }
    }

    /**
     * Termina la ejecución del reproductor.
     */
    public synchronized void exit() {
        if (sig != null) sig.exit();
        Platform.exit();
    }
}