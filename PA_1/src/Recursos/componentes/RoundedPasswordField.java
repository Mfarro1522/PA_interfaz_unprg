package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Campo de contraseña con esquinas redondeadas
 * 
 * USO EN NETBEANS:
 * Custom creation: new Recursos.componentes.RoundedPasswordField()
 * Custom creation con redondeo: new Recursos.componentes.RoundedPasswordField(15)
 * 
 * Luego usar setBackground(), setForeground(), setFont() normalmente.
 */
public class RoundedPasswordField extends JPasswordField {

    private int redondeo;
    private Color borderColor = new Color(200, 200, 200);
    private int borderWidth = 1;
    private Color placeholderColor = new Color(150, 150, 150);
    private String placeholder = "";
    private boolean showBorder = true;

    public RoundedPasswordField() {
        this(15);
    }

    public RoundedPasswordField(int cantRedondeo) {
        super();
        this.redondeo = cantRedondeo;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        setBackground(Color.WHITE);
    }

    public void setRedondeo(int redondeo) {
        this.redondeo = redondeo;
        repaint();
    }
    
    public int getRedondeo() {
        return redondeo;
    }
    
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
    
    public Color getBorderColor() {
        return borderColor;
    }
    
    public void setBorderWidth(int width) {
        this.borderWidth = width;
        repaint();
    }
    
    public void setShowBorder(boolean show) {
        this.showBorder = show;
        repaint();
    }
    
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
    
    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo redondeado
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, redondeo, redondeo));

        // Borde redondeado
        if (showBorder && borderWidth > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, redondeo, redondeo));
        }
        
        g2.dispose();
        
        super.paintComponent(g);
        
        // Placeholder
        if (getPassword().length == 0 && !placeholder.isEmpty() && !hasFocus()) {
            Graphics2D g2p = (Graphics2D) g.create();
            g2p.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2p.setColor(placeholderColor);
            g2p.setFont(getFont());
            Insets insets = getInsets();
            g2p.drawString(placeholder, insets.left, getHeight() / 2 + g2p.getFontMetrics().getAscent() / 2 - 2);
            g2p.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No pintar borde por defecto, ya lo hacemos en paintComponent
    }
}
