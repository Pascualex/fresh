package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Cancion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaCanciones;
import fresh.interfaz.vistas.VistaMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class ControladorCanciones {

    public ControladorCanciones(Sistema sistema, VistaCanciones vistaCanciones, VistaMenu vistaMenu) {
        cargarCanciones(sistema, vistaCanciones, vistaMenu);

        vistaCanciones.botonSubirCancion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCancion = vistaCanciones.entradaNombreCancion.getText();
                if (nombreCancion.length() == 0) return;

                if (vistaCanciones.selectorArchivo.showOpenDialog(vistaCanciones) == JFileChooser.APPROVE_OPTION) {
                    System.out.println(sistema.subirCancion(nombreCancion, vistaCanciones.selectorArchivo.getSelectedFile().getAbsolutePath()));
                    cargarCanciones(sistema, vistaCanciones, vistaMenu);
                    vistaCanciones.scrollPanel.repaint();
                }
            }
        });
    }

    private void cargarCanciones(Sistema sistema, VistaCanciones vistaCanciones, VistaMenu vistaMenu) {
        int numCanciones = sistema.getUsuarioActual().getCanciones().size();
        vistaCanciones.scrollPanel.setPreferredSize(new Dimension(0, 15+numCanciones*100));
        vistaCanciones.scrollPanel.removeAll();
        vistaCanciones.scrollFrame.revalidate();
        vistaCanciones.scrollFrame.repaint();

        int i = 0;
        for (Cancion c : sistema.getUsuarioActual().getCanciones()) {
            JLabel textoDuracion;
            textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
            textoDuracion.setBounds(115, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaCanciones.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion;
            textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(225, 15+100*i, 575, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoEstadoCancion;
            textoEstadoCancion = new JLabel(c.getAutor().getNombre());
            textoEstadoCancion.setBounds(225, 15+100*i+35, 575, 40);
            textoEstadoCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoEstadoCancion.setForeground(Estilo.colorTexto);
            textoEstadoCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaCanciones.scrollPanel.add(textoEstadoCancion);

            if (c.getBloqueado()) {
                i++;
                continue;
            }

            JCustomButton botonReproducir;
            botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(25, 15+100*i, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonReproducir.setForeground(Estilo.colorTexto);
            botonReproducir.setBackground(new Color(240, 240, 100));
            botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonReproducir.setCornerRadius(80);
            botonReproducir.setHeight(5);       
            botonReproducir.setShadowSize(5);
            botonReproducir.setShadowOpacity(0.4f);            
            vistaCanciones.scrollPanel.add(botonReproducir);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vistaMenu.botonReproducir.setVisible(false);
                    vistaMenu.botonParar.setVisible(true);
                    sistema.reproducir(c);
                }
            });

            i++;
        }
    }
}