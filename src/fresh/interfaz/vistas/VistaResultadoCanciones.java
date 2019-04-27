package fresh.interfaz.vistas;

import javax.swing.JPanel;
import fresh.interfaz.*;

public class VistaResultadoCanciones extends JPanel {
    private static final long serialVersionUID = 0;

    public VistaResultadoCanciones() {
        setBounds(260, 120, Estilo.anchura-260, 680-120);
        setOpaque(false);
        setLayout(null);

        JPanel prueba = new JPanel();
        prueba.setBounds(0, 0, 30, 30);
        prueba.setBackground(Estilo.colorBotonCerrar);
        add(prueba);
    }
}