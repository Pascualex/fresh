package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

public class VistaReportar extends JPanel {
    private static final long serialVersionUID = 0;

    public JCustomTextField entradaDescripcion;
    public JCustomButton botonReportar;
    public JLabel textoReportar;


    public VistaReportar() {
        setBounds(0, 0, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        textoReportar = new JLabel("");
        textoReportar.setBounds(15, 15, 685, 40);
        textoReportar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoReportar.setForeground(Estilo.colorTexto);
        textoReportar.setHorizontalAlignment(JLabel.LEFT);
        add(textoReportar);

        entradaDescripcion = new JCustomTextField("", 10);
        entradaDescripcion.setPlaceholder("Describe el motivo del reporte...");
        entradaDescripcion.setBounds(15, 100, Estilo.anchura-300, 200);
        entradaDescripcion.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaDescripcion.setForeground(Estilo.colorTexto);
        entradaDescripcion.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaDescripcion.setBackground(Estilo.colorTerciario);
        entradaDescripcion.setMarginSize(25);
        entradaDescripcion.setCornerRadius(70);
        entradaDescripcion.setShadowSize(5);
        entradaDescripcion.setShadowOpacity(0.2f);
        add(entradaDescripcion);

        botonReportar = new JCustomButton("Reportar");
        botonReportar.setBounds(15, 325, 160, 60);
        botonReportar.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonReportar.setForeground(Estilo.colorTexto);
        botonReportar.setBackground(new Color(10, 200, 90));
        botonReportar.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonReportar.setCornerRadius(35);
        botonReportar.setHeight(5);       
        botonReportar.setShadowSize(5);
        botonReportar.setShadowOpacity(0.4f);
        add(botonReportar);        

    }    
}