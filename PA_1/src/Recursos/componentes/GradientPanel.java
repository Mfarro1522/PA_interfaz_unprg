package Recursos.componentes;

import javax.swing.*;
import java.awt.*;

/***
 * Panel con fondo degradado vertical (de arriba hacia abajo).
 * 
 * USO EN NETBEANS:
 *   new Recursos.componentes.GradientPanel()
 *   new Recursos.componentes.GradientPanel(new java.awt.Color(30,30,120), new java.awt.Color(50,100,200))
 * */

public class GradientPanel extends JPanel {

    private Color colorInicio;
    private Color colorFin;

    public GradientPanel() {
        this(new Color(30, 30, 120), new Color(50, 100, 200)); 
    }

    public GradientPanel(Color colorInicio, Color colorFin) {
        this.colorInicio = colorInicio;
        this.colorFin = colorFin;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, colorInicio, 0, h, colorFin);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        super.paintComponent(g);
    }

    public Color getColorInicio() { return colorInicio; }
    public void setColorInicio(Color colorInicio) { this.colorInicio = colorInicio; repaint(); }
    public Color getColorFin() { return colorFin; }
    public void setColorFin(Color colorFin) { this.colorFin = colorFin; repaint(); }
}
