package fresh.interfaz.vistas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

public class VistaCrearAlbum extends JPanel {
    private static final long serialVersionUID = 0;
    
    public JCustomPanel separador;

    public JLabel textoEligeCanciones;
    public JPanel scrollPanel;
    public JCustomScrollPane scrollFrame;
    public JCustomButton botonCrear;

    public VistaCrearAlbum(String nombreAlbum) {
        setBounds(0, 0, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        textoEligeCanciones = new JLabel("Selecciona las canciones del álbum " + nombreAlbum + ":");
        textoEligeCanciones.setBounds(20, 0, 650, 40);
        textoEligeCanciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoEligeCanciones.setForeground(Estilo.colorTexto);
        textoEligeCanciones.setHorizontalAlignment(JLabel.LEFT);
        add(textoEligeCanciones);

        botonCrear = new JCustomButton("Crear álbum");
        botonCrear.setBounds(675, -5, 125, 50);
        botonCrear.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonCrear.setForeground(Estilo.colorTexto);
        botonCrear.setBackground(new Color(10, 200, 90));
        botonCrear.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonCrear.setCornerRadius(25);
        botonCrear.setHeight(5);       
        botonCrear.setShadowSize(5);
        botonCrear.setShadowOpacity(0.4f);
        add(botonCrear);

        separador = new JCustomPanel();
        separador.setBounds(20, 40, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);

        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 43, Estilo.anchura-270, 497);
        scrollFrame.setThumbColor(Estilo.colorSecundario.brighter());
        scrollFrame.setTrackColor(Estilo.colorPrimario.darker());
        scrollFrame.setBehindColor(Estilo.colorPrimario);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollFrame);

    }
}