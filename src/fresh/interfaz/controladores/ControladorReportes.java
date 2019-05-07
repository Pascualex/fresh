package fresh.interfaz.controladores;

import fresh.sistema.Sistema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

import fresh.datos.tipos.Reporte;
import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;
import fresh.interfaz.vistas.VistaMenuAdministrador;
import fresh.interfaz.vistas.VistaReportes;

public class ControladorReportes {

    public ControladorReportes(Sistema sistema, VistaReportes vistaReportes, VistaMenuAdministrador vistaMenuAdministrador) {
        List<Reporte> reportes = sistema.obtenerReportes();
        
        if (reportes.size() == 0) {
            vistaReportes.scrollFrame.setVisible(false);
            vistaReportes.textoSinResultados.setVisible(true);
        }

        cargarReportes(sistema, vistaReportes, vistaMenuAdministrador, reportes);
    }

    private void cargarReportes(Sistema sistema, VistaReportes vistaReportes, VistaMenuAdministrador vistaMenuAdministrador, List<Reporte> reportes) {
        vistaReportes.scrollFrame.revalidate();
        vistaReportes.scrollFrame.repaint();

        int i = 0;
        for (Reporte r : reportes) {
            JLabel textoCancionReportada = new JLabel("Cancion reportada: " + r.getCancionReportada().getNombre() + " de " + r.getCancionReportada().getAutor().getNombre());
            textoCancionReportada.setBounds(250, 35+i, 500, 40);
            textoCancionReportada.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoCancionReportada.setForeground(Estilo.colorTexto);
            textoCancionReportada.setHorizontalAlignment(JLabel.RIGHT);
            vistaReportes.scrollPanel.add(textoCancionReportada);

            JLabel textoDescripcion = new JLabel("\"" + r.getDescripcion() + "\"");
            textoDescripcion.setBounds(35, 80+i, Estilo.anchura-360, 100);
            textoDescripcion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            textoDescripcion.setForeground(Estilo.colorTexto);
            textoDescripcion.setHorizontalAlignment(JLabel.LEFT);
            vistaReportes.scrollPanel.add(textoDescripcion);

            JCustomButton botonAceptarReporte = new JCustomButton("✅");
            botonAceptarReporte.setBounds(110, 15+i, 75, 75);
            botonAceptarReporte.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            botonAceptarReporte.setForeground(Estilo.colorTexto);
            botonAceptarReporte.setBackground(new Color(10, 200, 90));
            botonAceptarReporte.setPressedBackgound(new Color(10, 200, 90).brighter());
            botonAceptarReporte.setCornerRadius(80);
            botonAceptarReporte.setHeight(5);       
            botonAceptarReporte.setShadowSize(5);
            botonAceptarReporte.setShadowOpacity(0.4f);
            vistaReportes.scrollPanel.add(botonAceptarReporte);

            JCustomButton botonRechazarReporte = new JCustomButton("✖");
            botonRechazarReporte.setBounds(195, 15+i, 75, 75);
            botonRechazarReporte.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
            botonRechazarReporte.setForeground(Estilo.colorTexto);
            botonRechazarReporte.setBackground(new Color(245, 100, 100));
            botonRechazarReporte.setPressedBackgound(new Color(245, 100, 100).brighter());
            botonRechazarReporte.setCornerRadius(80);
            botonRechazarReporte.setHeight(5);       
            botonRechazarReporte.setShadowSize(5);
            botonRechazarReporte.setShadowOpacity(0.4f);
            vistaReportes.scrollPanel.add(botonRechazarReporte);

            JCustomButton botonReproducir = new JCustomButton("▶");
            botonReproducir.setBounds(25, 15+i, 75, 75);
            botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
            botonReproducir.setForeground(Estilo.colorTexto);
            botonReproducir.setBackground(new Color(240, 240, 100));
            botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
            botonReproducir.setCornerRadius(80);
            botonReproducir.setHeight(5);       
            botonReproducir.setShadowSize(5);
            botonReproducir.setShadowOpacity(0.4f);
            vistaReportes.scrollPanel.add(botonReproducir);

            botonReproducir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.reproducir(r.getCancionReportada());
                }
            });

            botonAceptarReporte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.aceptarReporte(r);

                    if (vistaMenuAdministrador.panelActual != null) {
                        vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                        vistaMenuAdministrador.panelActual = null;
                    }
                    
                    VistaReportes vistaReportes = new VistaReportes();
                    vistaMenuAdministrador.panelActual = vistaReportes;
                    vistaMenuAdministrador.add(vistaReportes);
    
                    @SuppressWarnings("unused")
                    ControladorReportes controladorReportes = new ControladorReportes(sistema, vistaReportes, vistaMenuAdministrador);
    
                    vistaReportes.setVisible(true);
    
                    vistaMenuAdministrador.repaint();
                }
            });

            botonRechazarReporte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.rechazarReporte(r);

                    if (vistaMenuAdministrador.panelActual != null) {
                        vistaMenuAdministrador.remove(vistaMenuAdministrador.panelActual);                    
                        vistaMenuAdministrador.panelActual = null;
                    }
                    
                    VistaReportes vistaReportes = new VistaReportes();
                    vistaMenuAdministrador.panelActual = vistaReportes;
                    vistaMenuAdministrador.add(vistaReportes);
    
                    @SuppressWarnings("unused")
                    ControladorReportes controladorReportes = new ControladorReportes(sistema, vistaReportes, vistaMenuAdministrador);
    
                    vistaReportes.setVisible(true);
    
                    vistaMenuAdministrador.repaint();
                }
            });

            i += 175;
        }

        vistaReportes.scrollPanel.setPreferredSize(new Dimension(0, 15+i));
    }
}