package Recursos.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 * JScrollPane con bordes redondeados para tablas
 */
public class RoundedScrollPane extends JScrollPane {

    private int radius;
    private Color backgroundColor = Color.WHITE;

    public RoundedScrollPane(Component view, int radius) {
        super(view);
        this.radius = radius;
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        getViewport().setOpaque(false);
        
        // Ocultar scrollbars por defecto o hacerlos transparentes
        getVerticalScrollBar().setOpaque(false);
        getHorizontalScrollBar().setOpaque(false);
        
        if (view instanceof JTable) {
            JTable table = (JTable) view;
            table.setOpaque(false);
            table.getTableHeader().setOpaque(false);
        }
    }

    public RoundedScrollPane(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        getViewport().setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dibujar fondo redondeado completo
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
        
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No dibujar borde, el header ya tiene sus propias esquinas redondeadas
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }
}
