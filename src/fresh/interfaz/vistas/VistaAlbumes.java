package fresh.interfaz.vistas;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

public class VistaAlbumes extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomTextField entradaNombreAlbum;
    public JCustomTextField entradaAno;
    public JCustomButton botonCrearAlbum;
    
    public JCustomPanel separador;
    
    public JLabel textoSinAlbumes;
    public JPanel scrollPanel;
    public JCustomScrollPane scrollFrame;

    public VistaAlbumes() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        entradaNombreAlbum = new JCustomTextField("", 10);
        entradaNombreAlbum.setPlaceholder("Nombre álbum...");
        entradaNombreAlbum.setBounds(35, 0, 400, 60);
        entradaNombreAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaNombreAlbum.setForeground(Estilo.colorTexto);
        entradaNombreAlbum.setBackground(Estilo.colorTerciario);
        entradaNombreAlbum.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaNombreAlbum.setMarginSize(25);
        entradaNombreAlbum.setCornerRadius(40);
        entradaNombreAlbum.setShadowSize(5);
        entradaNombreAlbum.setShadowOpacity(0.2f);
        add(entradaNombreAlbum);

        entradaAno = new JCustomTextField("", 10);
        entradaAno.setPlaceholder("Año...");
        entradaAno.setBounds(445, 0, 120, 60);
        entradaAno.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaAno.setForeground(Estilo.colorTexto);
        entradaAno.setBackground(Estilo.colorTerciario);
        entradaAno.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaAno.setMarginSize(25);
        entradaAno.setCornerRadius(40);
        entradaAno.setShadowSize(5);
        entradaAno.setShadowOpacity(0.2f);
        add(entradaAno);

        botonCrearAlbum = new JCustomButton("Crear álbum");
        botonCrearAlbum.setBounds(575, 0, 160, 60);
        botonCrearAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonCrearAlbum.setForeground(Estilo.colorTexto);
        botonCrearAlbum.setBackground(new Color(10, 200, 90));
        botonCrearAlbum.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonCrearAlbum.setCornerRadius(35);
        botonCrearAlbum.setHeight(5);       
        botonCrearAlbum.setShadowSize(5);
        botonCrearAlbum.setShadowOpacity(0.4f);
        add(botonCrearAlbum);

        separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);
        
        textoSinAlbumes = new JLabel("Todavía no has creado ningún álbum.");
        textoSinAlbumes.setBounds(35, 90, 765, 40);
        textoSinAlbumes.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoSinAlbumes.setForeground(Estilo.colorTexto);
        textoSinAlbumes.setHorizontalAlignment(JLabel.LEFT);
        textoSinAlbumes.setVisible(false);
        add(textoSinAlbumes);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);
  
        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 80, Estilo.anchura-270, 460);
        scrollFrame.setThumbColor(Estilo.colorSecundario.brighter());
        scrollFrame.setTrackColor(Estilo.colorPrimario.darker());
        scrollFrame.setBehindColor(Estilo.colorPrimario);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollFrame);
    }
}