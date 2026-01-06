package Custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Botón redondeado con sombra opcional
 * 
 * USO EN NETBEANS:
 * Custom creation: new Custom.RoundedButton()
 * Custom creation con redondeo: new Custom.RoundedButton(20)
 * Custom creation con sombra: new Custom.RoundedButton(20, true)
 * 
 * Luego usar setBackground() para el color y setText() para el texto
 */
public class RoundedButton extends JButton {

    private int redondeo;
    private boolean mostrarSombra;
    private int shadowSize = 10;
    private boolean hover = false;

    public RoundedButton() {
        this(15, false);
    }

    public RoundedButton(int cantRedondeo) {
        this(cantRedondeo, false);
    }

    public RoundedButton(int cantRedondeo, boolean sombra) {
        super();
        this.redondeo = cantRedondeo;
        this.mostrarSombra = sombra;
        
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
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
    }

    public void setSombra(boolean mostrar) {
        this.mostrarSombra = mostrar;
        repaint();
    }

    public void setRedondeo(int redondeo) {
        this.redondeo = redondeo;
        repaint();
    }

    private Color oscurecer(Color c, float factor) {
        int r = (int) (c.getRed() * factor);
        int g = (int) (c.getGreen() * factor);
        int b = (int) (c.getBlue() * factor);
        return new Color(Math.max(r, 0), Math.max(g, 0), Math.max(b, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int offsetSombra = mostrarSombra ? shadowSize : 0;
        int btnWidth = getWidth() - offsetSombra;
        int btnHeight = getHeight() - offsetSombra;

        // Dibujar sombra
        if (mostrarSombra) {
            ShadowBorder.paintShadow(g2, 0, 0, btnWidth, btnHeight, redondeo, shadowSize);
        }

        // Usa getBackground() para el color (lo que pones en NetBeans)
        Color colorBase = getBackground();
        Color colorFinal = hover ? oscurecer(colorBase, 0.8f) : colorBase;
        g2.setColor(colorFinal);
        g2.fill(new RoundRectangle2D.Double(0, 0, btnWidth, btnHeight, redondeo, redondeo));

        // Dibujar texto centrado
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        int textX = (btnWidth - fm.stringWidth(getText())) / 2;
        int textY = (btnHeight + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }
}
