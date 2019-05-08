package fresh.interfaz.vistas;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;

import fresh.interfaz.*;
import fresh.interfaz.swing.*;

/**
 * Esta vista define la pestaña de autores seguidos por el usuario
 */
public class VistaAutores extends JPanel {
    private static final long serialVersionUID = 0;
    
    public JLabel textoAutoresSeguidos;

    public JCustomPanel separador;

    public JLabel textoSinAutores;
    public JPanel scrollPanel;
    public JCustomScrollPane scrollFrame;

    public VistaAutores() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);
        
        textoAutoresSeguidos = new JLabel("Autores seguidos");
        textoAutoresSeguidos.setBounds(35, 25, 765, 40);
        textoAutoresSeguidos.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoAutoresSeguidos.setForeground(Estilo.colorTexto);
        textoAutoresSeguidos.setHorizontalAlignment(JLabel.LEFT);
        add(textoAutoresSeguidos);

        separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);
        
        textoSinAutores = new JLabel("Todavía no sigues a ningún autor");
        textoSinAutores.setBounds(35, 90, 765, 40);
        textoSinAutores.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoSinAutores.setForeground(Estilo.colorTexto);
        textoSinAutores.setHorizontalAlignment(JLabel.LEFT);
        textoSinAutores.setVisible(false);
        add(textoSinAutores);

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