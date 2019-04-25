package fresh.interfaz.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class JCustomTextField extends JTextField {
    private static final long serialVersionUID = 0;
    
    private int cornerRadius;    
    private int shadowSize;
    private float shadowOpacity;
    private String placeholder;
    private Color placeholderColor;

    public JCustomTextField(String text, int columns) {
        super(text, columns);

        cornerRadius = 0;
        shadowSize = 0;

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

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
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

        super.paintComponent(g);
        if (placeholder != null && getText().length() == 0) {
            if (placeholderColor != null) g2d.setColor(placeholderColor);
            else g2d.setColor(getForeground());
            EmptyBorder border = (EmptyBorder) getBorder();
            int xMargin = border.getBorderInsets().left;
            int yMargin = (getHeight()-getFont().getSize())/2;
            g2d.drawString(placeholder, xMargin, getFont().getSize()*0.9f+yMargin);
        }
    }
}