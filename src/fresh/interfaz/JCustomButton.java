package fresh.interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class JCustomButton extends JButton {
    private int cornerRadius;
    private Color pressedBackground;
    private int height;
    private int shadowSize;
    private float shadowOpacity;

    public JCustomButton(String text) {
        super(text);

        cornerRadius = 0;
        pressedBackground = null;
        height = 0;
        shadowSize = 0;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setPressedBackgound(Color pressedBackground) {
        this.pressedBackground = pressedBackground;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int textOffset;

        if (shadowSize > 0 && shadowOpacity > 0) {
            g2d.setColor(Color.black);
            g2d.setComposite(AlphaComposite.SrcOver.derive(shadowOpacity/shadowSize));
            for (int i = 0; i < shadowSize; i++) {
                g2d.fillRoundRect(i, i+height, getWidth()-2*i, getHeight()-2*i-height, cornerRadius-2*i, cornerRadius-2*i);
            }            
            g2d.setComposite(AlphaComposite.SrcOver);
        }

        if (!getModel().isPressed()) {
            if (height > 0) {
                g2d.setColor(getBackground().darker());
                g2d.fillRoundRect(shadowSize, shadowSize, getWidth()-2*shadowSize, getHeight()-2*shadowSize, cornerRadius-2*shadowSize, cornerRadius-2*shadowSize);
            }
    
            g2d.setColor(getBackground());
            g2d.fillRoundRect(shadowSize, shadowSize, getWidth()-2*shadowSize, getHeight()-2*shadowSize-height, cornerRadius-2*shadowSize, cornerRadius-2*shadowSize);

            textOffset = -height/2;
        } else {
            if (pressedBackground != null) g2d.setColor(pressedBackground);
            else g2d.setColor(getBackground());
            g2d.fillRoundRect(shadowSize, shadowSize+height, getWidth()-2*shadowSize, getHeight()-2*shadowSize-height, cornerRadius-2*shadowSize, cornerRadius-2*shadowSize);

            textOffset = height-height/2;
        }

        g2d.setColor(getForeground());
        FontRenderContext frc = new FontRenderContext(null, false, false);
        Rectangle2D r = getFont().getStringBounds(getText(), frc); 
        int xMargin = (int) (getWidth()-r.getWidth())/2;
        int yMargin = (getHeight()-getFont().getSize())/2;
        g2d.drawString(getText(), xMargin, textOffset+getFont().getSize()*0.9f+yMargin);          

        g2d.dispose();
    }
}