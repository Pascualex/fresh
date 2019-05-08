package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;

/**
 * Esta vista define la pestaña inicial de la aplicación, donde se puede iniciar sesión
 */
public class VistaInicio extends JPanel {
    private static final long serialVersionUID = 0;
    
    public JPanel panelInicioSesion;
    public JLabel textoNombreAplicacion;
    public JCustomTextField entradaNombreInicio;
    public JCustomPasswordField entradaContrasenaInicio;
    public JCustomButton botonIniciarSesion;
    public JCustomButton botonRegistrarseInicio;
    public JCustomButton botonAnonimo;

    public JPanel panelMensaje;
    public JCustomButton botonOK;
    public JLabel textoIntroduceUsuario;
    public JLabel textoIntroduceContrasena;
    public JLabel textoUsuarioIncorrecto;
    public JLabel textoUsuarioBloqueado;
    public JLabel textoContrasenaIncorrecta;
    public JLabel textoError;

    public VistaInicio() {
        // Crea y configura el panel principal
        setBounds(0, 0, Estilo.anchura, Estilo.altura);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        // Crea y configura el panel de inicio de sesión
        panelInicioSesion = new JPanel();
        panelInicioSesion.setBounds(0, 75, Estilo.anchura, Estilo.altura-75);
        panelInicioSesion.setOpaque(false);
        panelInicioSesion.setLayout(null);
        panelInicioSesion.setVisible(false);
        add(panelInicioSesion);

        textoNombreAplicacion = new JLabel("FRESH");
        textoNombreAplicacion.setBounds(390, 40, Estilo.anchura-780, 75);
        textoNombreAplicacion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 90));
        textoNombreAplicacion.setForeground(Estilo.colorTexto);
        textoNombreAplicacion.setHorizontalAlignment(JLabel.CENTER);
        panelInicioSesion.add(textoNombreAplicacion);

        entradaNombreInicio = new JCustomTextField("", 10);
        entradaNombreInicio.setPlaceholder("Usuario");
        entradaNombreInicio.setBounds(380, 220, Estilo.anchura-760, 80);
        entradaNombreInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaNombreInicio.setForeground(Estilo.colorTexto);
        entradaNombreInicio.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaNombreInicio.setBackground(Estilo.colorTerciario);
        entradaNombreInicio.setMarginSize(25);
        entradaNombreInicio.setCornerRadius(70);
        entradaNombreInicio.setShadowSize(5);
        entradaNombreInicio.setShadowOpacity(0.2f);
        panelInicioSesion.add(entradaNombreInicio);

        entradaContrasenaInicio = new JCustomPasswordField(10);
        entradaContrasenaInicio.setPlaceholder("Contraseña");
        entradaContrasenaInicio.setBounds(380, 320, Estilo.anchura-760, 80);
        entradaContrasenaInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaContrasenaInicio.setForeground(Estilo.colorTexto);
        entradaContrasenaInicio.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaContrasenaInicio.setBackground(Estilo.colorTerciario);
        entradaContrasenaInicio.setMarginSize(25);
        entradaContrasenaInicio.setCornerRadius(70);
        entradaContrasenaInicio.setHorizontalAlignment(JLabel.LEFT);
        entradaContrasenaInicio.setShadowSize(5);
        entradaContrasenaInicio.setShadowOpacity(0.2f);
        panelInicioSesion.add(entradaContrasenaInicio);

        botonIniciarSesion = new JCustomButton("Iniciar sesión");
        botonIniciarSesion.setBounds(380, 440, 225, 80);
        botonIniciarSesion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonIniciarSesion.setForeground(Estilo.colorTexto);
        botonIniciarSesion.setBackground(new Color(10, 200, 90));
        botonIniciarSesion.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonIniciarSesion.setCornerRadius(70);
        botonIniciarSesion.setHeight(5);       
        botonIniciarSesion.setShadowSize(5);
        botonIniciarSesion.setShadowOpacity(0.4f);
        panelInicioSesion.add(botonIniciarSesion);

        botonRegistrarseInicio = new JCustomButton("Registrarse");
        botonRegistrarseInicio.setBounds(Estilo.anchura-380-200, 440, 200, 80);
        botonRegistrarseInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonRegistrarseInicio.setForeground(Estilo.colorTexto);
        botonRegistrarseInicio.setBackground(new Color(240, 240, 100));
        botonRegistrarseInicio.setPressedBackgound(new Color(240, 240, 100).brighter());
        botonRegistrarseInicio.setCornerRadius(70);
        botonRegistrarseInicio.setHeight(5);       
        botonRegistrarseInicio.setShadowSize(5);
        botonRegistrarseInicio.setShadowOpacity(0.4f);
        panelInicioSesion.add(botonRegistrarseInicio);

        botonAnonimo = new JCustomButton("Entrar como anónimo");
        botonAnonimo.setBounds(380, 530, 340, 80);
        botonAnonimo.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonAnonimo.setForeground(Estilo.colorTexto);
        botonAnonimo.setBackground(new Color(224, 62, 98));
        botonAnonimo.setPressedBackgound(new Color(224, 62, 98).brighter());
        botonAnonimo.setCornerRadius(70);
        botonAnonimo.setHeight(5);       
        botonAnonimo.setShadowSize(5);
        botonAnonimo.setShadowOpacity(0.4f);
        panelInicioSesion.add(botonAnonimo);

        //Configuro el panel de los mensajes
        panelMensaje = new JPanel();
        panelMensaje.setBounds(200, 125, Estilo.anchura-400, Estilo.altura-250);
        panelMensaje.setBackground(Estilo.colorTerciario);
        panelMensaje.setLayout(null);
        panelMensaje.setVisible(false);
        add(panelMensaje);

        botonOK = new JCustomButton("OK");
        botonOK.setBounds(300, 300, 200, 80);
        botonOK.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonOK.setForeground(Estilo.colorTexto);
        botonOK.setBackground(new Color(240, 240, 100));
        botonOK.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonOK.setCornerRadius(70);
        botonOK.setHeight(5);       
        botonOK.setShadowSize(5);
        botonOK.setShadowOpacity(0.4f);
        panelMensaje.add(botonOK);

        textoIntroduceUsuario = new JLabel("Introduce tu nombre de usuario");
        textoIntroduceUsuario.setBounds(150, 150, 500, 100);
        textoIntroduceUsuario.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceUsuario.setForeground(Estilo.colorTexto);
        textoIntroduceUsuario.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceUsuario.setVisible(false);
        panelMensaje.add(textoIntroduceUsuario);

        textoIntroduceContrasena = new JLabel("Introduce tu contraseña");
        textoIntroduceContrasena.setBounds(150, 150, 500, 100);
        textoIntroduceContrasena.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceContrasena.setForeground(Estilo.colorTexto);
        textoIntroduceContrasena.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceContrasena.setVisible(false);
        panelMensaje.add(textoIntroduceContrasena);

        textoUsuarioIncorrecto = new JLabel("Nombre de usuario incorrecto");
        textoUsuarioIncorrecto.setBounds(150, 150, 500, 100);
        textoUsuarioIncorrecto.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoUsuarioIncorrecto.setForeground(Estilo.colorTexto);
        textoUsuarioIncorrecto.setHorizontalAlignment(JLabel.CENTER);
        textoUsuarioIncorrecto.setVisible(false);
        panelMensaje.add(textoUsuarioIncorrecto);
        
        textoUsuarioBloqueado = new JLabel("Usuario bloqueado");
        textoUsuarioBloqueado.setBounds(150, 150, 500, 100);
        textoUsuarioBloqueado.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoUsuarioBloqueado.setForeground(Estilo.colorTexto);
        textoUsuarioBloqueado.setHorizontalAlignment(JLabel.CENTER);
        textoUsuarioBloqueado.setVisible(false);
        panelMensaje.add(textoUsuarioBloqueado);
        
        textoContrasenaIncorrecta = new JLabel("Contraseña incorrecta");
        textoContrasenaIncorrecta.setBounds(150, 150, 500, 100);
        textoContrasenaIncorrecta.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoContrasenaIncorrecta.setForeground(Estilo.colorTexto);
        textoContrasenaIncorrecta.setHorizontalAlignment(JLabel.CENTER);
        textoContrasenaIncorrecta.setVisible(false);
        panelMensaje.add(textoContrasenaIncorrecta); 
        
        textoError = new JLabel("Error interno en la aplicación");
        textoError.setBounds(150, 150, 500, 100);
        textoError.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoError.setForeground(Estilo.colorTexto);
        textoError.setHorizontalAlignment(JLabel.CENTER);
        textoError.setVisible(false);
        panelMensaje.add(textoError);

        // Revela la ventana de la aplicación
        panelInicioSesion.setVisible(true);
        setVisible(true);
    }
}