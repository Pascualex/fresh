package fresh.interfaz;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;

public class InterfazMenu extends JFrame {
    private static final int anchura = 1200;
    private static final int altura = 800;

    private static final String fuentePredeterminada = "helvetica";

    private static final Color colorPrimario = new Color(210, 220, 220);
    private static final Color colorSecundario = new Color(20, 160, 200);
    private static final Color colorTexto = new Color(40, 40, 40);
    private static final Color colorBotonMinimizar = new Color(220, 220, 220);
    private static final Color colorBotonCerrar = new Color(220, 10, 10);

    private JPanel panelInicial;
    private JCustomButton botonMinimizarAplicacion;
    private JCustomButton botonCerrarAplicacion;

    private JPanel panelInicio;
    private JLabel textoNombreAplicacion;
    private JCustomTextField entradaBusqueda;
    private JCustomButton botonReproducir;
    private JCustomButton botonAnterior;
    private JCustomButton botonSiguiente;
    private JCustomButton botonInicio;
    private JCustomButton botonPlaylist;
    private JCustomButton botonAutores;
    private JCustomButton botonMisCanciones;
    private JCustomButton botonConfiguracion;
    private JCustomButton botonCerrarSesion;

    public static void main(String[] args) {
        InterfazMenu interfaz = new InterfazMenu();
    }

