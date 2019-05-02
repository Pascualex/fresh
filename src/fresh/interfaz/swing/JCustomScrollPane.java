package fresh.interfaz.swing;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class JCustomScrollPane extends JScrollPane {
    private static final long serialVersionUID = 0;

    private CustomScrollBarUI customScrollBarUI;

    public JCustomScrollPane(Component c) {
        super(c);

        customScrollBarUI = new CustomScrollBarUI();

        setBorder(null);
        getVerticalScrollBar().setUI(customScrollBarUI);
        getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
    }

    public void setThumbColor(Color thumbColor) {
        customScrollBarUI.setThumbColor(thumbColor);
    }

    public void setTrackColor(Color trackColor) {
        customScrollBarUI.setTrackColor(trackColor);
    }

    public void setBehindColor(Color behindColor) {
        customScrollBarUI.setBehindColor(behindColor);
    }
    
    public void setHeight(int height) {
        customScrollBarUI.setHeight(height);
    }

    private static class CustomScrollBarUI extends BasicScrollBarUI {
        private Color thumbColor;
        private Color trackColor;
        private Color behindColor;
        private int height;

        public CustomScrollBarUI() {
            thumbColor = null;
            trackColor = null;
            behindColor = null;
            height = 0;
        }
        
        public void setThumbColor(Color thumbColor) {
            this.thumbColor = thumbColor;
        }

        public void setTrackColor(Color trackColor) {
            this.trackColor = trackColor;
        }

        public void setBehindColor(Color behindColor) {
            this.behindColor = behindColor;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (behindColor != null) {
                g2d.setColor(behindColor);
                g2d.fillRect(r.x, r.y, r.width, r.height);
            }

            if (trackColor != null) {
                if (height > 0) {
                    g2d.setColor(trackColor.darker());
                    g2d.fillRoundRect(r.x, r.y, r.width, r.height-height, 20, 20);
                }

                g2d.setColor(trackColor);
                g2d.fillRoundRect(r.x, r.y+height, r.width, r.height-2*height, 20, 20);
            }
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (thumbColor != null) {
                if (height > 0) {
                    int offset = height-((getTrackBounds().y+getTrackBounds().height)-(getThumbBounds().y+getThumbBounds().height));

                    if (offset < 0) offset = 0;

                    g2d.setColor(thumbColor.darker());
                    g2d.fillRoundRect(r.x, r.y, r.width, r.height-offset, 20, 20);
                }

                g2d.setColor(thumbColor);
                g2d.fillRoundRect(r.x, r.y, r.width, r.height-height, 20, 20);
            }
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            Dimension zeroDim = new Dimension(0, 0);

            button.setPreferredSize(zeroDim);
            button.setMinimumSize(zeroDim);
            button.setMaximumSize(zeroDim);

            return button;
        }
    }
}