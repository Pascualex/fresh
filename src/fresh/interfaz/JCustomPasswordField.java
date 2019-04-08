package fresh.interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class JCustomPasswordField extends JPasswordField {
    private int cornerRadius;    
    private int shadowSize;
    private float shadowOpacity;

    public JCustomPasswordField(int columns) {
        super(columns);

        cornerRadius = 0;
        shadowSize = 0;
        shadowOpacity = 0;

        setOpaque(false);
        setBorder(null);
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public void setMarginSize(int marginSize) {
        setBorder(new EmptyBorder(0, marginSize, 0, marginSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (shadowSize > 0 && shadowOpacity > 0) {
            g2d.setColor(Color.black);
            g2d.setComposite(AlphaComposite.SrcOver.derive(shadowOpacity/shadowSize));
            for (int i = 0; i < shadowSize; i++) {
                g2d.fillRoundRect(i, i, getWidth()-2*i, getHeight()-2*i, cornerRadius-2*i, cornerRadius-2*i);
            }            
            g2d.setComposite(AlphaComposite.SrcOver);
        }

        g2d.setColor(getBackground());
        g2d.fillRoundRect(shadowSize, shadowSize, getWidth()-2*shadowSize, getHeight()-2*shadowSize, cornerRadius-2*shadowSize, cornerRadius-2*shadowSize);

        g2d.dispose();

        super.paintComponent(g);
    }
}