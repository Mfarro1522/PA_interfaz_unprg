package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Toggle Switch moderno para reemplazar JCheckBox
 * 
 * USO EN NETBEANS:
 * Custom creation: new Recursos.componentes.ToggleSwitch()
 * 
 * Métodos útiles:
 * - setSelected(boolean) - activar/desactivar
 * - isSelected() - obtener estado
 * - setOnColor(Color) - color cuando está activo
 * - setOffColor(Color) - color cuando está inactivo
 * - setThumbColor(Color) - color de la bolita
 * 
 * Para escuchar cambios usar addActionListener() como con un botón normal.
 */
public class ToggleSwitch extends JCheckBox {

    private Color onColor = new Color(22, 86, 222); // Azul
    private Color offColor = new Color(180, 180, 180); // Gris
    private Color thumbColor = Color.WHITE;
    private Color hoverOnColor = new Color(30, 100, 240);
    private Color hoverOffColor = new Color(160, 160, 160);
    
    private boolean hover = false;
    private boolean animating = false;
    private float thumbPosition = 0f; // 0 = izquierda (off), 1 = derecha (on)
    private Timer animationTimer;
    
    private int switchWidth = 44;
    private int switchHeight = 24;
    private int thumbPadding = 3;

    public ToggleSwitch() {
        super();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Tamaño preferido basado en el switch
        setPreferredSize(new Dimension(switchWidth, switchHeight));
        setMinimumSize(new Dimension(switchWidth, switchHeight));
        
        // Listener para hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
        
        // Timer para animación suave
        animationTimer = new Timer(10, e -> {
            float target = isSelected() ? 1f : 0f;
            float speed = 0.15f;
            
            if (Math.abs(thumbPosition - target) < speed) {
                thumbPosition = target;
                animationTimer.stop();
                animating = false;
            } else {
                thumbPosition += (target > thumbPosition) ? speed : -speed;
            }
            repaint();
        });
    }
    
    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (!animating) {
            animating = true;
            animationTimer.start();
        }
    }

    public void setSwitchSize(int width, int height) {
        this.switchWidth = width;
        this.switchHeight = height;
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        repaint();
    }
    
    public void setOnColor(Color color) {
        this.onColor = color;
        this.hoverOnColor = brighter(color, 0.1f);
        repaint();
    }
    
    public Color getOnColor() {
        return onColor;
    }
    
    public void setOffColor(Color color) {
        this.offColor = color;
        this.hoverOffColor = darker(color, 0.1f);
        repaint();
    }
    
    public Color getOffColor() {
        return offColor;
    }
    
    public void setThumbColor(Color color) {
        this.thumbColor = color;
        repaint();
    }
    
    public Color getThumbColor() {
        return thumbColor;
    }
    
    private Color brighter(Color c, float factor) {
        int r = Math.min(255, (int) (c.getRed() + 255 * factor));
        int g = Math.min(255, (int) (c.getGreen() + 255 * factor));
        int b = Math.min(255, (int) (c.getBlue() + 255 * factor));
        return new Color(r, g, b);
    }
    
    private Color darker(Color c, float factor) {
        int r = Math.max(0, (int) (c.getRed() * (1 - factor)));
        int g = Math.max(0, (int) (c.getGreen() * (1 - factor)));
        int b = Math.max(0, (int) (c.getBlue() * (1 - factor)));
        return new Color(r, g, b);
    }
    
    private Color interpolateColor(Color c1, Color c2, float ratio) {
        int r = (int) (c1.getRed() + (c2.getRed() - c1.getRed()) * ratio);
        int g = (int) (c1.getGreen() + (c2.getGreen() - c1.getGreen()) * ratio);
        int b = (int) (c1.getBlue() + (c2.getBlue() - c1.getBlue()) * ratio);
        return new Color(r, g, b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth();
        int h = getHeight();
        
        // Calcular posiciones
        int trackHeight = Math.min(h, switchHeight);
        int trackWidth = Math.min(w, switchWidth);
        int trackX = (w - trackWidth) / 2;
        int trackY = (h - trackHeight) / 2;
        int cornerRadius = trackHeight;
        
        // Color del track interpolado
        Color trackColorBase = interpolateColor(offColor, onColor, thumbPosition);
        Color trackColor = hover ? 
            interpolateColor(hoverOffColor, hoverOnColor, thumbPosition) : 
            trackColorBase;
        
        // Dibujar track (fondo del switch)
        g2.setColor(trackColor);
        g2.fill(new RoundRectangle2D.Double(trackX, trackY, trackWidth, trackHeight, cornerRadius, cornerRadius));
        
        // Calcular posición del thumb (bolita)
        int thumbDiameter = trackHeight - (thumbPadding * 2);
        int thumbMinX = trackX + thumbPadding;
        int thumbMaxX = trackX + trackWidth - thumbDiameter - thumbPadding;
        int thumbX = (int) (thumbMinX + (thumbMaxX - thumbMinX) * thumbPosition);
        int thumbY = trackY + thumbPadding;
        
        // Sombra del thumb
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fill(new Ellipse2D.Double(thumbX + 1, thumbY + 2, thumbDiameter, thumbDiameter));
        
        // Dibujar thumb (bolita blanca)
        g2.setColor(thumbColor);
        g2.fill(new Ellipse2D.Double(thumbX, thumbY, thumbDiameter, thumbDiameter));
        
        g2.dispose();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(switchWidth, switchHeight);
    }
}
