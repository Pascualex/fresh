package fresh.interfaz.controladores;

import fresh.sistema.Sistema;
import fresh.datos.tipos.Album;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.JCustomButton;
import fresh.interfaz.vistas.VistaAlbumes;
import fresh.interfaz.vistas.VistaMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class ControladorAlbumes {

    public ControladorAlbumes(Sistema sistema, VistaAlbumes vistaAlbumes, VistaMenu vistaMenu) {
        cargarAlbumes(sistema, vistaAlbumes, vistaMenu);

        vistaAlbumes.botonCrearAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String nombreCancion = vistaAlbumes.entradaNombreCancion.getText();
                if (nombreCancion.length() == 0) return;

                sistema.crearAlbum(nombre, ano, canciones);*/
            }
        });
    }

    private void cargarAlbumes(Sistema sistema, VistaAlbumes vistaAlbumes, VistaMenu vistaMenu) {
        int numAlbumes = sistema.getUsuarioActual().getAlbumes().size();
        vistaAlbumes.scrollPanel.setPreferredSize(new Dimension(0, 15+numAlbumes*100));
        vistaAlbumes.scrollPanel.removeAll();
        vistaAlbumes.scrollFrame.revalidate();
        vistaAlbumes.scrollFrame.repaint();

        int i = 0;
        for (Album a : sistema.getUsuarioActual().getAlbumes()) {
            JLabel textoDuracion;
            textoDuracion = new JLabel(String.valueOf(a.getDuracion()/60) + ":" + String.format("%02d", a.getDuracion()%60));
            textoDuracion.setBounds(115, 15+100*i+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaAlbumes.scrollPanel.add(textoDuracion);

            JLabel textoNombreAlbum;
            textoNombreAlbum = new JLabel(a.getNombre());
            textoNombreAlbum.setBounds(225, 15+100*i, 575, 40);
            textoNombreAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreAlbum.setForeground(Estilo.colorTexto);
            textoNombreAlbum.setHorizontalAlignment(JLabel.LEFT);
            vistaAlbumes.scrollPanel.add(textoNombreAlbum);

            String estado;
            if (!a.getBloqueado()) estado = "Disponible";
            else estado = "Bloqueado";

            JLabel textoEstadoAlbum;
            textoEstadoAlbum = new JLabel(estado);
            textoEstadoAlbum.setBounds(225, 15+100*i+35, 575, 40);
            textoEstadoAlbum.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoEstadoAlbum.setForeground(Estilo.colorTexto);
            textoEstadoAlbum.setHorizontalAlignment(JLabel.LEFT);
            vistaAlbumes.scrollPanel.add(textoEstadoAlbum);

            if (a.getBloqueado()) {
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
            vistaAlbumes.scrollPanel.add(botonReproducir);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vistaMenu.botonReproducir.setVisible(false);
                    vistaMenu.botonParar.setVisible(true);
                    sistema.reproducir(a);
                }
            });

            i++;
        }
    }
}