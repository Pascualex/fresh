package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;

/**
 * Esta vista define la pestaña de configuración del administrador
 */
public class VistaConfiguracion extends JPanel {
    private static final long serialVersionUID = 0;

    public JLabel textoConfiguracion;
    public JCustomButton botonActualizar;

    public JCustomPanel separador;

    public JLabel textoNombreAdministrador;
    public JLabel textoContrasenaAdministrador;
    public JLabel textoEdadMinima;
    public JLabel textoMaxReproduccionesAnonimo;
    public JLabel textoMaxReproduccionesRegistrado;
    public JLabel textoCuotaPremium;
    public JLabel textoMinReproduccionesPremium;
    public JLabel textoCaracteresMinimos;

    public JCustomTextField entradaNombreAdministrador;
    public JCustomTextField entradaContrasenaAdministrador;
    public JCustomTextField entradaEdadMinima;
    public JCustomTextField entradaMaxReproduccionesAnonimo;
    public JCustomTextField entradaMaxReproduccionesRegistrado;
    public JCustomTextField entradaCuotaPremium;
    public JCustomTextField entradaMinReproduccionesPremium;
    public JCustomTextField entradaCaracteresMinimos;

    public VistaConfiguracion() {
        setBounds(260, 140, Estilo.anchura-260, 540);
        setOpaque(false);
        setLayout(null);
        setVisible(false);

        textoConfiguracion = new JLabel("Configuración");
        textoConfiguracion.setBounds(35, 15, 765, 40);
        textoConfiguracion.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        textoConfiguracion.setForeground(Estilo.colorTexto);
        textoConfiguracion.setHorizontalAlignment(JLabel.LEFT);
        add(textoConfiguracion);

        botonActualizar = new JCustomButton("Actualizar configuracion");
        botonActualizar.setBounds(618, 0, 250, 60);
        botonActualizar.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        botonActualizar.setForeground(Estilo.colorTexto);
        botonActualizar.setBackground(new Color(10, 200, 90));
        botonActualizar.setPressedBackgound(new Color(10, 200, 90).brighter());
        botonActualizar.setCornerRadius(25);
        botonActualizar.setHeight(5);       
        botonActualizar.setShadowSize(5);
        botonActualizar.setShadowOpacity(0.4f);
        add(botonActualizar);

        separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
        separador.setBackground(Estilo.colorPrimario.darker());
        separador.setCornerRadius(5);
        add(separador);

        textoNombreAdministrador = new JLabel("Nombre administrador");
        textoNombreAdministrador.setBounds(50, 100, 350, 40);
        textoNombreAdministrador.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoNombreAdministrador.setForeground(Estilo.colorTexto);
        textoNombreAdministrador.setHorizontalAlignment(JLabel.RIGHT);
        add(textoNombreAdministrador);

        textoContrasenaAdministrador = new JLabel("Contraseña administrador");
        textoContrasenaAdministrador.setBounds(50, 150, 350, 40);
        textoContrasenaAdministrador.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoContrasenaAdministrador.setForeground(Estilo.colorTexto);
        textoContrasenaAdministrador.setHorizontalAlignment(JLabel.RIGHT);
        add(textoContrasenaAdministrador);

        textoEdadMinima = new JLabel("Edad mínima");
        textoEdadMinima.setBounds(50, 200, 350, 40);
        textoEdadMinima.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoEdadMinima.setForeground(Estilo.colorTexto);
        textoEdadMinima.setHorizontalAlignment(JLabel.RIGHT);
        add(textoEdadMinima);

        textoMaxReproduccionesAnonimo = new JLabel("Reproducciones máximas anónimo");
        textoMaxReproduccionesAnonimo.setBounds(50, 250, 350, 40);
        textoMaxReproduccionesAnonimo.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoMaxReproduccionesAnonimo.setForeground(Estilo.colorTexto);
        textoMaxReproduccionesAnonimo.setHorizontalAlignment(JLabel.RIGHT);
        add(textoMaxReproduccionesAnonimo);

        textoMaxReproduccionesRegistrado = new JLabel("Reproducciones máximas registrado");
        textoMaxReproduccionesRegistrado.setBounds(50, 300, 350, 40);
        textoMaxReproduccionesRegistrado.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoMaxReproduccionesRegistrado.setForeground(Estilo.colorTexto);
        textoMaxReproduccionesRegistrado.setHorizontalAlignment(JLabel.RIGHT);
        add(textoMaxReproduccionesRegistrado);

        textoCuotaPremium = new JLabel("Coste cuota premium");
        textoCuotaPremium.setBounds(50, 350, 350, 40);
        textoCuotaPremium.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoCuotaPremium.setForeground(Estilo.colorTexto);
        textoCuotaPremium.setHorizontalAlignment(JLabel.RIGHT);
        add(textoCuotaPremium);

        textoMinReproduccionesPremium = new JLabel("Reproducciones mínimas premium");
        textoMinReproduccionesPremium.setBounds(50, 400, 350, 40);
        textoMinReproduccionesPremium.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoMinReproduccionesPremium.setForeground(Estilo.colorTexto);
        textoMinReproduccionesPremium.setHorizontalAlignment(JLabel.RIGHT);
        add(textoMinReproduccionesPremium);

        textoCaracteresMinimos = new JLabel("Caracteres mínimos campos");
        textoCaracteresMinimos.setBounds(50, 450, 350, 40);
        textoCaracteresMinimos.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoCaracteresMinimos.setForeground(Estilo.colorTexto);
        textoCaracteresMinimos.setHorizontalAlignment(JLabel.RIGHT);
        add(textoCaracteresMinimos);

        entradaNombreAdministrador = new JCustomTextField("", 10);
        entradaNombreAdministrador.setPlaceholder("Placeholder");
        entradaNombreAdministrador.setBounds(420, 95, 380, 50);
        entradaNombreAdministrador.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaNombreAdministrador.setForeground(Estilo.colorTexto);
        entradaNombreAdministrador.setBackground(Estilo.colorTerciario);
        entradaNombreAdministrador.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaNombreAdministrador.setMarginSize(25);
        entradaNombreAdministrador.setCornerRadius(40);
        entradaNombreAdministrador.setShadowSize(5);
        entradaNombreAdministrador.setShadowOpacity(0.2f);
        add(entradaNombreAdministrador);

        entradaContrasenaAdministrador = new JCustomTextField("", 10);
        entradaContrasenaAdministrador.setPlaceholder("Placeholder");
        entradaContrasenaAdministrador.setBounds(420, 145, 380, 50);
        entradaContrasenaAdministrador.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaContrasenaAdministrador.setForeground(Estilo.colorTexto);
        entradaContrasenaAdministrador.setBackground(Estilo.colorTerciario);
        entradaContrasenaAdministrador.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaContrasenaAdministrador.setMarginSize(25);
        entradaContrasenaAdministrador.setCornerRadius(40);
        entradaContrasenaAdministrador.setShadowSize(5);
        entradaContrasenaAdministrador.setShadowOpacity(0.2f);
        add(entradaContrasenaAdministrador);

        entradaEdadMinima = new JCustomTextField("", 10);
        entradaEdadMinima.setPlaceholder("Placeholder");
        entradaEdadMinima.setBounds(420, 195, 380, 50);
        entradaEdadMinima.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaEdadMinima.setForeground(Estilo.colorTexto);
        entradaEdadMinima.setBackground(Estilo.colorTerciario);
        entradaEdadMinima.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaEdadMinima.setMarginSize(25);
        entradaEdadMinima.setCornerRadius(40);
        entradaEdadMinima.setShadowSize(5);
        entradaEdadMinima.setShadowOpacity(0.2f);
        add(entradaEdadMinima);

        entradaMaxReproduccionesAnonimo = new JCustomTextField("", 10);
        entradaMaxReproduccionesAnonimo.setPlaceholder("Placeholder");
        entradaMaxReproduccionesAnonimo.setBounds(420, 245, 380, 50);
        entradaMaxReproduccionesAnonimo.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaMaxReproduccionesAnonimo.setForeground(Estilo.colorTexto);
        entradaMaxReproduccionesAnonimo.setBackground(Estilo.colorTerciario);
        entradaMaxReproduccionesAnonimo.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaMaxReproduccionesAnonimo.setMarginSize(25);
        entradaMaxReproduccionesAnonimo.setCornerRadius(40);
        entradaMaxReproduccionesAnonimo.setShadowSize(5);
        entradaMaxReproduccionesAnonimo.setShadowOpacity(0.2f);
        add(entradaMaxReproduccionesAnonimo);

        entradaMaxReproduccionesRegistrado = new JCustomTextField("", 10);
        entradaMaxReproduccionesRegistrado.setPlaceholder("Placeholder");
        entradaMaxReproduccionesRegistrado.setBounds(420, 295, 380, 50);
        entradaMaxReproduccionesRegistrado.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaMaxReproduccionesRegistrado.setForeground(Estilo.colorTexto);
        entradaMaxReproduccionesRegistrado.setBackground(Estilo.colorTerciario);
        entradaMaxReproduccionesRegistrado.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaMaxReproduccionesRegistrado.setMarginSize(25);
        entradaMaxReproduccionesRegistrado.setCornerRadius(40);
        entradaMaxReproduccionesRegistrado.setShadowSize(5);
        entradaMaxReproduccionesRegistrado.setShadowOpacity(0.2f);
        add(entradaMaxReproduccionesRegistrado);

        entradaCuotaPremium = new JCustomTextField("", 10);
        entradaCuotaPremium.setPlaceholder("Placeholder");
        entradaCuotaPremium.setBounds(420, 345, 380, 50);
        entradaCuotaPremium.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaCuotaPremium.setForeground(Estilo.colorTexto);
        entradaCuotaPremium.setBackground(Estilo.colorTerciario);
        entradaCuotaPremium.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaCuotaPremium.setMarginSize(25);
        entradaCuotaPremium.setCornerRadius(40);
        entradaCuotaPremium.setShadowSize(5);
        entradaCuotaPremium.setShadowOpacity(0.2f);
        add(entradaCuotaPremium);

        entradaMinReproduccionesPremium = new JCustomTextField("", 10);
        entradaMinReproduccionesPremium.setPlaceholder("Placeholder");
        entradaMinReproduccionesPremium.setBounds(420, 395, 380, 50);
        entradaMinReproduccionesPremium.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaMinReproduccionesPremium.setForeground(Estilo.colorTexto);
        entradaMinReproduccionesPremium.setBackground(Estilo.colorTerciario);
        entradaMinReproduccionesPremium.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaMinReproduccionesPremium.setMarginSize(25);
        entradaMinReproduccionesPremium.setCornerRadius(40);
        entradaMinReproduccionesPremium.setShadowSize(5);
        entradaMinReproduccionesPremium.setShadowOpacity(0.2f);
        add(entradaMinReproduccionesPremium);

        entradaCaracteresMinimos = new JCustomTextField("", 10);
        entradaCaracteresMinimos.setPlaceholder("Placeholder");
        entradaCaracteresMinimos.setBounds(420, 445, 380, 50);
        entradaCaracteresMinimos.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 20));
        entradaCaracteresMinimos.setForeground(Estilo.colorTexto);
        entradaCaracteresMinimos.setBackground(Estilo.colorTerciario);
        entradaCaracteresMinimos.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaCaracteresMinimos.setMarginSize(25);
        entradaCaracteresMinimos.setCornerRadius(40);
        entradaCaracteresMinimos.setShadowSize(5);
        entradaCaracteresMinimos.setShadowOpacity(0.2f);
        add(entradaCaracteresMinimos);
    }
}