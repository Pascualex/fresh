package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

/**
 * Esta vista permite registrarse a los usuarios
 */
public class VistaRegistro extends JPanel {
    private static final long serialVersionUID = 0;
    
    public JPanel panelRegistrarse;
    public JLabel textoRegistrarse;
    public JCustomTextField entradaNombreInicio;
    public JCustomTextField entradaNombreAutor;
    public JCustomTextField entradaFechaNacimientoDia;
    public JCustomTextField entradaFechaNacimientoMes;
    public JCustomTextField entradaFechaNacimientoAno;
    public JCustomPasswordField entradaContrasenaInicio;
    public JCustomButton botonRegistrarseInicio;
    public JCustomButton botonVolver;
    
    public JPanel panelMensaje;
    public JCustomButton botonOK;
    public JLabel textoIntroduceUsuario;
    public JLabel textoIntroduceAutor;
    public JLabel textoIntroduceContrasena;
    public JLabel textoIntroduceFecha;
    public JLabel textoUsuarioIncorrecto;
    public JLabel textoNombreUsuarioIncorrecto;
    public JLabel textoContrasenaIncorrecta;
    public JLabel textoFechaIncorrecta;
    public JLabel textoUsuarioExistente;
    public JLabel textoRegistroCorrecto;
    public JLabel textoError;
    
