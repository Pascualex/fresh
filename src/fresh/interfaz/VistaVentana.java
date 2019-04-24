package fresh.interfaz;

import javax.swing.*;

public class VistaVentana extends JFrame {
    public JPanel barraSuperior;
    public JCustomButton botonMinimizarAplicacion;
    public JCustomButton botonCerrarAplicacion;

    public VistaVentana() {
        super("Swing");

        // Configura la ventana principal
        setSize(Estilo.anchura, Estilo.altura);
        setResizable(false);
        setLayout(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Estilo.colorSecundario);

        // Crea y configura el panel de inicio
        barraSuperior = new JPanel();
        barraSuperior.setBounds(0, 0, Estilo.anchura, 75);
        barraSuperior.setBackground(Estilo.colorSecundario);
        barraSuperior.setLayout(null);
        barraSuperior.setVisible(false);
        add(barraSuperior);

        botonMinimizarAplicacion = new JCustomButton("⎯");
        botonMinimizarAplicacion.setBounds(Estilo.anchura-125, 10, 50, 55);
        botonMinimizarAplicacion.setBackground(Estilo.colorBotonMinimizar);
        botonMinimizarAplicacion.setPressedBackgound(Estilo.colorBotonMinimizar.brighter());
        botonMinimizarAplicacion.setCornerRadius(70);
        botonMinimizarAplicacion.setHeight(5);       
        botonMinimizarAplicacion.setShadowSize(5);
        botonMinimizarAplicacion.setShadowOpacity(0.4f);
        barraSuperior.add(botonMinimizarAplicacion);

        botonCerrarAplicacion = new JCustomButton("✖");
        botonCerrarAplicacion.setBounds(Estilo.anchura-65, 10, 50, 55);
        botonCerrarAplicacion.setBackground(Estilo.colorBotonCerrar);
        botonCerrarAplicacion.setPressedBackgound(Estilo.colorBotonCerrar.brighter());
        botonCerrarAplicacion.setCornerRadius(70);
        botonCerrarAplicacion.setHeight(5);       
        botonCerrarAplicacion.setShadowSize(5);
        botonCerrarAplicacion.setShadowOpacity(0.4f); 
        barraSuperior.add(botonCerrarAplicacion);

        barraSuperior.setVisible(true);
        setVisible(true);
    }
}