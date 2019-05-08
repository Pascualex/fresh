package fresh.interfaz.vistas;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class VistaAnadirALista extends JPanel {
    private static final long serialVersionUID = 0;
    
    public JLabel textoEligePlaylist;
    public JCustomButton botonCancelar;

    public JCustomPanel separador;

    public JCustomScrollPane scrollFrame;
    public JPanel scrollPanel;

    public VistaAnadirALista(String tipoElemento) {
        setBounds(0, 0, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);
        
        textoEligePlaylist = new JLabel("Selecciona la playlist a la que añadir " + tipoElemento);
        textoEligePlaylist.setBounds(35, 25, 600, 40);
        textoEligePlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoEligePlaylist.setForeground(Estilo.colorTexto);
        textoEligePlaylist.setHorizontalAlignment(JLabel.LEFT);
        add(textoEligePlaylist);

        botonCancelar = new JCustomButton("Cancelar");
        botonCancelar.setBounds(708, 0, 160, 60);
        botonCancelar.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonCancelar.setForeground(Estilo.colorTexto);
        botonCancelar.setBackground(new Color(245, 100, 100));
        botonCancelar.setPressedBackgound(new Color(220, 50, 50).brighter());
        botonCancelar.setCornerRadius(25);
        botonCancelar.setHeight(5);       
        botonCancelar.setShadowSize(5);
        botonCancelar.setShadowOpacity(0.4f);
        add(botonCancelar);
        
        separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);

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