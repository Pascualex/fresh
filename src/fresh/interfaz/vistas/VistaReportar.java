package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

/**
 * Esta vista aparece cuando se reporta una canción
 */
public class VistaReportar extends JPanel {
    private static final long serialVersionUID = 0;

    public JLabel textoReportar;

    public JCustomPanel separador;

    public JCustomTextField entradaDescripcion;
    public JCustomButton botonReportar;
    public JCustomButton botonCancelar;

    public VistaReportar() {
        setBounds(0, 0, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        textoReportar = new JLabel("");
        textoReportar.setBounds(35, 25, 480, 40);
        textoReportar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoReportar.setForeground(Estilo.colorTexto);
        textoReportar.setHorizontalAlignment(JLabel.LEFT);
        add(textoReportar);

        separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);

        entradaDescripcion = new JCustomTextField("", 10);
        entradaDescripcion.setPlaceholder("Describe el motivo del reporte...");
        entradaDescripcion.setBounds(30, 100, Estilo.anchura-400, 80);
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
        botonReportar.setBounds(533, 0, 160, 60);
        botonReportar.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonReportar.setForeground(Estilo.colorTexto);
        botonReportar.setBackground(new Color(10, 200, 90));
        botonReportar.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonReportar.setCornerRadius(25);
        botonReportar.setHeight(5);       
        botonReportar.setShadowSize(5);
        botonReportar.setShadowOpacity(0.4f);
        add(botonReportar);

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
    }
}