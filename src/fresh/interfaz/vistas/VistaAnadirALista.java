package fresh.interfaz.vistas;

import javax.swing.JPanel;

import java.awt.Dimension;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

public class VistaAnadirALista extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomScrollPane scrollFrame;
    public JPanel scrollPanel;


    public VistaAnadirALista() {
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
        scrollFrame.setBounds(0, 100, Estilo.anchura-260, 440);
        add(scrollFrame);
    }    
}