package fresh.interfaz;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class InterfazRegistrarse extends JFrame{
	
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
    
    public JPanel panelRegistrarse;
    public JLabel textoRegistrarse;
    public JCustomTextField entradaNombreInicio;
    public JCustomTextField entradaNombreAutor;
    public JCustomTextField entradaFechaNacimientoDia;
    public JCustomTextField entradaFechaNacimientoMes;
    public JCustomTextField entradaFechaNacimientoAno;
    public JCustomPasswordField entradaContrasenaInicio;
    public JCustomButton botonRegistrarseInicio;
    
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
    public JLabel textoError;
    
    public InterfazRegistrarse() {
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
        panelRegistrarse = new JPanel();
        panelRegistrarse.setBounds(0, 0, anchura, altura);
        panelRegistrarse.setOpaque(false);
        panelRegistrarse.setLayout(null);
        panelRegistrarse.setVisible(false);
        
        textoRegistrarse = new JLabel("Introduce tus datos");
        textoRegistrarse.setBounds(390, 80, anchura-780, 100);
        textoRegistrarse.setFont(new Font(fuentePredeterminada, Font.BOLD, 37));
        textoRegistrarse.setForeground(colorTexto);
        textoRegistrarse.setHorizontalAlignment(JLabel.CENTER);
        panelRegistrarse.add(textoRegistrarse);
    	
        entradaNombreInicio = new JCustomTextField("", 10);
        entradaNombreInicio.setPlaceholder("Usuario");
        entradaNombreInicio.setBounds(380, 200, anchura-760, 80);
        entradaNombreInicio.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaNombreInicio.setForeground(colorTexto);
        entradaNombreInicio.setPlaceholderColor(colorPlaceholder);
        entradaNombreInicio.setBackground(colorPrimario);
        entradaNombreInicio.setMarginSize(25);
        entradaNombreInicio.setCornerRadius(70);
        entradaNombreInicio.setShadowSize(5);
        entradaNombreInicio.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaNombreInicio);
        
        entradaNombreAutor = new JCustomTextField("", 10);
        entradaNombreAutor.setPlaceholder("Autor");
        entradaNombreAutor.setBounds(380, 300, anchura-760, 80);
        entradaNombreAutor.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaNombreAutor.setForeground(colorTexto);
        entradaNombreAutor.setPlaceholderColor(colorPlaceholder);
        entradaNombreAutor.setBackground(colorPrimario);
        entradaNombreAutor.setMarginSize(25);
        entradaNombreAutor.setCornerRadius(70);
        entradaNombreAutor.setShadowSize(5);
        entradaNombreAutor.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaNombreAutor);
        
        entradaContrasenaInicio = new JCustomPasswordField(10);
        entradaContrasenaInicio.setPlaceholder("Contraseña");
        entradaContrasenaInicio.setBounds(380, 400, anchura-760, 80);
        entradaContrasenaInicio.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaContrasenaInicio.setForeground(colorTexto);
        entradaContrasenaInicio.setPlaceholderColor(colorPlaceholder);
        entradaContrasenaInicio.setBackground(colorPrimario);
        entradaContrasenaInicio.setMarginSize(25);
        entradaContrasenaInicio.setCornerRadius(70);
        entradaContrasenaInicio.setHorizontalAlignment(JLabel.LEFT);
        entradaContrasenaInicio.setShadowSize(5);
        entradaContrasenaInicio.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaContrasenaInicio);
        
        entradaFechaNacimientoDia = new JCustomTextField("", 10);
        entradaFechaNacimientoDia.setPlaceholder("Dia");
        entradaFechaNacimientoDia.setBounds(380, 500, anchura-1100, 80);
        entradaFechaNacimientoDia.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaFechaNacimientoDia.setForeground(colorTexto);
        entradaFechaNacimientoDia.setPlaceholderColor(colorPlaceholder);
        entradaFechaNacimientoDia.setBackground(colorPrimario);
        entradaFechaNacimientoDia.setMarginSize(25);
        entradaFechaNacimientoDia.setCornerRadius(70);
        entradaFechaNacimientoDia.setShadowSize(5);
        entradaFechaNacimientoDia.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaFechaNacimientoDia);
        
        entradaFechaNacimientoMes = new JCustomTextField("", 10);
        entradaFechaNacimientoMes.setPlaceholder("Mes");
        entradaFechaNacimientoMes.setBounds(500, 500, anchura-1100, 80);
        entradaFechaNacimientoMes.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaFechaNacimientoMes.setForeground(colorTexto);
        entradaFechaNacimientoMes.setPlaceholderColor(colorPlaceholder);
        entradaFechaNacimientoMes.setBackground(colorPrimario);
        entradaFechaNacimientoMes.setMarginSize(25);
        entradaFechaNacimientoMes.setCornerRadius(70);
        entradaFechaNacimientoMes.setShadowSize(5);
        entradaFechaNacimientoMes.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaFechaNacimientoMes);
        
        entradaFechaNacimientoAno= new JCustomTextField("", 10);
        entradaFechaNacimientoAno.setPlaceholder("Año");
        entradaFechaNacimientoAno.setBounds(620, 500, anchura-1000, 80);
        entradaFechaNacimientoAno.setFont(new Font(fuentePredeterminada, Font.PLAIN, 25));
        entradaFechaNacimientoAno.setForeground(colorTexto);
        entradaFechaNacimientoAno.setPlaceholderColor(colorPlaceholder);
        entradaFechaNacimientoAno.setBackground(colorPrimario);
        entradaFechaNacimientoAno.setMarginSize(25);
        entradaFechaNacimientoAno.setCornerRadius(70);
        entradaFechaNacimientoAno.setShadowSize(5);
        entradaFechaNacimientoAno.setShadowOpacity(0.2f);
        panelRegistrarse.add(entradaFechaNacimientoAno);
        
        botonRegistrarseInicio = new JCustomButton("Registrarse");
        botonRegistrarseInicio.setBounds(anchura-710, 600, 200, 80);
        botonRegistrarseInicio.setFont(new Font(fuentePredeterminada, Font.BOLD, 25));
        botonRegistrarseInicio.setForeground(colorTexto);
        botonRegistrarseInicio.setBackground(new Color(240, 240, 100));
        botonRegistrarseInicio.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonRegistrarseInicio.setCornerRadius(70);
        botonRegistrarseInicio.setHeight(5);       
        botonRegistrarseInicio.setShadowSize(5);
        botonRegistrarseInicio.setShadowOpacity(0.4f);
        panelRegistrarse.add(botonRegistrarseInicio);
        
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
        
        textoIntroduceAutor = new JLabel("Introduce tu nombre de autor");
        textoIntroduceAutor.setBounds(150, 150, 500, 100);
        textoIntroduceAutor.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceAutor.setForeground(colorTexto);
        textoIntroduceAutor.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceAutor.setVisible(false);
        panelMensaje.add(textoIntroduceAutor);
        
        textoIntroduceFecha = new JLabel("Introduce tu fecha de nacimiento");
        textoIntroduceFecha.setBounds(150, 150, 500, 100);
        textoIntroduceFecha.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoIntroduceFecha.setForeground(colorTexto);
        textoIntroduceFecha.setHorizontalAlignment(JLabel.CENTER);
        textoIntroduceFecha.setVisible(false);
        panelMensaje.add(textoIntroduceFecha);
        
        textoUsuarioIncorrecto = new JLabel("Nombre de usuario incorrecto");
        textoUsuarioIncorrecto.setBounds(150, 150, 500, 100);
        textoUsuarioIncorrecto.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoUsuarioIncorrecto.setForeground(colorTexto);
        textoUsuarioIncorrecto.setHorizontalAlignment(JLabel.CENTER);
        textoUsuarioIncorrecto.setVisible(false);
        panelMensaje.add(textoUsuarioIncorrecto);
        
        textoNombreUsuarioIncorrecto = new JLabel("Nombre de autor incorrecto");
        textoNombreUsuarioIncorrecto.setBounds(150, 150, 500, 100);
        textoNombreUsuarioIncorrecto.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoNombreUsuarioIncorrecto.setForeground(colorTexto);
        textoNombreUsuarioIncorrecto.setHorizontalAlignment(JLabel.CENTER);
        textoNombreUsuarioIncorrecto.setVisible(false);
        panelMensaje.add(textoNombreUsuarioIncorrecto);
        
        textoContrasenaIncorrecta = new JLabel("Contraseña incorrecta");
        textoContrasenaIncorrecta.setBounds(150, 150, 500, 100);
        textoContrasenaIncorrecta.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoContrasenaIncorrecta.setForeground(colorTexto);
        textoContrasenaIncorrecta.setHorizontalAlignment(JLabel.CENTER);
        textoContrasenaIncorrecta.setVisible(false);
        panelMensaje.add(textoContrasenaIncorrecta);
        
        textoFechaIncorrecta = new JLabel("Fecha incorrecta");
        textoFechaIncorrecta.setBounds(150, 150, 500, 100);
        textoFechaIncorrecta.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoFechaIncorrecta.setForeground(colorTexto);
        textoFechaIncorrecta.setHorizontalAlignment(JLabel.CENTER);
        textoFechaIncorrecta.setVisible(false);
        panelMensaje.add(textoFechaIncorrecta); 
        
        textoUsuarioExistente = new JLabel("Nombre de usuario ya existente");
        textoUsuarioExistente.setBounds(150, 150, 500, 100);
        textoUsuarioExistente.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoUsuarioExistente.setForeground(colorTexto);
        textoUsuarioExistente.setHorizontalAlignment(JLabel.CENTER);
        textoUsuarioExistente.setVisible(false);
        panelMensaje.add(textoUsuarioExistente);
        
        textoError = new JLabel("Error interno en la aplicacón");
        textoError.setBounds(150, 150, 500, 100);
        textoError.setFont(new Font(fuentePredeterminada, Font.BOLD, 30));
        textoError.setForeground(colorTexto);
        textoError.setHorizontalAlignment(JLabel.CENTER);
        textoError.setVisible(false);
        panelMensaje.add(textoError);
        
        panelInicial.add(panelRegistrarse);
        panelInicial.add(panelMensaje);
        // Añade a la ventana principal todos los paneles
        add(panelInicial);

        // Revela la ventana de la aplicación
        panelRegistrarse.setVisible(true);
        panelInicial.setVisible(true);
        setVisible(true);
        
    }
    
   /* public static void main(String[] args) {
    	InterfazRegistrarse interfaz= new InterfazRegistrarse();
    }*/
}
