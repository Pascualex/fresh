package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Usuario;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaResultadoAutores;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

public class ControladorResultadoAutores {

    public ControladorResultadoAutores(Sistema sistema, VistaResultadoAutores vistaResultadoAutores, String entrada) {
        List<Usuario> autores = sistema.buscarAutores(entrada);
        
        if (autores.size() == 0) {
            vistaResultadoAutores.scrollFrame.setVisible(false);
            vistaResultadoAutores.textoSinResultados.setVisible(true);
        }

        cargarAutores(sistema, vistaResultadoAutores, autores);
    }

    private void cargarAutores(Sistema sistema, VistaResultadoAutores vistaResultadoAutores, List<Usuario> autores) {
        vistaResultadoAutores.scrollPanel.setPreferredSize(new Dimension(0, 15+autores.size()*100));
        vistaResultadoAutores.scrollFrame.revalidate();
        vistaResultadoAutores.scrollFrame.repaint();

        int i = 0;
        for (Usuario a : autores) {
            JLabel textoNombre = new JLabel(a.getNombreAutor());
            textoNombre.setBounds(240, 15+100*i+20, 560, 40);
            textoNombre.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombre.setForeground(Estilo.colorTexto);
            textoNombre.setHorizontalAlignment(JLabel.LEFT);
            vistaResultadoAutores.scrollPanel.add(textoNombre);             

            JCustomButton botonSeguir = new JCustomButton("Seguir");
            botonSeguir.setBounds(25, 15+100*i, 200, 75);
            botonSeguir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonSeguir.setForeground(Estilo.colorTexto);
            botonSeguir.setBackground(new Color(240, 240, 100));
            botonSeguir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonSeguir.setCornerRadius(80);
            botonSeguir.setHeight(5);       
            botonSeguir.setShadowSize(5);
            botonSeguir.setShadowOpacity(0.4f);
            vistaResultadoAutores.scrollPanel.add(botonSeguir);

            JCustomButton botonNoSeguir = new JCustomButton("No seguir");
            botonNoSeguir.setBounds(25, 15+100*i, 200, 75);
            botonNoSeguir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonNoSeguir.setForeground(Estilo.colorTexto);
            botonNoSeguir.setBackground(new Color(245, 100, 100));
            botonNoSeguir.setPressedBackgound(new Color(220, 50, 50).brighter());
            botonNoSeguir.setCornerRadius(80);
            botonNoSeguir.setHeight(5);       
            botonNoSeguir.setShadowSize(5);
            botonNoSeguir.setShadowOpacity(0.4f);
            vistaResultadoAutores.scrollPanel.add(botonNoSeguir);

            if (!sistema.getUsuarioActual().getAutoresSeguidos().contains(a)) {
                botonNoSeguir.setVisible(false);
            } else {
                botonSeguir.setVisible(false);
            }

            botonSeguir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.seguirAutor(a);
                    botonSeguir.setVisible(false);
                    botonNoSeguir.setVisible(true);
                }
            });

            botonNoSeguir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.noSeguirAutor(a);
                    botonNoSeguir.setVisible(false);
                    botonSeguir.setVisible(true);
                }
            });

            i++;
        }
    }
}