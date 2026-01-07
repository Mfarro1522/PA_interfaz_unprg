package Recursos.componentes;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 * Borde con efecto de sombra suave
 * 
 * USO ESTÁTICO: ShadowBorder.paintShadow(g2, width, height, redondeo, shadowSize);
 */
public class ShadowBorder extends AbstractBorder {

    private int shadowSize;
    private Color shadowColor;

    public ShadowBorder() {
        this(15, new Color(0, 0, 0, 35));
    }

    public ShadowBorder(int size, Color color) {
        this.shadowSize = size;
        this.shadowColor = color;
    }
    
    /**
     * Método estático para pintar sombra suave
     * @param g2 Graphics2D
     * @param x posición X
     * @param y posición Y  
     * @param width ancho del panel
     * @param height alto del panel
     * @param cornerRadius radio de las esquinas
     * @param shadowSize tamaño de la sombra
     */
    public static void paintShadow(Graphics2D g2, int x, int y, int width, int height, int cornerRadius, int shadowSize) {
        int shadowOffsetX = 0;
        int shadowOffsetY = 4;
        
        for (int i = shadowSize; i >= 0; i--) {
            float ratio = (float) i / shadowSize;
            int alpha = (int) (35 * Math.pow(1 - ratio, 2));
            g2.setColor(new Color(0, 0, 0, alpha));
            
            int blur = i * 2;
            g2.fill(new RoundRectangle2D.Double(
                x + shadowOffsetX + (shadowSize - i) / 2.0, 
                y + shadowOffsetY + (shadowSize - i) / 2.0, 
                width + blur - (shadowSize - i), 
                height + blur - (shadowSize - i), 
                cornerRadius + i, cornerRadius + i
            ));
        }
    }
    
    /**
     * Versión simplificada
     */
    public static void paintShadow(Graphics2D g2, int width, int height, int cornerRadius) {
        paintShadow(g2, 0, 0, width, height, cornerRadius, 15);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintShadow(g2, x, y, width - shadowSize, height - shadowSize, 12, shadowSize);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(2, 2, shadowSize + 2, shadowSize + 2);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = 2;
        insets.top = 2;
        insets.right = shadowSize + 2;
        insets.bottom = shadowSize + 2;
        return insets;
    }
}
