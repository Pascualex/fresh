package fresh.interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BotonRedondeado extends JButton {
    private float radioEsquinas;

    public BotonRedondeado(String texto) {
        super("<html> <center>" + texto.replaceAll("\n", "<br/>") + "</center> </html>");
        setMargin(new Insets(0, 0, 0, 0));
    }

    public void setRadioEsquinas(float radioEsquinas) {
        this.radioEsquinas = radioEsquinas;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int buttonWidth = getWidth() - 1;
        int buttonHeight = getHeight() - 1;
        Shape shape = new RoundRectangle2D.Float(0, 0, buttonWidth, buttonHeight, 15, 15);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(shape);
        g2.dispose();
        super.paintComponent(g);
    }
}