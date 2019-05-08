package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;

public class VistaNotificaciones extends JPanel {
    private static final long serialVersionUID = 0;

    public JLabel textoNotificaciones;

    public JCustomPanel separador;

    public JLabel textoSinNotificaciones;
    public JCustomScrollPane scrollFrame;
    public JPanel scrollPanel;

    public VistaNotificaciones() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);
        
        textoNotificaciones = new JLabel("Notificaciones");
        textoNotificaciones.setBounds(35, 25, 765, 40);
        textoNotificaciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoNotificaciones.setForeground(Estilo.colorTexto);
        textoNotificaciones.setHorizontalAlignment(JLabel.LEFT);
        add(textoNotificaciones);

        separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);
        
        textoSinNotificaciones = new JLabel("Sin notificaciones, nada nuevo por aquí.");
        textoSinNotificaciones.setBounds(35, 90, 765, 40);
        textoSinNotificaciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoSinNotificaciones.setForeground(Estilo.colorTexto);
        textoSinNotificaciones.setHorizontalAlignment(JLabel.LEFT);
        textoSinNotificaciones.setVisible(false);
        add(textoSinNotificaciones);

        scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.setBackground(Estilo.colorPrimario);
        scrollPanel.setLayout(null);
        
        scrollFrame = new JCustomScrollPane(scrollPanel);
        scrollFrame.setBounds(0, 80, Estilo.anchura-270, 460);
        scrollFrame.setThumbColor(Estilo.colorSecundario.brighter());
        scrollFrame.setTrackColor(Estilo.colorPrimario.darker());
        scrollFrame.setBehindColor(Estilo.colorPrimario);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollFrame);
    }
}