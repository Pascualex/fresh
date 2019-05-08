package fresh.interfaz.vistas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

public class VistaCancionesDe extends JPanel {
    private static final long serialVersionUID = 0;

    public JLabel textoFuenteCanciones;
    public JCustomButton botonVolver;
    
    public JCustomPanel separador;

    public JLabel textoSinCanciones;    
    public JPanel scrollPanel;
    public JCustomScrollPane scrollFrame;

    public VistaCancionesDe(String fuenteCanciones) {
        setBounds(0, 0, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);
        
        textoFuenteCanciones = new JLabel("Canciones " + fuenteCanciones);
        textoFuenteCanciones.setBounds(35, 25, 650, 40);
        textoFuenteCanciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoFuenteCanciones.setForeground(Estilo.colorTexto);
        textoFuenteCanciones.setHorizontalAlignment(JLabel.LEFT);
        add(textoFuenteCanciones);

        botonVolver = new JCustomButton("Volver");
        botonVolver.setBounds(708, 0, 160, 60);
        botonVolver.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonVolver.setForeground(Estilo.colorTexto);
        botonVolver.setBackground(new Color(245, 100, 100));
        botonVolver.setPressedBackgound(new Color(220, 50, 50).brighter());
        botonVolver.setCornerRadius(25);
        botonVolver.setHeight(5);       
        botonVolver.setShadowSize(5);
        botonVolver.setShadowOpacity(0.4f);
        add(botonVolver);

        separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);
        
        textoSinCanciones = new JLabel("Sin canciones");
        textoSinCanciones.setBounds(35, 90, 765, 40);
        textoSinCanciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoSinCanciones.setForeground(Estilo.colorTexto);
        textoSinCanciones.setHorizontalAlignment(JLabel.LEFT);
        textoSinCanciones.setVisible(false);
        add(textoSinCanciones);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);

        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 100, Estilo.anchura-270, 440);
        scrollFrame.setThumbColor(Estilo.colorSecundario.brighter());
        scrollFrame.setTrackColor(Estilo.colorPrimario.darker());
        scrollFrame.setBehindColor(Estilo.colorPrimario);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollFrame);
    }
}