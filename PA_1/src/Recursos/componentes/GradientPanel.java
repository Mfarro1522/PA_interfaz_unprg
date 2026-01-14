package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Panel versátil con soporte para gradiente y/o esquinas redondeadas.
 * 
 * USO EN NETBEANS:
 *   - Solo gradiente:
 *       new Recursos.componentes.GradientPanel(new java.awt.Color(30,30,120), new java.awt.Color(50,100,200))
 *   
 *   - Solo redondeado:
 *       new Recursos.componentes.GradientPanel(20)
 *   
 *   - Gradiente + redondeado:
 *       new Recursos.componentes.GradientPanel(new java.awt.Color(30,30,120), new java.awt.Color(50,100,200), 20)
 */
public class GradientPanel extends JPanel {

    private Color colorInicio;
    private Color colorFin;
    private int redondeo;
    private boolean usarGradiente;
    private boolean usarRedondeo;

    /**
     * Constructor solo con gradiente (sin redondeo).
     */
    public GradientPanel(Color colorInicio, Color colorFin) {
        this.colorInicio = colorInicio;
        this.colorFin = colorFin;
        this.redondeo = 0;
        this.usarGradiente = true;
        this.usarRedondeo = false;
        setOpaque(false);
    }

    /**
     * Constructor solo con redondeo (sin gradiente).
     * Usar setBackground() para definir el color.
     */
    public GradientPanel(int redondeo) {
        this.colorInicio = null;
        this.colorFin = null;
        this.redondeo = redondeo;
        this.usarGradiente = false;
        this.usarRedondeo = true;
        setOpaque(false);
    }

    /**
     * Constructor con gradiente Y redondeo.
     */
    public GradientPanel(Color colorInicio, Color colorFin, int redondeo) {
        this.colorInicio = colorInicio;
        this.colorFin = colorFin;
        this.redondeo = redondeo;
        this.usarGradiente = true;
        this.usarRedondeo = true;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int w = getWidth();
        int h = getHeight();
        Shape forma;

        // Definir la forma (redondeada o rectangular)
        if (usarRedondeo && redondeo > 0) {
            forma = new RoundRectangle2D.Double(0, 0, w, h, redondeo, redondeo);
        } else {
            forma = new Rectangle(0, 0, w, h);
        }

        // Aplicar gradiente o color sólido
        if (usarGradiente && colorInicio != null && colorFin != null) {
            GradientPaint gp = new GradientPaint(0, 0, colorInicio, 0, h, colorFin);
            g2.setPaint(gp);
        } else {
            g2.setColor(getBackground());
        }

        g2.fill(forma);
        g2.dispose();

        super.paintComponent(g);
    }

    // Getters y Setters
    public Color getColorInicio() { return colorInicio; }
    public void setColorInicio(Color colorInicio) { 
        this.colorInicio = colorInicio; 
        this.usarGradiente = (colorInicio != null && colorFin != null);
        repaint(); 
    }

    public Color getColorFin() { return colorFin; }
    public void setColorFin(Color colorFin) { 
        this.colorFin = colorFin; 
        this.usarGradiente = (colorInicio != null && colorFin != null);
        repaint(); 
    }

    public int getRedondeo() { return redondeo; }
    public void setRedondeo(int redondeo) { 
        this.redondeo = redondeo; 
        this.usarRedondeo = (redondeo > 0);
        repaint(); 
    }

    public boolean isUsarGradiente() { return usarGradiente; }
    public boolean isUsarRedondeo() { return usarRedondeo; }
}
