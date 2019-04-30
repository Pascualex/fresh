package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

import fresh.datos.tipos.Cancion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaResultadoCanciones;

public class ControladorResultadoCanciones {

    public ControladorResultadoCanciones(Sistema sistema, VistaResultadoCanciones vistaResultadoCanciones, String entrada) {
        List<Cancion> canciones = sistema.buscarCanciones(entrada);

        vistaResultadoCanciones.scrollPanel.setPreferredSize(new Dimension(0, canciones.size()*100));

        int i = 0;
        for (Cancion c : canciones) {
            JLabel textoNombreCancion;
            textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(200, 100*i+25, 200, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.CENTER);
            vistaResultadoCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoNombreAutor;
            textoNombreAutor = new JLabel(c.getAutor().getNombre());
            textoNombreAutor.setBounds(400, 100*i+25, 200, 40);
            textoNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreAutor.setForeground(Estilo.colorTexto);
            textoNombreAutor.setHorizontalAlignment(JLabel.CENTER);
            vistaResultadoCanciones.scrollPanel.add(textoNombreAutor);

            JLabel textoDuracion;
            textoDuracion = new JLabel(String.valueOf(c.getDuracion()));
            textoDuracion.setBounds(600, 100*i+25, 100, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.CENTER);
            vistaResultadoCanciones.scrollPanel.add(textoDuracion);

            JCustomButton botonReproducir;
            botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(50, 100*i, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonReproducir.setForeground(Estilo.colorTexto);
            botonReproducir.setBackground(new Color(240, 240, 100));
            botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonReproducir.setCornerRadius(80);
            botonReproducir.setHeight(5);       
            botonReproducir.setShadowSize(5);
            botonReproducir.setShadowOpacity(0.4f);
            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.reproducir(c);
                }
            });
            vistaResultadoCanciones.scrollPanel.add(botonReproducir);
            i++;
        }
    }
}