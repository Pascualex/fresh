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

/**
 * Este controlador carga los autores seguidos por el usuario, permitiéndole
 * dejar de seguirles.
 */
public class ControladorAutores {

    public ControladorAutores(Sistema sistema, VistaAutores vistaAutores) {
        cargarAutores(sistema, vistaAutores);
    }

    private void cargarAutores(Sistema sistema, VistaAutores vistaAutores) {
        int numAutores = sistema.getUsuarioActual().getAutoresSeguidos().size();

        if (numAutores == 0) {
            vistaAutores.scrollFrame.setVisible(false);
            vistaAutores.textoSinAutores.setVisible(true);
            return;
        }

        vistaAutores.textoSinAutores.setVisible(false);
        vistaAutores.scrollFrame.setVisible(true);

        vistaAutores.scrollPanel.setPreferredSize(new Dimension(0, 15+numAutores*100));
        vistaAutores.scrollPanel.removeAll();
        vistaAutores.scrollFrame.revalidate();
        vistaAutores.scrollFrame.repaint();

        int i = 0;
        for (Usuario a : sistema.getUsuarioActual().getAutoresSeguidos()) {
            JLabel textoNombre = new JLabel(a.getNombreAutor());
            textoNombre.setBounds(115, 15+100*i+20, 685, 40);
            textoNombre.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombre.setForeground(Estilo.colorTexto);
            textoNombre.setHorizontalAlignment(JLabel.LEFT);
            vistaAutores.scrollPanel.add(textoNombre);

            JCustomButton botonNoSeguir = new JCustomButton("✖");
            botonNoSeguir.setBounds(25, 15+100*i, 75, 75);
            botonNoSeguir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
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