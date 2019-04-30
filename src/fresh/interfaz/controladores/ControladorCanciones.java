package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import fresh.Status;
import fresh.datos.tipos.Cancion;
import fresh.datos.tipos.EstadoCancion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaCanciones;

public class ControladorCanciones {

    public ControladorCanciones(Sistema sistema, VistaCanciones vistaCanciones) {
        vistaCanciones.botonSubirCancion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = vistaCanciones.chooser.showOpenDialog(vistaCanciones);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    Status ret = sistema.subirCancion(vistaCanciones.chooser.getSelectedFile().getName(), vistaCanciones.chooser.getSelectedFile().getAbsolutePath());
                    System.out.println(ret);
                    vistaCanciones.scrollPanel.repaint();
                }
            }
        });

        int numCanciones = sistema.getUsuarioActual().getCanciones().size();
        vistaCanciones.scrollPanel.setPreferredSize(new Dimension(0, numCanciones*100));

        int i = 0;
        for (Cancion c : sistema.getUsuarioActual().getCanciones()) {
            JLabel textoNombreCancion;
            textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(200, 100*i+25, 200, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.CENTER);
            vistaCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoEstadoCancion;
            textoEstadoCancion = new JLabel(c.getEstado().toString());
            textoEstadoCancion.setBounds(400, 100*i+25, 200, 40);
            textoEstadoCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
            textoEstadoCancion.setForeground(Estilo.colorTexto);
            textoEstadoCancion.setHorizontalAlignment(JLabel.CENTER);
            vistaCanciones.scrollPanel.add(textoEstadoCancion);

            JCustomButton botonReproducir;
            botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(50, 100*i, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
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
            vistaCanciones.scrollPanel.add(botonReproducir);
            i++;
        }
    }
}