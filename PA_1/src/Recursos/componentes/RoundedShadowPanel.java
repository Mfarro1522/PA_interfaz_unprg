package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Panel con esquinas redondeadas y sombra opcional
 * 
 * USO EN NETBEANS:
 * Custom creation: new Recursos.componentes.RoundedShadowPanel()
 * Custom creation con redondeo: new Recursos.componentes.RoundedShadowPanel(20)
 * Custom creation con sombra: new Recursos.componentes.RoundedShadowPanel(20, true)
 * 
 * Luego usar setBackground() para el color.
 */
public class RoundedShadowPanel extends JPanel {

    private int redondeo;
    private boolean mostrarSombra;
    private int shadowSize = 15;
    private Color shadowColor = new Color(0, 0, 0, 60);

    public RoundedShadowPanel() {
        this(15, false);
    }

    public RoundedShadowPanel(int cantRedondeo) {
        this(cantRedondeo, false);
    }

    public RoundedShadowPanel(int cantRedondeo, boolean sombra) {
        super();
        this.redondeo = cantRedondeo;
        this.mostrarSombra = sombra;
        setOpaque(false);
        setLayout(null); // Permite posicionamiento libre en NetBeans
    }

    public void setRedondeo(int redondeo) {
        this.redondeo = redondeo;
        repaint();
    }
    
    public int getRedondeo() {
        return redondeo;
    }
    
    public void setSombra(boolean mostrar) {
        this.mostrarSombra = mostrar;
        repaint();
    }
    
    public boolean isSombra() {
        return mostrarSombra;
    }
    
    public void setShadowSize(int size) {
        this.shadowSize = size;
        repaint();
    }
    
    public int getShadowSize() {
        return shadowSize;
    }
    
    public void setShadowColor(Color color) {
        this.shadowColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int offsetSombra = mostrarSombra ? shadowSize : 0;
        int panelWidth = getWidth() - offsetSombra;
        int panelHeight = getHeight() - offsetSombra;

        // Dibujar sombra
        if (mostrarSombra) {
            ShadowBorder.paintShadow(g2, 0, 0, panelWidth, panelHeight, redondeo, shadowSize);
        }

        // Dibujar panel redondeado
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, panelWidth, panelHeight, redondeo, redondeo));

        g2.dispose();
        
        super.paintComponent(g);
    }
}
