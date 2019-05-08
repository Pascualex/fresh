package fresh.interfaz.vistas;

import fresh.interfaz.Estilo;
import fresh.interfaz.swing.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

public class VistaPagarPremium extends JPanel{
    private static final long serialVersionUID = 0;	
    
    public JPanel panelPagarPremium;
	public JLabel textoPagarPremium;
    public JCustomPanel separador;
	public JCustomTextField entradaTarjeta;
	public JCustomButton botonOK;
	
	public JPanel panelMensaje;
    public JCustomButton botonVolver;
    public JCustomButton botonPagado;
    public JLabel textoErrorEntrada;
    public JLabel textoOperacionInvalida;
    public JLabel textoYaPremium;
    public JLabel textoTarjetaInvalida;
    public JLabel textoFalloInternet;
    public JLabel textoPagoRechazado;
    public JLabel textoOK;
    public JLabel textoError;
	
	public VistaPagarPremium() {		
		setBounds(260, 140, Estilo.anchura-260, 540);
		setOpaque(false);
		setLayout(null);
		setVisible(false);
		
		// Crea y configura el panel de inicio de sesión
        panelPagarPremium = new JPanel();
        panelPagarPremium.setBounds(0, 0, Estilo.anchura-260, 540);
		panelPagarPremium.setOpaque(false);
		panelPagarPremium.setLayout(null);
		panelPagarPremium.setVisible(true);
        add(panelPagarPremium);
		
		textoPagarPremium = new JLabel("Introduce tu tarjeta de crédito");
        textoPagarPremium.setBounds(35, 25, 480, 40);
		textoPagarPremium.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
		textoPagarPremium.setForeground(Estilo.colorTexto);
		textoPagarPremium.setHorizontalAlignment(JLabel.LEFT);
		panelPagarPremium.add(textoPagarPremium);
		
		separador = new JCustomPanel();
        separador.setBounds(20, 77, Estilo.anchura-260-92, 3);
		separador.setBackground(Estilo.colorPrimario.darker());
		separador.setCornerRadius(5);
		panelPagarPremium.add(separador);
        
        entradaTarjeta = new JCustomTextField("", 10);
        entradaTarjeta.setPlaceholder("Tarjeta de crédito...");
        entradaTarjeta.setBounds(30, 100, Estilo.anchura-400, 80);
        entradaTarjeta.setFont(new Font(Estilo.fuentePredeterminada, Font.PLAIN, 25));
        entradaTarjeta.setForeground(Estilo.colorTexto);
        entradaTarjeta.setPlaceholderColor(Estilo.colorPlaceholder);
        entradaTarjeta.setBackground(Estilo.colorTerciario);
        entradaTarjeta.setMarginSize(25);
        entradaTarjeta.setCornerRadius(70);
        entradaTarjeta.setShadowSize(5);
        entradaTarjeta.setShadowOpacity(0.2f);
        panelPagarPremium.add(entradaTarjeta);
        
        botonOK = new JCustomButton("Pagar");
        botonOK.setBounds(708, 0, 160, 60);
        botonOK.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonOK.setForeground(Estilo.colorTexto);
        botonOK.setBackground(new Color(240, 240, 100));
        botonOK.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonOK.setCornerRadius(70);
        botonOK.setHeight(5);       
        botonOK.setShadowSize(5);
        botonOK.setShadowOpacity(0.4f);
        panelPagarPremium.add(botonOK);
        
        // Configura el panel de los mensajes
        panelMensaje = new JPanel();
        panelMensaje.setBounds(50, 15, Estilo.anchura-400, Estilo.altura-300);
        panelMensaje.setBackground(Estilo.colorTerciario);
        panelMensaje.setLayout(null);
        panelMensaje.setVisible(false);
        add(panelMensaje);
        
        botonVolver = new JCustomButton("Volver");
        botonVolver.setBounds(300, 300, 200, 80);
        botonVolver.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 25));
        botonVolver.setForeground(Estilo.colorTexto);
        botonVolver.setBackground(new Color(240, 240, 100));
        botonVolver.setPressedBackgound(new Color(220, 220, 95).brighter());
        botonVolver.setCornerRadius(70);
        botonVolver.setHeight(5);       
        botonVolver.setShadowSize(5);
        botonVolver.setShadowOpacity(0.4f);
        panelMensaje.add(botonVolver);
        
        textoErrorEntrada = new JLabel("Introduce tu tarjeta");
        textoErrorEntrada.setBounds(150, 150, 500, 100);
        textoErrorEntrada.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoErrorEntrada.setForeground(Estilo.colorTexto);
        textoErrorEntrada.setHorizontalAlignment(JLabel.CENTER);
        textoErrorEntrada.setVisible(false);
        panelMensaje.add(textoErrorEntrada);
        
        textoOperacionInvalida = new JLabel("Tienes que estar registrado");
        textoOperacionInvalida.setBounds(150, 150, 500, 100);
        textoOperacionInvalida.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoOperacionInvalida.setForeground(Estilo.colorTexto);
        textoOperacionInvalida.setHorizontalAlignment(JLabel.CENTER);
        textoOperacionInvalida.setVisible(false);
        panelMensaje.add(textoOperacionInvalida);
        
        textoYaPremium = new JLabel("Ya eres premium");
        textoYaPremium.setBounds(150, 150, 500, 100);
        textoYaPremium.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoYaPremium.setForeground(Estilo.colorTexto);
        textoYaPremium.setHorizontalAlignment(JLabel.CENTER);
        textoYaPremium.setVisible(false);
        panelMensaje.add(textoYaPremium);
        
        textoTarjetaInvalida = new JLabel("Tarjeta Invalida");
        textoTarjetaInvalida.setBounds(150, 150, 500, 100);
        textoTarjetaInvalida.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoTarjetaInvalida.setForeground(Estilo.colorTexto);
        textoTarjetaInvalida.setHorizontalAlignment(JLabel.CENTER);
        textoTarjetaInvalida.setVisible(false);
        panelMensaje.add(textoTarjetaInvalida);
        
        textoFalloInternet = new JLabel("Fallo Internet");
        textoFalloInternet.setBounds(150, 150, 500, 100);
        textoFalloInternet.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoFalloInternet.setForeground(Estilo.colorTexto);
        textoFalloInternet.setHorizontalAlignment(JLabel.CENTER);
        textoFalloInternet.setVisible(false);
        panelMensaje.add(textoFalloInternet);
        
        textoPagoRechazado = new JLabel("Pago rechazado");
        textoPagoRechazado.setBounds(150, 150, 500, 100);
        textoPagoRechazado.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoPagoRechazado.setForeground(Estilo.colorTexto);
        textoPagoRechazado.setHorizontalAlignment(JLabel.CENTER);
        textoPagoRechazado.setVisible(false);
        panelMensaje.add(textoPagoRechazado);
        
        textoOK = new JLabel("Ya eres premium!");
        textoOK.setBounds(150, 150, 500, 100);
        textoOK.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 20));
        textoOK.setForeground(Estilo.colorTexto);
        textoOK.setHorizontalAlignment(JLabel.CENTER);
        textoOK.setVisible(false);
        panelMensaje.add(textoOK);
        
        textoError = new JLabel("Error interno en la aplicación");
        textoError.setBounds(150, 150, 500, 100);
        textoError.setFont(new Font(Estilo.fuentePredeterminada, Font.BOLD, 30));
        textoError.setForeground(Estilo.colorTexto);
        textoError.setHorizontalAlignment(JLabel.CENTER);
        textoError.setVisible(false);
        panelMensaje.add(textoError);
	
	}

}
