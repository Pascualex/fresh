package fresh.modulos;

import fresh.datos.tipos.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;

public class ModuloMP3 implements Runnable {
    private MediaPlayer mediaPlayer = null;
    private List<Cancion> colaReproduccion = new ArrayList<>();
    private final JFXPanel fxPanel = new JFXPanel();
    private ModuloMP3 sig = null;

    public void anadirAColaReproduccion(List<Cancion> canciones) {
        if (sig != null) {
            sig.anadirAColaReproduccion(canciones);
        } else {
            colaReproduccion.addAll(canciones);
        }
    }

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

    public synchronized void pause() {
        if (sig != null) sig.pause();
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    public synchronized void play() {
        if (sig != null) sig.play();
        if (mediaPlayer != null) mediaPlayer.play();
    }

    public synchronized void siguente() {
        sig = new ModuloMP3();
        mediaPlayer.stop();
        sig.anadirAColaReproduccion(colaReproduccion);
        sig.run();
    }

    public synchronized void exit() {
        if (sig != null) sig.exit();
        Platform.exit();
    }
}