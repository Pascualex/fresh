package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class VistaPlaylists extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomScrollPane scrollFrame;
    public JPanel scrollPanel;
    public JCustomTextField entradaNombrePlaylist;
    public JCustomButton botonCrearPlaylist;

    public VistaPlaylists() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        entradaNombrePlaylist = new JCustomTextField("", 10);
        entradaNombrePlaylist.setPlaceholder("Nombre playlist...");
        entradaNombrePlaylist.setBounds(35, 0, 400, 60);
        entradaNombrePlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaNombrePlaylist.setForeground(Estilo.colorTexto);
        entradaNombrePlaylist.setBackground(Estilo.colorTerciario);
        entradaNombrePlaylist.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaNombrePlaylist.setMarginSize(25);
        entradaNombrePlaylist.setCornerRadius(40);
        entradaNombrePlaylist.setShadowSize(5);
        entradaNombrePlaylist.setShadowOpacity(0.2f);
        add(entradaNombrePlaylist);

        botonCrearPlaylist = new JCustomButton("Crear playlist");
        botonCrearPlaylist.setBounds(444, 0, 160, 60);
        botonCrearPlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonCrearPlaylist.setForeground(Estilo.colorTexto);
        botonCrearPlaylist.setBackground(new Color(10, 200, 90));
        botonCrearPlaylist.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonCrearPlaylist.setCornerRadius(35);
        botonCrearPlaylist.setHeight(5);       
        botonCrearPlaylist.setShadowSize(5);
        botonCrearPlaylist.setShadowOpacity(0.4f);
        add(botonCrearPlaylist);

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