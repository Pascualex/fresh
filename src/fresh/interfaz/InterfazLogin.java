package fresh.interfaz;

import javax.swing.*;

import java.awt.*;

public class InterfazLogin extends JFrame {
    public static final int anchura = 1200;
    public static final int altura = 800;

    public static final String fuentePredeterminada = "helvetica";

    public static final Color colorPrimario = new Color(210, 220, 220);
    public static final Color colorSecundario = new Color(20, 160, 200);
    public static final Color colorTexto = new Color(40, 40, 40);
    public static final Color colorPlaceholder = new Color(120, 120, 120);
    public static final Color colorBotonMinimizar = new Color(220, 220, 220);
    public static final Color colorBotonCerrar = new Color(220, 10, 10);

    public JPanel panelInicial;
    public JCustomButton botonMinimizarAplicacion;
    public JCustomButton botonCerrarAplicacion;
   
    public JPanel panelInicioSesion;
    public JLabel textoNombreAplicacion;
    public JCustomTextField entradaNombreInicio;
    public JCustomPasswordField entradaContrasenaInicio;
    public JCustomButton botonIniciarSesion;
    public JCustomButton botonRegistrarseInicio;

    public JPanel panelMensaje;
    public JCustomButton botonOK;
    public JLabel textoIntroduceUsuario;
    public JLabel textoIntroduceContrasena;
    public JLabel textoContrasenaIncorrecta;

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
        entradaContrasenaInicio.setPlaceholder("Contraseña");
        entradaContrasenaInicio.setBounds(380, 430, anchura-760, 80);
        entradaContrasenaInicio.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaContrasenaInicio.setForeground(colorTexto);
        entradaContrasenaInicio.setPlaceholderColor(colorPlaceholder);
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

        //Configuro el panel de los mensajes
        panelMensaje = new JPanel();
        panelMensaje.setBounds(200, 200, anchura-400, altura-300);
        panelMensaje.setBackground(colorPrimario);
        panelMensaje.setLayout(null);
        panelMensaje.setVisible(false);

        botonOK = new JCustomButton("OK");
        botonOK.setBounds(300, 300, 200, 80);
        botonOK.setFont(new Font(fuentePredeterminada, Font.BOLD, 25));
        botonOK.setForeground(colorTexto);
        botonOK.setBackground(new Color(240, 240, 100));
        botonOK.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonOK.setCornerRadius(70);
        botonOK.setHeight(5);       
        botonOK.setShadowSize(5);
        botonOK.setShadowOpacity(0.4f);
        panelMensaje.add(botonOK);

        textoIntroduceUsuario = new JLabel("Introduce tu nombre de usuario");
        textoIntroduceUsuario.setBounds(150, 150, 500, 100);
        textoIntroduceUsuario.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceUsuario.setForeground(colorTexto);
        textoIntroduceUsuario.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceUsuario.setVisible(false);
        panelMensaje.add(textoIntroduceUsuario);

        textoIntroduceContrasena = new JLabel("Introduce tu contraseña");
        textoIntroduceContrasena.setBounds(150, 150, 500, 100);
        textoIntroduceContrasena.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceContrasena.setForeground(colorTexto);
        textoIntroduceContrasena.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceContrasena.setVisible(false);
        panelMensaje.add(textoIntroduceContrasena);

        textoContrasenaIncorrecta = new JLabel("Contraseña incorrecta");
        textoContrasenaIncorrecta.setBounds(150, 150, 500, 100);
        textoContrasenaIncorrecta.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoContrasenaIncorrecta.setForeground(colorTexto);
        textoContrasenaIncorrecta.setHorizontalAlignment(JLabel.CENTER);
        textoContrasenaIncorrecta.setVisible(false);
        panelMensaje.add(textoContrasenaIncorrecta);

        panelInicial.add(panelInicioSesion);
        panelInicial.add(panelMensaje);
        // Añade a la ventana principal todos los paneles
        add(panelInicial);

        // Revela la ventana de la aplicación
        panelInicioSesion.setVisible(true);
        panelInicial.setVisible(true);
        setVisible(true);
    }
}