    public InterfazMenu() {
        super("Swing");

        // Configura la ventana principal
        setSize(anchura, altura);
        setBackground(new Color(0, 0, 0));
        setResizable(false);
        setLayout(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea y configura el panel de inicio
        panelInicial = new JPanel();
        panelInicial.setBounds(0, 0, anchura, altura);
        panelInicial.setBackground(colorSecundario);
        panelInicial.setLayout(null);
        panelInicial.setVisible(false);

        botonMinimizarAplicacion = new JCustomButton("");
        botonMinimizarAplicacion.setBounds(anchura - 125, 10, 50, 55);
        botonMinimizarAplicacion.setBackground(colorBotonMinimizar);
        botonMinimizarAplicacion.setPressedBackgound(colorBotonMinimizar.brighter());
        botonMinimizarAplicacion.setCornerRadius(70);
        botonMinimizarAplicacion.setHeight(5);
        botonMinimizarAplicacion.setShadowSize(5);
        botonMinimizarAplicacion.setShadowOpacity(0.4f);
        panelInicial.add(botonMinimizarAplicacion);

        botonCerrarAplicacion = new JCustomButton("");
        botonCerrarAplicacion.setBounds(anchura - 65, 10, 50, 55);
        botonCerrarAplicacion.setBackground(colorBotonCerrar);
        botonCerrarAplicacion.setPressedBackgound(colorBotonCerrar.brighter());
        botonCerrarAplicacion.setCornerRadius(70);
        botonCerrarAplicacion.setHeight(5);
        botonCerrarAplicacion.setShadowSize(5);
        botonCerrarAplicacion.setShadowOpacity(0.4f);
        panelInicial.add(botonCerrarAplicacion);

        // Crea y configura el panel de inicio de sesión
        panelInicio = new JPanel();
        panelInicio.setBounds(0, 0, anchura, altura);
        panelInicio.setOpaque(false);
        panelInicio.setLayout(null);
        panelInicio.setVisible(false);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(-80, -30, anchura - 900, 100);
        textoNombreAplicacion.setFont(new Font(fuentePredeterminada, Font.BOLD, 25));
        textoNombreAplicacion.setForeground(colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);
        panelInicio.add(textoNombreAplicacion);

        entradaBusqueda = new JCustomTextField("🔍  Busca canciones...", 10);
        entradaBusqueda.setBounds(280, 10, anchura - 760, 80);
        entradaBusqueda.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaBusqueda.setForeground(colorTexto);
        entradaBusqueda.setBackground(colorPrimario);
        entradaBusqueda.setMarginSize(25);
        entradaBusqueda.setCornerRadius(40);
        entradaBusqueda.setShadowSize(5);
        entradaBusqueda.setShadowOpacity(0.2f);
        //Hago que el texto se borre cuando se hace click sobre el texto
        entradaBusqueda.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                entradaBusqueda.setText("");
            }
            public void focusLost(FocusEvent e) {
                entradaBusqueda.setText("🔍  Busca canciones...");
            }      
        });
        panelInicio.add(entradaBusqueda);

        botonReproducir = new JCustomButton("▶");
        botonReproducir.setBounds(anchura/2, 700, 100, 80);
        botonReproducir.setFont(new Font(fuentePredeterminada, Font.BOLD, 35));
        botonReproducir.setForeground(colorTexto);
        botonReproducir.setBackground(new Color(240, 240, 100));
        botonReproducir.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonReproducir.setCornerRadius(80);
        botonReproducir.setHeight(5);       
        botonReproducir.setShadowSize(5);
        botonReproducir.setShadowOpacity(0.4f);
        panelInicio.add(botonReproducir);

        botonAnterior = new JCustomButton("⏪");
        botonAnterior.setBounds(anchura/2-100, 700, 100, 80);
        botonAnterior.setFont(new Font(fuentePredeterminada, Font.BOLD, 35));
        botonAnterior.setForeground(colorTexto);
        botonAnterior.setBackground(new Color(240, 240, 100));
        botonAnterior.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonAnterior.setCornerRadius(80);
        botonAnterior.setHeight(5);       
        botonAnterior.setShadowSize(5);
        botonAnterior.setShadowOpacity(0.4f);
        panelInicio.add(botonAnterior);

        botonSiguiente = new JCustomButton("⏩");
        botonSiguiente.setBounds(anchura/2+100, 700, 100, 80);
        botonSiguiente.setFont(new Font(fuentePredeterminada, Font.BOLD, 35));
        botonSiguiente.setForeground(colorTexto);
        botonSiguiente.setBackground(new Color(240, 240, 100));
        botonSiguiente.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonSiguiente.setCornerRadius(80);
        botonSiguiente.setHeight(5);       
        botonSiguiente.setShadowSize(5);
        botonSiguiente.setShadowOpacity(0.4f); 
        panelInicio.add(botonSiguiente);

        botonInicio = new JCustomButton("🔥  Inicio");
        botonInicio.setBounds(20, 100, 200, 80);
        botonInicio.setFont(new Font(fuentePredeterminada, Font.BOLD, 20));
        botonInicio.setForeground(colorTexto);
        botonInicio.setBackground(new Color(240, 240, 100));
        botonInicio.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonInicio.setCornerRadius(40);
        botonInicio.setHeight(5);       
        botonInicio.setShadowSize(5);
        botonInicio.setShadowOpacity(0.4f);
        panelInicio.add(botonInicio);

        botonPlaylist = new JCustomButton("❤  Playlists");
        botonPlaylist.setBounds(20, 175, 200, 80);
        botonPlaylist.setFont(new Font(fuentePredeterminada, Font.BOLD, 20));
        botonPlaylist.setForeground(colorTexto);
        botonPlaylist.setBackground(new Color(240, 240, 100));
        botonPlaylist.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonPlaylist.setCornerRadius(40);
        botonPlaylist.setHeight(5);       
        botonPlaylist.setShadowSize(5);
        botonPlaylist.setShadowOpacity(0.4f);
        panelInicio.add(botonPlaylist);

        botonAutores = new JCustomButton("Autores seguidos");
        botonAutores.setBounds(20, 250, 200, 80);
        botonAutores.setFont(new Font(fuentePredeterminada, Font.BOLD, 20));
        botonAutores.setForeground(colorTexto);
        botonAutores.setBackground(new Color(240, 240, 100));
        botonAutores.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonAutores.setCornerRadius(40);
        botonAutores.setHeight(5);       
        botonAutores.setShadowSize(5);
        botonAutores.setShadowOpacity(0.4f);
        panelInicio.add(botonAutores);

        botonMisCanciones = new JCustomButton("Tus canciones");
        botonMisCanciones.setBounds(20, 325, 200, 80);
        botonMisCanciones.setFont(new Font(fuentePredeterminada, Font.BOLD, 20));
        botonMisCanciones.setForeground(colorTexto);
        botonMisCanciones.setBackground(new Color(240, 240, 100));
        botonMisCanciones.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonMisCanciones.setCornerRadius(40);
        botonMisCanciones.setHeight(5);       
        botonMisCanciones.setShadowSize(5);
        botonMisCanciones.setShadowOpacity(0.4f);
        panelInicio.add(botonMisCanciones);

        botonConfiguracion = new JCustomButton("Configuración");
        botonConfiguracion.setBounds(20, 400, 200, 80);
        botonConfiguracion.setFont(new Font(fuentePredeterminada, Font.BOLD, 20));
        botonConfiguracion.setForeground(colorTexto);
        botonConfiguracion.setBackground(new Color(240, 240, 100));
        botonConfiguracion.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonConfiguracion.setCornerRadius(40);
        botonConfiguracion.setHeight(5);       
        botonConfiguracion.setShadowSize(5);
        botonConfiguracion.setShadowOpacity(0.4f);
        panelInicio.add(botonConfiguracion);

        botonCerrarSesion = new JCustomButton("Cerrar sesión");
        botonCerrarSesion.setBounds(20, 700, 200, 80);
        botonCerrarSesion.setFont(new Font(fuentePredeterminada, Font.BOLD, 20));
        botonCerrarSesion.setForeground(colorTexto);
        botonCerrarSesion.setBackground(new Color(245, 100, 100));
        botonCerrarSesion.setPressedBackgound(new Color(220, 50, 50).brighter());
        botonCerrarSesion.setCornerRadius(40);
        botonCerrarSesion.setHeight(5);       
        botonCerrarSesion.setShadowSize(5);
        botonCerrarSesion.setShadowOpacity(0.4f);
        panelInicio.add(botonCerrarSesion);

        panelInicial.add(panelInicio);

        // Añade a la ventana principal todos los paneles 
        add(panelInicial);
        
        // Revela la ventana de la aplicación 
        panelInicio.setVisible(true);
        panelInicial.setVisible(true);
        setVisible(true);
    }
}