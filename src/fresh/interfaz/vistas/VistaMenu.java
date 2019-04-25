package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;

public class VistaMenu extends JPanel {
    private static final long serialVersionUID = 0;

    public JPanel panelSuperior;
    public JLabel textoNombreAplicacion;
    public JCustomTextField entradaBusqueda;
    public JCustomComboBox seleccion;
    public JCustomButton botonBuscar;
    
    public JPanel panelLateral;
    public JCustomButton botonInicio;
    public JCustomButton botonPlaylist;
    public JCustomButton botonAutores;
    public JCustomButton botonMisCanciones;
    public JCustomButton botonConfiguracion;
    public JCustomButton botonCerrarSesion;

    public JPanel panelInferior;
    public JCustomButton botonReproducir;
    public JCustomButton botonParar;
    public JCustomButton botonAnterior;
    public JCustomButton botonSiguiente;

    public VistaMenu() {
        // Crea y configura el panel principal
        setBounds(0, 0, Estilo.anchura, Estilo.altura);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        // Configura el panel superior
        panelSuperior = new JPanel();        
        panelSuperior.setBounds(260, 0, Estilo.anchura-260, 120);        
        panelSuperior.setOpaque(false);
        panelSuperior.setLayout(null);
        add(panelSuperior);

        entradaBusqueda = new JCustomTextField("", 10);
        entradaBusqueda.setPlaceholder("Busca...");
        entradaBusqueda.setBounds(35, 30, 500, 80);
        entradaBusqueda.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaBusqueda.setForeground(Estilo.colorTexto);
        entradaBusqueda.setBackground(Estilo.colorTerciario);
        entradaBusqueda.setMarginSize(25);
        entradaBusqueda.setCornerRadius(40);
        entradaBusqueda.setShadowSize(5);
        entradaBusqueda.setShadowOpacity(0.2f);
        panelSuperior.add(entradaBusqueda);

        String[] opciones = {"Canciones", "Álbumes", "Autores"};
        seleccion = new JCustomComboBox(opciones);
        seleccion.setBounds(550, 30, 100, 80);
        panelSuperior.add(seleccion);

        botonBuscar = new JCustomButton("🔎");
        botonBuscar.setBounds(665, 30, 80, 80);
        botonBuscar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonBuscar.setForeground(Estilo.colorTexto);
        botonBuscar.setBackground(new Color(240, 240, 100));
        botonBuscar.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonBuscar.setCornerRadius(40);
        botonBuscar.setHeight(5);
        botonBuscar.setShadowSize(5);
        botonBuscar.setShadowOpacity(0.4f);
        panelSuperior.add(botonBuscar);

        // Configura el panel lateral
        panelLateral = new JPanel();        
        panelLateral.setBounds(0, 0, 260, Estilo.altura);        
        panelLateral.setBackground(Estilo.colorSecundario);
        panelLateral.setLayout(null);
        add(panelLateral);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(0, 40, 260, 40);
        textoNombreAplicacion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 50));
        textoNombreAplicacion.setForeground(Estilo.colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);
        panelLateral.add(textoNombreAplicacion);

        botonInicio = new JCustomButton("🔥  Inicio");
        botonInicio.setBounds(20, 120, 220, 80);
        botonInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonInicio.setForeground(Estilo.colorTexto);
        botonInicio.setBackground(new Color(240, 240, 100));
        botonInicio.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonInicio.setCornerRadius(40);
        botonInicio.setHeight(5);       
        botonInicio.setShadowSize(5);
        botonInicio.setShadowOpacity(0.4f);
        panelLateral.add(botonInicio);

        botonPlaylist = new JCustomButton("❤  Playlists");
        botonPlaylist.setBounds(20, 205, 220, 80);
        botonPlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonPlaylist.setForeground(Estilo.colorTexto);
        botonPlaylist.setBackground(new Color(240, 240, 100));
        botonPlaylist.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonPlaylist.setCornerRadius(40);
        botonPlaylist.setHeight(5);       
        botonPlaylist.setShadowSize(5);
        botonPlaylist.setShadowOpacity(0.4f);
        panelLateral.add(botonPlaylist);

        botonAutores = new JCustomButton("Autores seguidos");
        botonAutores.setBounds(20, 290, 220, 80);
        botonAutores.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonAutores.setForeground(Estilo.colorTexto);
        botonAutores.setBackground(new Color(240, 240, 100));
        botonAutores.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonAutores.setCornerRadius(40);
        botonAutores.setHeight(5);       
        botonAutores.setShadowSize(5);
        botonAutores.setShadowOpacity(0.4f);
        panelLateral.add(botonAutores);

        botonMisCanciones = new JCustomButton("Tus canciones");
        botonMisCanciones.setBounds(20, 375, 220, 80);
        botonMisCanciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonMisCanciones.setForeground(Estilo.colorTexto);
        botonMisCanciones.setBackground(new Color(240, 240, 100));
        botonMisCanciones.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonMisCanciones.setCornerRadius(40);
        botonMisCanciones.setHeight(5);       
        botonMisCanciones.setShadowSize(5);
        botonMisCanciones.setShadowOpacity(0.4f);
        panelLateral.add(botonMisCanciones);

        botonConfiguracion = new JCustomButton("Configuración");
        botonConfiguracion.setBounds(20, 460, 220, 80);
        botonConfiguracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonConfiguracion.setForeground(Estilo.colorTexto);
        botonConfiguracion.setBackground(new Color(240, 240, 100));
        botonConfiguracion.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonConfiguracion.setCornerRadius(40);
        botonConfiguracion.setHeight(5);       
        botonConfiguracion.setShadowSize(5);
        botonConfiguracion.setShadowOpacity(0.4f);
        panelLateral.add(botonConfiguracion);

        botonCerrarSesion = new JCustomButton("Cerrar sesión");
        botonCerrarSesion.setBounds(20, 705, 220, 80);
        botonCerrarSesion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonCerrarSesion.setForeground(Estilo.colorTexto);
        botonCerrarSesion.setBackground(new Color(245, 100, 100));
        botonCerrarSesion.setPressedBackgound(new Color(220, 50, 50).brighter());
        botonCerrarSesion.setCornerRadius(40);
        botonCerrarSesion.setHeight(5);       
        botonCerrarSesion.setShadowSize(5);
        botonCerrarSesion.setShadowOpacity(0.4f);
        panelLateral.add(botonCerrarSesion);

        // Configura el panel inferior
        panelInferior = new JPanel();        
        panelInferior.setBounds(260, 680, Estilo.anchura-260, Estilo.altura-680);      
        panelInferior.setBackground(Estilo.colorPrimario.darker());
        panelInferior.setLayout(null);
        add(panelInferior);

        botonAnterior = new JCustomButton("⏪");
        botonAnterior.setBounds((Estilo.anchura-260-100)/2-100, 20, 100, 80);
        botonAnterior.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonAnterior.setForeground(Estilo.colorTexto);
        botonAnterior.setBackground(new Color(240, 240, 100));
        botonAnterior.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonAnterior.setCornerRadius(80);
        botonAnterior.setHeight(5);       
        botonAnterior.setShadowSize(5);
        botonAnterior.setShadowOpacity(0.4f);
        panelInferior.add(botonAnterior);

        botonReproducir = new JCustomButton("▶");
        botonReproducir.setBounds((Estilo.anchura-260-100)/2, 20, 100, 80);
        botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonReproducir.setForeground(Estilo.colorTexto);
        botonReproducir.setBackground(new Color(240, 240, 100));
        botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonReproducir.setCornerRadius(80);
        botonReproducir.setHeight(5);       
        botonReproducir.setShadowSize(5);
        botonReproducir.setShadowOpacity(0.4f);
        panelInferior.add(botonReproducir);

        botonParar = new JCustomButton("‖");
        botonParar.setBounds((Estilo.anchura-260-100)/2, 20, 100, 80);
        botonParar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonParar.setForeground(Estilo.colorTexto);
        botonParar.setBackground(new Color(240, 240, 100));
        botonParar.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonParar.setCornerRadius(80);
        botonParar.setHeight(5);
        botonParar.setShadowSize(5);
        botonParar.setShadowOpacity(0.4f);
        botonParar.setVisible(false);
        panelInferior.add(botonParar);

        botonSiguiente = new JCustomButton("⏩");
        botonSiguiente.setBounds((Estilo.anchura-260-100)/2+100, 20, 100, 80);
        botonSiguiente.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonSiguiente.setForeground(Estilo.colorTexto);
        botonSiguiente.setBackground(new Color(240, 240, 100));
        botonSiguiente.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonSiguiente.setCornerRadius(80);
        botonSiguiente.setHeight(5);       
        botonSiguiente.setShadowSize(5);
        botonSiguiente.setShadowOpacity(0.4f); 
        panelInferior.add(botonSiguiente);
        
        // Revela la ventana de la aplicación 
        setVisible(true);
    }
}