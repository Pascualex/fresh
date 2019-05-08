package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

import fresh.datos.tipos.EstadoCancion;
import fresh.datos.tipos.Cancion;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaMenuAdministrador;
import fresh.interfaz.vistas.VistaNuevasCanciones;

/**
 * Este controlador carga las nuevas canciones pendientes de validación, 
 * permitiendo al administrador validarlas o rechazarlas.
 */
public class ControladorNuevasCanciones {

    public ControladorNuevasCanciones(Sistema sistema, VistaNuevasCanciones vistaNuevasCanciones, VistaMenuAdministrador vistaMenuAdministrador) {
        List<Cancion> canciones = sistema.obtenerNuevasCanciones();
        
        if (canciones.size() == 0) {
            vistaNuevasCanciones.scrollFrame.setVisible(false);
            vistaNuevasCanciones.textoSinResultados.setVisible(true);
        }

        cargarCanciones(sistema, vistaNuevasCanciones, vistaMenuAdministrador, canciones);
    }

    private void cargarCanciones(Sistema sistema, VistaNuevasCanciones vistaNuevasCanciones, VistaMenuAdministrador vistaMenuAdministrador, List<Cancion> canciones) {
        vistaNuevasCanciones.scrollFrame.revalidate();
        vistaNuevasCanciones.scrollFrame.repaint();

        int i = 0;
        for (Cancion c : canciones) {
            JLabel textoDuracion = new JLabel(String.valueOf(c.getDuracion()/60) + ":" + String.format("%02d", c.getDuracion()%60));
            textoDuracion.setBounds(355, 15+i*100+20, 80, 40);
            textoDuracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDuracion.setForeground(Estilo.colorTexto);
            textoDuracion.setHorizontalAlignment(JLabel.RIGHT);
            vistaNuevasCanciones.scrollPanel.add(textoDuracion);

            JLabel textoNombreCancion = new JLabel(c.getNombre());
            textoNombreCancion.setBounds(465, 15+i*100, 385, 40);
            textoNombreCancion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoNombreCancion.setForeground(Estilo.colorTexto);
            textoNombreCancion.setHorizontalAlignment(JLabel.LEFT);
            vistaNuevasCanciones.scrollPanel.add(textoNombreCancion);

            JLabel textoNombreAutor = new JLabel(c.getAutor().getNombre());
            textoNombreAutor.setBounds(465, 15+i*100+35, 385, 40);
            textoNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
            textoNombreAutor.setForeground(Estilo.colorTexto);
            textoNombreAutor.setHorizontalAlignment(JLabel.LEFT);
            vistaNuevasCanciones.scrollPanel.add(textoNombreAutor);

            JCustomButton botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(25, 15+i*100, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuenteEmojis, Font.PLAIN, 25));
            botonReproducir.setForeground(Estilo.colorTexto);
            botonReproducir.setBackground(new Color(240, 240, 100));
            botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonReproducir.setCornerRadius(80);
            botonReproducir.setHeight(5);       
            botonReproducir.setShadowSize(5);
            botonReproducir.setShadowOpacity(0.4f);
            vistaNuevasCanciones.scrollPanel.add(botonReproducir);

            JCustomButton botonValidar = new JCustomButton("✅");
            botonValidar.setBounds(110, 15+i*100, 75, 75);
            botonValidar.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 25));
            botonValidar.setForeground(Estilo.colorTexto);
            botonValidar.setBackground(new Color(10, 200, 90));
            botonValidar.setPressedBackgound(new Color(10, 200, 90).brighter());
            botonValidar.setCornerRadius(80);
            botonValidar.setHeight(5);       
            botonValidar.setShadowSize(5);
            botonValidar.setShadowOpacity(0.4f);
            vistaNuevasCanciones.scrollPanel.add(botonValidar);

            JCustomButton botonValidarExplicita = new JCustomButton("+18");
            botonValidarExplicita.setBounds(195, 15+i*100, 75, 75);
            botonValidarExplicita.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonValidarExplicita.setForeground(Estilo.colorTexto);
            botonValidarExplicita.setBackground(new Color(240, 240, 100));
            botonValidarExplicita.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonValidarExplicita.setCornerRadius(80);
            botonValidarExplicita.setHeight(5);       
            botonValidarExplicita.setShadowSize(5);
            botonValidarExplicita.setShadowOpacity(0.4f);
            vistaNuevasCanciones.scrollPanel.add(botonValidarExplicita);

            JCustomButton botonRechazarCancion = new JCustomButton("✖");
            botonRechazarCancion.setBounds(280, 15+i*100, 75, 75);
            botonRechazarCancion.setFont(new Font(Estilo.fuenteEmojis, Font.BOLD, 25));
            botonRechazarCancion.setForeground(Estilo.colorTexto);
            botonRechazarCancion.setBackground(new Color(245, 100, 100));
            botonRechazarCancion.setPressedBackgound(new Color(245, 100, 100).brighter());
            botonRechazarCancion.setCornerRadius(80);
            botonRechazarCancion.setHeight(5);       
            botonRechazarCancion.setShadowSize(5);
            botonRechazarCancion.setShadowOpacity(0.4f);
            vistaNuevasCanciones.scrollPanel.add(botonRechazarCancion);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vistaMenuAdministrador.botonReproducir.setVisible(false);
                    vistaMenuAdministrador.botonParar.setVisible(true);
                    sistema.reproducir(c);
                }
            });

            botonValidar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.cambiarEstadoCancion(c, EstadoCancion.VALIDADA);

                    if (vistaMenuAdministrador.panelActual != null) {
                        vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                        vistaMenuAdministrador.panelActual = null;
                    }
                    
                    VistaNuevasCanciones vistaNuevasCanciones = new VistaNuevasCanciones();
                    vistaMenuAdministrador.panelActual = vistaNuevasCanciones;
                    vistaMenuAdministrador.add(vistaNuevasCanciones);
    
                    @SuppressWarnings("unused")
                    ControladorNuevasCanciones controladorNuevasCanciones = new ControladorNuevasCanciones(sistema, vistaNuevasCanciones, vistaMenuAdministrador);
    
                    vistaNuevasCanciones.setVisible(true);
    
                    vistaMenuAdministrador.repaint();
                }
            });

            botonValidarExplicita.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.cambiarEstadoCancion(c, EstadoCancion.VALIDADA_EXPLICITA);

                    if (vistaMenuAdministrador.panelActual != null) {
                        vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                        vistaMenuAdministrador.panelActual = null;
                    }
                    
                    VistaNuevasCanciones vistaNuevasCanciones = new VistaNuevasCanciones();
                    vistaMenuAdministrador.panelActual = vistaNuevasCanciones;
                    vistaMenuAdministrador.add(vistaNuevasCanciones);
    
                    @SuppressWarnings("unused")
                    ControladorNuevasCanciones controladorNuevasCanciones = new ControladorNuevasCanciones(sistema, vistaNuevasCanciones, vistaMenuAdministrador);
    
                    vistaNuevasCanciones.setVisible(true);
    
                    vistaMenuAdministrador.repaint();
                }
            });

            botonRechazarCancion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.cambiarEstadoCancion(c, EstadoCancion.RECHAZADA_REVISABLE);

                    if (vistaMenuAdministrador.panelActual != null) {
                        vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                        vistaMenuAdministrador.panelActual = null;
                    }
                    
                    VistaNuevasCanciones vistaNuevasCanciones = new VistaNuevasCanciones();
                    vistaMenuAdministrador.panelActual = vistaNuevasCanciones;
                    vistaMenuAdministrador.add(vistaNuevasCanciones);
    
                    @SuppressWarnings("unused")
                    ControladorNuevasCanciones ControladorNuevasCanciones = new ControladorNuevasCanciones(sistema, vistaNuevasCanciones, vistaMenuAdministrador);
    
                    vistaNuevasCanciones.setVisible(true);
    
                    vistaMenuAdministrador.repaint();
                }
            });

            i++;
        }

        vistaNuevasCanciones.scrollPanel.setPreferredSize(new Dimension(0, 15+i*100));
    }
}