package fresh.interfaz.swing;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.Font;

public class JCustomComboBox extends JComboBox {
    private static final long serialVersionUID = 0;

    private Color behindColor;
    private int cornerRadius;
    private int shadowSize;
    private float shadowOpacity;
    private Font mainFont;
    
    public JCustomComboBox(Object[] info) {
        super(info);

        cornerRadius = 0;
        shadowSize = 0;
        shadowOpacity = 0;
        behindColor = null;
        mainFont = null;

        setUI(new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return null;
            }
        });
    }

    public void setBehindColor(Color behindColor) {
        this.behindColor = behindColor;
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

    public void setMainFont(Font mainFont) {
        this.mainFont = mainFont;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (behindColor != null) {
            g2d.setColor(behindColor);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

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

        if (mainFont != null) g2d.setFont(mainFont);
        g2d.setColor(getForeground());
        EmptyBorder border = (EmptyBorder) getBorder();
        int xMargin = border.getBorderInsets().left;
        int yMargin = (getHeight()-getFont().getSize())/2;
        g2d.drawString((String) getSelectedItem(), xMargin, getFont().getSize()*0.9f+yMargin);
    }
}