package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Usuario;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaAutores;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAutores {

    public ControladorAutores(Sistema sistema, VistaAutores vistaAutores) {
        cargarAutores(sistema, vistaAutores);
    }

    private void cargarAutores(Sistema sistema, VistaAutores vistaAutores) {
        int numCanciones = sistema.getUsuarioActual().getAutoresSeguidos().size();
        vistaAutores.scrollPanel.setPreferredSize(new Dimension(0, 15+numCanciones*100));
        vistaAutores.scrollPanel.removeAll();
        vistaAutores.scrollFrame.revalidate();
        vistaAutores.scrollFrame.repaint();

        int i = 0;
        for (Usuario a : sistema.getUsuarioActual().getAutoresSeguidos()) {
            JLabel textoNombre = new JLabel(a.getNombreAutor());
            textoNombre.setBounds(240, 15+100*i+20, 560, 40);
            textoNombre.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombre.setForeground(Estilo.colorTexto);
            textoNombre.setHorizontalAlignment(JLabel.LEFT);
            vistaAutores.scrollPanel.add(textoNombre);

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
            vistaAutores.scrollPanel.add(botonNoSeguir);

            botonNoSeguir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.noSeguirAutor(a);
                    cargarAutores(sistema, vistaAutores);
                }
            });

            i++;
        }
    }
}