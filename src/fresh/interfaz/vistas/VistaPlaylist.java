package fresh.interfaz.vistas;

import javax.swing.JPanel;
import java.awt.Color;
import fresh.interfaz.*;

public class VistaPlaylist extends JPanel {
    private static final long serialVersionUID = 0;

    public VistaPlaylist() {
        // Crea y configura el panel principal
        setBounds(260, 120, Estilo.anchura-260, 680-120);
        setOpaque(false);
        setLayout(null);
        setVisible(false);
    }
    
}