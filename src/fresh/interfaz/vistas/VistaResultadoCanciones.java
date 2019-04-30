package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Dimension;

public class VistaResultadoCanciones extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomScrollPane scrollFrame;
    public JPanel scrollPanel;

    public VistaResultadoCanciones() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 2000));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);
        scrollPanel.setAutoscrolls(true);
        
        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 0, Estilo.anchura-260, 540);
        add(scrollFrame);
    }
}