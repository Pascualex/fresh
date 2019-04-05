package fresh.interfaz;

import javax.swing.*;

import java.awt.*;

public class Interfaz extends JFrame {
    private static final int anchura = 1200;
    private static final int altura = 800;

    private static final Color colorPrimario = new Color(210, 220, 220);
    private static final Color colorSecundario = new Color(20, 160, 200);
    private static final Color colorTerciario = new Color(10, 200, 90);
    private static final Color colorTexto = new Color(40, 40, 40);

    private JPanel panelInicio;
    private JLabel textoNombreAplicacion;
    private JTextField entradaNombre;
    private JPasswordField entradaContrasena;
    private JButton botonIniciarSesion;

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
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea y configura el panel de inicio
        panelInicio = new JPanel();
        panelInicio.setBounds(0, 0, anchura, altura);
        panelInicio.setBackground(colorSecundario);
        panelInicio.setLayout(null);
        panelInicio.setVisible(false);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(390, 150, anchura-780, 100);
        textoNombreAplicacion.setFont(new Font("arial", Font.BOLD, 90));
        textoNombreAplicacion.setForeground(colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);

        entradaNombre = new JTextField("Usuario", 10);
        entradaNombre.setBounds(390, 330, anchura-780, 70);
        entradaNombre.setFont(new Font("arial", Font.PLAIN, 25));
        entradaNombre.setForeground(colorTexto);
        entradaNombre.setBackground(colorPrimario);
        entradaNombre.setHorizontalAlignment(JLabel.LEFT);
        entradaNombre.setBorder(null);

        entradaContrasena = new JPasswordField(10);
        entradaContrasena.setBounds(390, 430, anchura-780, 70);
        entradaContrasena.setFont(new Font("arial", Font.PLAIN, 20));
        entradaContrasena.setForeground(colorTexto);
        entradaContrasena.setBackground(colorPrimario);
        entradaContrasena.setHorizontalAlignment(JLabel.LEFT);
        entradaContrasena.setBorder(null);

        botonIniciarSesion = new BotonRedondeado("Iniciar sesión");
        botonIniciarSesion.setBounds(390, 560, anchura-780, 70);
        botonIniciarSesion.setFont(new Font("arial", Font.BOLD, 25));
        botonIniciarSesion.setForeground(colorTexto);
        botonIniciarSesion.setBackground(colorTerciario);
        botonIniciarSesion.setHorizontalAlignment(JLabel.CENTER);
        botonIniciarSesion.setFocusPainted(false);
        botonIniciarSesion.setBorderPainted(false);

        panelInicio.add(textoNombreAplicacion);
        panelInicio.add(entradaNombre);
        panelInicio.add(entradaContrasena);
        panelInicio.add(botonIniciarSesion);

        // Añade a la ventana principal todos los paneles
        add(panelInicio);

        // Revela la ventana de la aplicación
        panelInicio.setVisible(true);
        setVisible(true);
    }
}