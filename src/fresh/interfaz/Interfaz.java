package fresh.interfaz;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Interfaz extends JFrame {
    private static final int anchura = 1000;
    private static final int altura = 800;

    private static final Color colorPrincipal = new Color(210, 220, 220);
    private static final Color colorSecundario = new Color(25, 215, 180);
    private static final Color colorTexto = new Color(40, 40, 40);

    private JPanel panelInicio;
    private JLabel textoNombreAplicacion;
    private JLabel textoNombre;
    private JTextField entradaNombre;
    private JLabel textoContrasena;
    private JPasswordField entradaContrasena;
    private JButton botonIniciarSesion;
    private JButton botonRegistrarse;

    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
    }

    public Interfaz() {
        super("Swing");

        // Configura la ventana principal
        setSize(anchura, altura);
        setBackground(new Color(0, 0, 0));
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea y configura el panel de inicio
        panelInicio = new JPanel();
        panelInicio.setBounds(0, 0, anchura, altura);
        panelInicio.setBackground(colorPrincipal);
        panelInicio.setLayout(null);
        panelInicio.setVisible(false);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(200, 120, anchura-400, 100);
        textoNombreAplicacion.setFont(new Font("arial", Font.BOLD, 120));
        textoNombreAplicacion.setForeground(colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);

        textoNombre = new JLabel("Nombre:");
        textoNombre.setBounds(350, 280, anchura-700, 30);
        textoNombre.setFont(new Font("arial", Font.BOLD, 20));
        textoNombre.setForeground(colorTexto);
        textoNombre.setHorizontalAlignment(JLabel.LEFT);

        entradaNombre = new JTextField(10);
        entradaNombre.setBounds(350, 320, anchura-700, 35);
        entradaNombre.setFont(new Font("arial", Font.PLAIN, 20));
        entradaNombre.setForeground(colorTexto);
        entradaNombre.setBackground(colorPrincipal.darker());
        entradaNombre.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        entradaNombre.setHorizontalAlignment(JLabel.LEFT);

        textoContrasena = new JLabel("Contraseña:");
        textoContrasena.setBounds(350, 380, anchura-700, 30);
        textoContrasena.setFont(new Font("arial", Font.BOLD, 20));
        textoContrasena.setForeground(colorTexto);
        textoContrasena.setHorizontalAlignment(JLabel.LEFT);

        entradaContrasena = new JPasswordField(10);
        entradaContrasena.setBounds(350, 420, anchura-700, 35);
        entradaContrasena.setFont(new Font("arial", Font.PLAIN, 20));
        entradaContrasena.setForeground(colorTexto);
        entradaContrasena.setBackground(colorPrincipal.darker());
        entradaContrasena.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        entradaContrasena.setHorizontalAlignment(JLabel.LEFT);

        botonIniciarSesion =  new JButton("Iniciar sesión");
        botonIniciarSesion.setBounds(350, 500, 130, 50);
        botonIniciarSesion.setFont(new Font("arial", Font.BOLD, 15));
        botonIniciarSesion.setForeground(colorTexto);
        botonIniciarSesion.setBackground(colorPrincipal.darker());
        botonIniciarSesion.setHorizontalAlignment(JLabel.CENTER);

        botonRegistrarse =  new JButton("Registrarse");
        botonRegistrarse.setBounds(anchura-350-130, 500, 130, 50);
        botonRegistrarse.setFont(new Font("arial", Font.BOLD, 15));
        botonRegistrarse.setForeground(colorTexto);
        botonRegistrarse.setBackground(colorPrincipal.darker());
        botonRegistrarse.setHorizontalAlignment(JLabel.CENTER);

        panelInicio.add(textoNombreAplicacion);
        panelInicio.add(textoNombre);
        panelInicio.add(entradaNombre);
        panelInicio.add(textoContrasena);
        panelInicio.add(entradaContrasena);
        panelInicio.add(botonIniciarSesion);
        panelInicio.add(botonRegistrarse);

        // Añade a la ventana principal todos los paneles
        add(panelInicio);

        // Revela la ventana de la aplicación
        panelInicio.setVisible(true);
        setVisible(true);
    }
}