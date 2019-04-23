package fresh.interfaz;

import javax.swing.*;
import java.awt.*;

public class VistaMenu extends JPanel {
    public JCustomComboBox seleccion;

    public JLabel textoNombreAplicacion;
    public JCustomTextField entradaBusqueda;
    public JCustomButton botonReproducir;
    public JCustomButton botonParar;
    public JCustomButton botonAnterior;
    public JCustomButton botonSiguiente;
    public JCustomButton botonInicio;
    public JCustomButton botonPlaylist;
    public JCustomButton botonAutores;
    public JCustomButton botonMisCanciones;
    public JCustomButton botonConfiguracion;
    public JCustomButton botonCerrarSesion;
    public JCustomButton botonBuscar;

    public VistaMenu() {
        // Crea y configura el panel principal
        setBounds(0, 0, Estilo.anchura, Estilo.altura);
        setBackground(Estilo.colorSecundario);
        setLayout(null);
        setVisible(false);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(-80, -30, Estilo.anchura - 900, 100);
        textoNombreAplicacion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoNombreAplicacion.setForeground(Estilo.colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);
        add(textoNombreAplicacion);

        entradaBusqueda = new JCustomTextField("", 10);
        entradaBusqueda.setPlaceholder("Busca...");
        entradaBusqueda.setBounds(280, 10, Estilo.anchura-800, 80);
        entradaBusqueda.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaBusqueda.setForeground(Estilo.colorTexto);
        entradaBusqueda.setBackground(Estilo.colorPrimario);
        entradaBusqueda.setMarginSize(25);
        entradaBusqueda.setCornerRadius(40);
        entradaBusqueda.setShadowSize(5);
        entradaBusqueda.setShadowOpacity(0.2f);
        add(entradaBusqueda);

        String[] opciones = {"Canciones", "Álbumes", "Autores"};
        seleccion = new JCustomComboBox(opciones);
        seleccion.setBounds(700, 25, 100, 50);
        add(seleccion);

        botonReproducir = new JCustomButton("▶");
        botonReproducir.setBounds(Estilo.anchura/2, 700, 100, 80);
        botonReproducir.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonReproducir.setForeground(Estilo.colorTexto);
        botonReproducir.setBackground(new Color(240, 240, 100));
        botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonReproducir.setCornerRadius(80);
        botonReproducir.setHeight(5);       
        botonReproducir.setShadowSize(5);
        botonReproducir.setShadowOpacity(0.4f);
        add(botonReproducir);

        botonParar = new JCustomButton("‖");
        botonParar.setBounds(Estilo.anchura/2, 700, 100, 80);
        botonParar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonParar.setForeground(Estilo.colorTexto);
        botonParar.setBackground(new Color(240, 240, 100));
        botonParar.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonParar.setCornerRadius(80);
        botonParar.setHeight(5);       
        botonParar.setShadowSize(5);
        botonParar.setShadowOpacity(0.4f);
        botonParar.setVisible(false);
        add(botonParar);

        botonAnterior = new JCustomButton("⏪");
        botonAnterior.setBounds(Estilo.anchura/2-100, 700, 100, 80);
        botonAnterior.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonAnterior.setForeground(Estilo.colorTexto);
        botonAnterior.setBackground(new Color(240, 240, 100));
        botonAnterior.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonAnterior.setCornerRadius(80);
        botonAnterior.setHeight(5);       
        botonAnterior.setShadowSize(5);
        botonAnterior.setShadowOpacity(0.4f);
        add(botonAnterior);

        botonSiguiente = new JCustomButton("⏩");
        botonSiguiente.setBounds(Estilo.anchura/2+100, 700, 100, 80);
        botonSiguiente.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 35));
        botonSiguiente.setForeground(Estilo.colorTexto);
        botonSiguiente.setBackground(new Color(240, 240, 100));
        botonSiguiente.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonSiguiente.setCornerRadius(80);
        botonSiguiente.setHeight(5);       
        botonSiguiente.setShadowSize(5);
        botonSiguiente.setShadowOpacity(0.4f); 
        add(botonSiguiente);

        botonInicio = new JCustomButton("🔥  Inicio");
        botonInicio.setBounds(20, 100, 200, 80);
        botonInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonInicio.setForeground(Estilo.colorTexto);
        botonInicio.setBackground(new Color(240, 240, 100));
        botonInicio.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonInicio.setCornerRadius(40);
        botonInicio.setHeight(5);       
        botonInicio.setShadowSize(5);
        botonInicio.setShadowOpacity(0.4f);
        add(botonInicio);

        botonPlaylist = new JCustomButton("❤  Playlists");
        botonPlaylist.setBounds(20, 175, 200, 80);
        botonPlaylist.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonPlaylist.setForeground(Estilo.colorTexto);
        botonPlaylist.setBackground(new Color(240, 240, 100));
        botonPlaylist.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonPlaylist.setCornerRadius(40);
        botonPlaylist.setHeight(5);       
        botonPlaylist.setShadowSize(5);
        botonPlaylist.setShadowOpacity(0.4f);
        add(botonPlaylist);

        botonAutores = new JCustomButton("Autores seguidos");
        botonAutores.setBounds(20, 250, 200, 80);
        botonAutores.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonAutores.setForeground(Estilo.colorTexto);
        botonAutores.setBackground(new Color(240, 240, 100));
        botonAutores.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonAutores.setCornerRadius(40);
        botonAutores.setHeight(5);       
        botonAutores.setShadowSize(5);
        botonAutores.setShadowOpacity(0.4f);
        add(botonAutores);

        botonMisCanciones = new JCustomButton("Tus canciones");
        botonMisCanciones.setBounds(20, 325, 200, 80);
        botonMisCanciones.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonMisCanciones.setForeground(Estilo.colorTexto);
        botonMisCanciones.setBackground(new Color(240, 240, 100));
        botonMisCanciones.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonMisCanciones.setCornerRadius(40);
        botonMisCanciones.setHeight(5);       
        botonMisCanciones.setShadowSize(5);
        botonMisCanciones.setShadowOpacity(0.4f);
        add(botonMisCanciones);

        botonConfiguracion = new JCustomButton("Configuración");
        botonConfiguracion.setBounds(20, 400, 200, 80);
        botonConfiguracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonConfiguracion.setForeground(Estilo.colorTexto);
        botonConfiguracion.setBackground(new Color(240, 240, 100));
        botonConfiguracion.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonConfiguracion.setCornerRadius(40);
        botonConfiguracion.setHeight(5);       
        botonConfiguracion.setShadowSize(5);
        botonConfiguracion.setShadowOpacity(0.4f);
        add(botonConfiguracion);

        botonBuscar = new JCustomButton("🔎");
        botonBuscar.setBounds(820, 10, 80, 80);
        botonBuscar.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonBuscar.setForeground(Estilo.colorTexto);
        botonBuscar.setBackground(new Color(240, 240, 100));
        botonBuscar.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonBuscar.setCornerRadius(40);
        botonBuscar.setHeight(5);
        botonBuscar.setShadowSize(5);
        botonBuscar.setShadowOpacity(0.4f);
        add(botonBuscar);

        botonCerrarSesion = new JCustomButton("Cerrar sesión");
        botonCerrarSesion.setBounds(20, 700, 200, 80);
        botonCerrarSesion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        botonCerrarSesion.setForeground(Estilo.colorTexto);
        botonCerrarSesion.setBackground(new Color(245, 100, 100));
        botonCerrarSesion.setPressedBackgound(new Color(220, 50, 50).brighter());
        botonCerrarSesion.setCornerRadius(40);
        botonCerrarSesion.setHeight(5);       
        botonCerrarSesion.setShadowSize(5);
        botonCerrarSesion.setShadowOpacity(0.4f);
        add(botonCerrarSesion);
        
        // Revela la ventana de la aplicación 
        setVisible(true);
    }
}