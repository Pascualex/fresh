package fresh.interfaz.vistas;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import fresh.interfaz.*;
import fresh.interfaz.swing.JCustomScrollPane;

public class VistaPlaylists extends JPanel {
    private static final long serialVersionUID = 0;

    public JPanel panelScroll;
    public JCustomScrollPane scroll;

    public VistaPlaylists() {
        setBounds(260, 120, Estilo.anchura-260, 680-120);
        setOpaque(false);
        setLayout(null);
        setPreferredSize(new Dimension(Estilo.anchura, Estilo.altura));

        panelScroll = new JPanel();        
        panelScroll.setBounds(0, 0, Estilo.anchura-260, 680-120);
        panelScroll.setPreferredSize(new Dimension(Estilo.anchura, Estilo.altura));    
        panelScroll.setOpaque(true);
        panelScroll.setBackground(new Color(255, 0, 0));
        panelScroll.setLayout(null);
        panelScroll.setVisible(true);
        add(panelScroll);

        scroll = new JCustomScrollPane(panelScroll);
        scroll.setVerticalScrollBarPolicy(JCustomScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVisible(true);
        add(scroll);      
        
        setVisible(true);
    }    
}