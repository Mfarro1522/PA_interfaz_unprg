package Custom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Panel con esquinas redondeadas.
 * 
 * USO EN NETBEANS:
 * Custom creation: new Custom.RoundedPanel()
 * Custom creation con redondeo: new Custom.RoundedPanel(20)
 * 
 * Luego usar setBackground() para el color.
 */
public class RoundedPanel extends JPanel {

    private int redondeo;

    public RoundedPanel() {
        this(15);
    }

    public RoundedPanel(int cantRedondeo) {
        super();
        this.redondeo = cantRedondeo;
        setOpaque(false);
    }

    public void setRedondeo(int redondeo) {
        this.redondeo = redondeo;
        repaint();
    }
    
    public int getRedondeo() {
        return redondeo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), redondeo, redondeo));

        g2.dispose();
        
        super.paintComponent(g);
    }
}
