package fresh.interfaz.vistas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);
        
        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 100, Estilo.anchura-270, 440);
        scrollFrame.setThumbColor(Estilo.colorSecundario.brighter());
        scrollFrame.setTrackColor(Estilo.colorPrimario.darker());
        scrollFrame.setBehindColor(Estilo.colorPrimario);
        scrollFrame.setHeight(10); 
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollFrame);
    }    
}