    public VistaRegistro() {
    	// Crea y configura el panel principal
        setBounds(0, 0, Estilo.anchura, Estilo.altura);
        setOpaque(false);
        setLayout(null);
        setVisible(false);
        
        // Crea y configura el panel de inicio de sesión
        panelRegistrarse = new JPanel();
        panelRegistrarse.setBounds(0, 75, Estilo.anchura, Estilo.altura-75);
        panelRegistrarse.setOpaque(false);
        panelRegistrarse.setLayout(null);
        panelRegistrarse.setVisible(false);
        add(panelRegistrarse);
        
        textoRegistrarse = new JLabel("Introduce tus datos");
        textoRegistrarse.setBounds(390, 80, Estilo.anchura-780, 100);
        textoRegistrarse.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 37));
        textoRegistrarse.setForeground(Estilo.colorTexto);
        textoRegistrarse.setHorizontalAlignment(JLabel.CENTER);
        panelRegistrarse.add(textoRegistrarse);
    	
        entradaNombreInicio = new JCustomTextField("", 10);
        entradaNombreInicio.setPlaceholder("Usuario");
        entradaNombreInicio.setBounds(380, 200, Estilo.anchura-760, 80);
        entradaNombreInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaNombreInicio.setForeground(Estilo.colorTexto);
        entradaNombreInicio.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaNombreInicio.setBackground(Estilo.colorTerciario);
        entradaNombreInicio.setMarginSize(25);
        entradaNombreInicio.setCornerRadius(70);
        entradaNombreInicio.setShadowSize(5);
        entradaNombreInicio.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaNombreInicio);
        
        entradaNombreAutor = new JCustomTextField("", 10);
        entradaNombreAutor.setPlaceholder("Autor");
        entradaNombreAutor.setBounds(380, 300, Estilo.anchura-760, 80);
        entradaNombreAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaNombreAutor.setForeground(Estilo.colorTexto);
        entradaNombreAutor.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaNombreAutor.setBackground(Estilo.colorTerciario);
        entradaNombreAutor.setMarginSize(25);
        entradaNombreAutor.setCornerRadius(70);
        entradaNombreAutor.setShadowSize(5);
        entradaNombreAutor.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaNombreAutor);
        
        entradaContrasenaInicio = new JCustomPasswordField(10);
        entradaContrasenaInicio.setPlaceholder("Contraseña");
        entradaContrasenaInicio.setBounds(380, 400, Estilo.anchura-760, 80);
        entradaContrasenaInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaContrasenaInicio.setForeground(Estilo.colorTexto);
        entradaContrasenaInicio.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaContrasenaInicio.setBackground(Estilo.colorTerciario);
        entradaContrasenaInicio.setMarginSize(25);
        entradaContrasenaInicio.setCornerRadius(70);
        entradaContrasenaInicio.setHorizontalAlignment(JLabel.LEFT);
        entradaContrasenaInicio.setShadowSize(5);
        entradaContrasenaInicio.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaContrasenaInicio);
        
        entradaFechaNacimientoDia = new JCustomTextField("", 10);
        entradaFechaNacimientoDia.setPlaceholder("Dia");
        entradaFechaNacimientoDia.setBounds(380, 500, Estilo.anchura-1100, 80);
        entradaFechaNacimientoDia.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaFechaNacimientoDia.setForeground(Estilo.colorTexto);
        entradaFechaNacimientoDia.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaFechaNacimientoDia.setBackground(Estilo.colorTerciario);
        entradaFechaNacimientoDia.setMarginSize(25);
        entradaFechaNacimientoDia.setCornerRadius(70);
        entradaFechaNacimientoDia.setShadowSize(5);
        entradaFechaNacimientoDia.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaFechaNacimientoDia);
        
        entradaFechaNacimientoMes = new JCustomTextField("", 10);
        entradaFechaNacimientoMes.setPlaceholder("Mes");
        entradaFechaNacimientoMes.setBounds(500, 500, Estilo.anchura-1100, 80);
        entradaFechaNacimientoMes.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaFechaNacimientoMes.setForeground(Estilo.colorTexto);
        entradaFechaNacimientoMes.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaFechaNacimientoMes.setBackground(Estilo.colorTerciario);
        entradaFechaNacimientoMes.setMarginSize(25);
        entradaFechaNacimientoMes.setCornerRadius(70);
        entradaFechaNacimientoMes.setShadowSize(5);
        entradaFechaNacimientoMes.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaFechaNacimientoMes);
        
        entradaFechaNacimientoAno= new JCustomTextField("", 10);
        entradaFechaNacimientoAno.setPlaceholder("Año");
        entradaFechaNacimientoAno.setBounds(620, 500, Estilo.anchura-1000, 80);
        entradaFechaNacimientoAno.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaFechaNacimientoAno.setForeground(Estilo.colorTexto);
        entradaFechaNacimientoAno.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaFechaNacimientoAno.setBackground(Estilo.colorTerciario);
        entradaFechaNacimientoAno.setMarginSize(25);
        entradaFechaNacimientoAno.setCornerRadius(70);
        entradaFechaNacimientoAno.setShadowSize(5);
        entradaFechaNacimientoAno.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaFechaNacimientoAno);
        
        botonRegistrarseInicio = new JCustomButton("Registrarse");
        botonRegistrarseInicio.setBounds(Estilo.anchura-580, 600, 200, 80);
        botonRegistrarseInicio.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonRegistrarseInicio.setForeground(Estilo.colorTexto);
        botonRegistrarseInicio.setBackground(new Color(240, 240, 100));
        botonRegistrarseInicio.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonRegistrarseInicio.setCornerRadius(70);
        botonRegistrarseInicio.setHeight(5);       
        botonRegistrarseInicio.setShadowSize(5);
        botonRegistrarseInicio.setShadowOpacity(0.4f);
        panelRegistrarse.add(botonRegistrarseInicio);

        botonVolver = new JCustomButton("Volver");
        botonVolver.setBounds(Estilo.anchura-820, 600, 200, 80);
        botonVolver.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonVolver.setForeground(Estilo.colorTexto);
        botonVolver.setBackground(new Color(224, 62, 98));
        botonVolver.setPressedBackgound(new Color(224, 62, 98).brighter());
        botonVolver.setCornerRadius(70);
        botonVolver.setHeight(5);       
        botonVolver.setShadowSize(5);
        botonVolver.setShadowOpacity(0.4f);
        panelRegistrarse.add(botonVolver);
        
        // Configura el panel de los mensajes
        panelMensaje = new JPanel();
        panelMensaje.setBounds(200, 200, Estilo.anchura-400, Estilo.altura-300);
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
        
        textoIntroduceAutor = new JLabel("Introduce tu nombre de autor");
        textoIntroduceAutor.setBounds(150, 150, 500, 100);
        textoIntroduceAutor.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceAutor.setForeground(Estilo.colorTexto);
        textoIntroduceAutor.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceAutor.setVisible(false);
        panelMensaje.add(textoIntroduceAutor);
        
        textoIntroduceFecha = new JLabel("Introduce tu fecha de nacimiento");
        textoIntroduceFecha.setBounds(150, 150, 500, 100);
        textoIntroduceFecha.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceFecha.setForeground(Estilo.colorTexto);
        textoIntroduceFecha.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceFecha.setVisible(false);
        panelMensaje.add(textoIntroduceFecha);
        
        textoUsuarioIncorrecto = new JLabel("Nombre de usuario incorrecto");
        textoUsuarioIncorrecto.setBounds(150, 150, 500, 100);
        textoUsuarioIncorrecto.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoUsuarioIncorrecto.setForeground(Estilo.colorTexto);
        textoUsuarioIncorrecto.setHorizontalAlignment(JLabel.CENTER);
        textoUsuarioIncorrecto.setVisible(false);
        panelMensaje.add(textoUsuarioIncorrecto);
        
        textoNombreUsuarioIncorrecto = new JLabel("Nombre de autor incorrecto");
        textoNombreUsuarioIncorrecto.setBounds(150, 150, 500, 100);
        textoNombreUsuarioIncorrecto.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoNombreUsuarioIncorrecto.setForeground(Estilo.colorTexto);
        textoNombreUsuarioIncorrecto.setHorizontalAlignment(JLabel.CENTER);
        textoNombreUsuarioIncorrecto.setVisible(false);
        panelMensaje.add(textoNombreUsuarioIncorrecto);
        
        textoContrasenaIncorrecta = new JLabel("Contraseña incorrecta");
        textoContrasenaIncorrecta.setBounds(150, 150, 500, 100);
        textoContrasenaIncorrecta.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoContrasenaIncorrecta.setForeground(Estilo.colorTexto);
        textoContrasenaIncorrecta.setHorizontalAlignment(JLabel.CENTER);
        textoContrasenaIncorrecta.setVisible(false);
        panelMensaje.add(textoContrasenaIncorrecta);
        
        textoFechaIncorrecta = new JLabel("Fecha incorrecta");
        textoFechaIncorrecta.setBounds(150, 150, 500, 100);
        textoFechaIncorrecta.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoFechaIncorrecta.setForeground(Estilo.colorTexto);
        textoFechaIncorrecta.setHorizontalAlignment(JLabel.CENTER);
        textoFechaIncorrecta.setVisible(false);
        panelMensaje.add(textoFechaIncorrecta); 
        
        textoUsuarioExistente = new JLabel("Nombre de usuario ya existente");
        textoUsuarioExistente.setBounds(150, 150, 500, 100);
        textoUsuarioExistente.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoUsuarioExistente.setForeground(Estilo.colorTexto);
        textoUsuarioExistente.setHorizontalAlignment(JLabel.CENTER);
        textoUsuarioExistente.setVisible(false);
        panelMensaje.add(textoUsuarioExistente);
        
        textoRegistroCorrecto = new JLabel("Registro correcto");
        textoRegistroCorrecto.setBounds(150, 150, 500, 100);
        textoRegistroCorrecto.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoRegistroCorrecto.setForeground(Estilo.colorTexto);
        textoRegistroCorrecto.setHorizontalAlignment(JLabel.CENTER);
        textoRegistroCorrecto.setVisible(false);
        panelMensaje.add(textoRegistroCorrecto);
        
        textoError = new JLabel("Error interno en la aplicación");
        textoError.setBounds(150, 150, 500, 100);
        textoError.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoError.setForeground(Estilo.colorTexto);
        textoError.setHorizontalAlignment(JLabel.CENTER);
        textoError.setVisible(false);
        panelMensaje.add(textoError);

        // Revela la ventana de la aplicación
        panelRegistrarse.setVisible(true);
        setVisible(true);
    }
}
