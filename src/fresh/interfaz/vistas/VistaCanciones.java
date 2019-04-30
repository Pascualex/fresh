package fresh.interfaz.vistas;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

public class VistaCanciones extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomScrollPane scrollFrame;
    public JPanel scrollPanel;
    public JFileChooser chooser;

    public JCustomButton botonSubirCancion;

    public VistaCanciones() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        botonSubirCancion = new JCustomButton("Subir canción");
        botonSubirCancion.setBounds(40, 0, 160, 60);
        botonSubirCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonSubirCancion.setForeground(Estilo.colorTexto);
        botonSubirCancion.setBackground(new Color(10, 200, 90));
        botonSubirCancion.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonSubirCancion.setCornerRadius(35);
        botonSubirCancion.setHeight(5);       
        botonSubirCancion.setShadowSize(5);
        botonSubirCancion.setShadowOpacity(0.4f);
        add(botonSubirCancion);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 2000));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);
        scrollPanel.setAutoscrolls(true);
        
        chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Archivo MP3", "mp3"));
        
        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 100, Estilo.anchura-260, 440);
        add(scrollFrame);
    }    
}