package fresh.interfaz.vistas;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

public class VistaCanciones extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomTextField entradaNombreCancion;
    public JCustomButton botonSubirCancion;
    public JFileChooser selectorArchivo;
    
    public JCustomPanel separador;

    public JPanel scrollPanel;
    public JCustomScrollPane scrollFrame;

    public VistaCanciones() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        entradaNombreCancion = new JCustomTextField("", 10);
        entradaNombreCancion.setPlaceholder("Nombre cancion...");
        entradaNombreCancion.setBounds(35, 0, 400, 60);
        entradaNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaNombreCancion.setForeground(Estilo.colorTexto);
        entradaNombreCancion.setBackground(Estilo.colorTerciario);
        entradaNombreCancion.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaNombreCancion.setMarginSize(25);
        entradaNombreCancion.setCornerRadius(40);
        entradaNombreCancion.setShadowSize(5);
        entradaNombreCancion.setShadowOpacity(0.2f);
        add(entradaNombreCancion);

        botonSubirCancion = new JCustomButton("Subir canción");
        botonSubirCancion.setBounds(444, 0, 160, 60);
        botonSubirCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonSubirCancion.setForeground(Estilo.colorTexto);
        botonSubirCancion.setBackground(new Color(10, 200, 90));
        botonSubirCancion.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonSubirCancion.setCornerRadius(35);
        botonSubirCancion.setHeight(5);       
        botonSubirCancion.setShadowSize(5);
        botonSubirCancion.setShadowOpacity(0.4f);
        add(botonSubirCancion);

        separador = new JCustomPanel();
        separador.setBounds(20, 97, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);
        
        selectorArchivo = new JFileChooser();
        selectorArchivo.setFileFilter(new FileNameExtensionFilter("Archivo MP3", "mp3"));
        
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