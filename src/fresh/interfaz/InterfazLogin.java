package fresh.interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterfazLogin extends JFrame {
    private static final int anchura = 1200;
    private static final int altura = 800;

    private static final String fuentePredeterminada = "helvetica";

    private static final Color colorPrimario = new Color(210, 220, 220);
    private static final Color colorSecundario = new Color(20, 160, 200);
    private static final Color colorTexto = new Color(40, 40, 40);
    private static final Color colorPlaceholder = new Color(100, 100, 100);
    private static final Color colorBotonMinimizar = new Color(220, 220, 220);
    private static final Color colorBotonCerrar = new Color(220, 10, 10);

    private JPanel panelInicial;
    private JCustomButton botonMinimizarAplicacion;
    private JCustomButton botonCerrarAplicacion;
   
    private JPanel panelInicioSesion;
    private JLabel textoNombreAplicacion;
    private JCustomTextField entradaNombreInicio;
    private JCustomPasswordField entradaContrasenaInicio;
    private JCustomButton botonIniciarSesion;
    private JCustomButton botonRegistrarseInicio;

    public static void main(String[] args) {
        InterfazLogin InterfazLogin = new InterfazLogin();
    }

    public InterfazLogin() {
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
        botonMinimizarAplicacion.setBounds(anchura-125, 10, 50, 55);
        botonMinimizarAplicacion.setBackground(colorBotonMinimizar);
        botonMinimizarAplicacion.setPressedBackgound(colorBotonMinimizar.brighter());
        botonMinimizarAplicacion.setCornerRadius(70);
        botonMinimizarAplicacion.setHeight(5);       
        botonMinimizarAplicacion.setShadowSize(5);
        botonMinimizarAplicacion.setShadowOpacity(0.4f);
        panelInicial.add(botonMinimizarAplicacion);

        botonCerrarAplicacion = new JCustomButton("");
        botonCerrarAplicacion.setBounds(anchura-65, 10, 50, 55);
        botonCerrarAplicacion.setBackground(colorBotonCerrar);
        botonCerrarAplicacion.setPressedBackgound(colorBotonCerrar.brighter());
        botonCerrarAplicacion.setCornerRadius(70);
        botonCerrarAplicacion.setHeight(5);       
        botonCerrarAplicacion.setShadowSize(5);
        botonCerrarAplicacion.setShadowOpacity(0.4f);
        panelInicial.add(botonCerrarAplicacion);

        // Crea y configura el panel de inicio de sesión
        panelInicioSesion = new JPanel();
        panelInicioSesion.setBounds(0, 0, anchura, altura);
        panelInicioSesion.setOpaque(false);
        panelInicioSesion.setLayout(null);
        panelInicioSesion.setVisible(false);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(390, 150, anchura-780, 100);
        textoNombreAplicacion.setFont(new Font(fuentePredeterminada, Font.BOLD, 90));
        textoNombreAplicacion.setForeground(colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);
        panelInicioSesion.add(textoNombreAplicacion);

        entradaNombreInicio = new JCustomTextField("", 10);
        entradaNombreInicio.setPlaceholder("Usuario");
        entradaNombreInicio.setBounds(380, 330, anchura-760, 80);
        entradaNombreInicio.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaNombreInicio.setForeground(colorTexto);
        entradaNombreInicio.setPlaceholderColor(colorPlaceholder);
        entradaNombreInicio.setBackground(colorPrimario);
        entradaNombreInicio.setMarginSize(25);
        entradaNombreInicio.setCornerRadius(70);
        entradaNombreInicio.setShadowSize(5);
        entradaNombreInicio.setShadowOpacity(0.2f);
        panelInicioSesion.add(entradaNombreInicio);

        entradaContrasenaInicio = new JCustomPasswordField(10);
        entradaContrasenaInicio.setBounds(380, 430, anchura-760, 80);
        entradaContrasenaInicio.setFont(new Font(fuentePredeterminada, Font.PLAIN, 20));
        entradaContrasenaInicio.setForeground(colorTexto);
        entradaContrasenaInicio.setBackground(colorPrimario);
        entradaContrasenaInicio.setMarginSize(25);
        entradaContrasenaInicio.setCornerRadius(70);
        entradaContrasenaInicio.setHorizontalAlignment(JLabel.LEFT);
        entradaContrasenaInicio.setShadowSize(5);
        entradaContrasenaInicio.setShadowOpacity(0.2f);
        panelInicioSesion.add(entradaContrasenaInicio);

        botonIniciarSesion = new JCustomButton("Iniciar sesión");
        botonIniciarSesion.setBounds(380, 560, 225, 80);
        botonIniciarSesion.setFont(new Font(fuentePredeterminada, Font.BOLD, 25));
        botonIniciarSesion.setForeground(colorTexto);
        botonIniciarSesion.setBackground(new Color(10, 200, 90));
        botonIniciarSesion.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonIniciarSesion.setCornerRadius(70);
        botonIniciarSesion.setHeight(5);       
        botonIniciarSesion.setShadowSize(5);
        botonIniciarSesion.setShadowOpacity(0.4f);
        panelInicioSesion.add(botonIniciarSesion);

        botonRegistrarseInicio = new JCustomButton("Registrarse");
        botonRegistrarseInicio.setBounds(anchura-380-200, 560, 200, 80);
        botonRegistrarseInicio.setFont(new Font(fuentePredeterminada, Font.BOLD, 25));
        botonRegistrarseInicio.setForeground(colorTexto);
        botonRegistrarseInicio.setBackground(new Color(240, 240, 100));
        botonRegistrarseInicio.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonRegistrarseInicio.setCornerRadius(70);
        botonRegistrarseInicio.setHeight(5);       
        botonRegistrarseInicio.setShadowSize(5);
        botonRegistrarseInicio.setShadowOpacity(0.4f);
        panelInicioSesion.add(botonRegistrarseInicio);

        panelInicial.add(panelInicioSesion);

        // Añade a la ventana principal todos los paneles
        add(panelInicial);

        // Revela la ventana de la aplicación
        panelInicioSesion.setVisible(true);
        panelInicial.setVisible(true);
        setVisible(true);
        botonIniciarSesion.repaint();
    }
}