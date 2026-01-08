package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Botón redondeado con degradado, borde o sombra
 * 
 * USO EN NETBEANS (Custom Creation Code):
 * 
 * Sin efectos (solo redondeo):
 *   new Recursos.componentes.RoundedButton(colorIzq, colorDer, redondeo, 0)
 * 
 * Con borde negro:
 *   new Recursos.componentes.RoundedButton(colorIzq, colorDer, redondeo, 1)
 * 
 * Con sombra:
 *   new Recursos.componentes.RoundedButton(colorIzq, colorDer, redondeo, 2)
 * 
 * EJEMPLOS:
 *   new Recursos.componentes.RoundedButton(new java.awt.Color(255,100,100), new java.awt.Color(100,100,255), 20, 0)
 *   new Recursos.componentes.RoundedButton(new java.awt.Color(255,100,100), new java.awt.Color(100,100,255), 20, 1)
 *   new Recursos.componentes.RoundedButton(new java.awt.Color(255,100,100), new java.awt.Color(100,100,255), 20, 2)
 * 
 * Color sólido (mismo color en ambos):
 *   new Recursos.componentes.RoundedButton(new java.awt.Color(255,255,255), new java.awt.Color(255,255,255), 20, 1)
 */
public class RoundedButton extends JButton {

    // Constantes para el tipo de efecto
    public static final int SIN_EFECTO = 0;
    public static final int CON_BORDE = 1;
    public static final int CON_SOMBRA = 2;

    private int redondeo;
    private int tipoEfecto;
    private int shadowSize = 10;
    private int grosorBorde = 2;
    private Color colorBorde = Color.BLACK;
    private boolean hover = false;
    
    private Color colorIzquierdo;
    private Color colorDerecho;

    /**
     * Constructor por defecto
     */
    public RoundedButton() {
        this(null, null, 15, SIN_EFECTO);
    }

    /**
     * Constructor simple con redondeo
     */
    public RoundedButton(int cantRedondeo) {
        this(null, null, cantRedondeo, SIN_EFECTO);
    }

    /**
     * Constructor principal - USAR ESTE EN NETBEANS
     * @param colorIzquierdo Color izquierdo del degradado (null = usa background)
     * @param colorDerecho Color derecho del degradado (null = usa background)
     * @param cantRedondeo Radio del redondeo de esquinas
     * @param efecto 0 = sin efecto, 1 = borde negro, 2 = sombra
     */
    public RoundedButton(Color colorIzquierdo, Color colorDerecho, int cantRedondeo, int efecto) {
        super();
        this.colorIzquierdo = colorIzquierdo;
        this.colorDerecho = colorDerecho;
        this.redondeo = cantRedondeo;
        this.tipoEfecto = efecto;
        init();
    }

    private void init() {
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

    // ========== SETTERS ==========

    public void setRedondeo(int redondeo) {
        this.redondeo = redondeo;
        repaint();
    }

    public void setTipoEfecto(int efecto) {
        this.tipoEfecto = efecto;
        repaint();
    }

    public void setColorIzquierdo(Color color) {
        this.colorIzquierdo = color;
        repaint();
    }

    public void setColorDerecho(Color color) {
        this.colorDerecho = color;
        repaint();
    }

    public void setColorBorde(Color color) {
        this.colorBorde = color;
        repaint();
    }

    public void setGrosorBorde(int grosor) {
        this.grosorBorde = grosor;
        repaint();
    }

    // ========== GETTERS ==========

    public int getRedondeo() {
        return redondeo;
    }

    public int getTipoEfecto() {
        return tipoEfecto;
    }

    public Color getColorIzquierdo() {
        return colorIzquierdo;
    }

    public Color getColorDerecho() {
        return colorDerecho;
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public int getGrosorBorde() {
        return grosorBorde;
    }

    // ========== MÉTODOS PRIVADOS ==========

    private Color oscurecer(Color c, float factor) {
        if (c == null) return null;
        int r = (int) (c.getRed() * factor);
        int g = (int) (c.getGreen() * factor);
        int b = (int) (c.getBlue() * factor);
        return new Color(Math.max(r, 0), Math.max(g, 0), Math.max(b, 0));
    }

    private boolean usarGradiente() {
        return colorIzquierdo != null && colorDerecho != null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        boolean tieneSombra = (tipoEfecto == CON_SOMBRA);
        int offsetSombra = tieneSombra ? shadowSize : 0;
        int btnWidth = getWidth() - offsetSombra;
        int btnHeight = getHeight() - offsetSombra;

        // Dibujar sombra si está activada
        if (tieneSombra) {
            ShadowBorder.paintShadow(g2, 0, 0, btnWidth, btnHeight, redondeo, shadowSize);
        }

        // Crear el shape redondeado
        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(0, 0, btnWidth, btnHeight, redondeo, redondeo);
        
        // Pintar el fondo (degradado o sólido)
        if (usarGradiente()) {
            Color izq = hover ? oscurecer(colorIzquierdo, 0.85f) : colorIzquierdo;
            Color der = hover ? oscurecer(colorDerecho, 0.85f) : colorDerecho;
            GradientPaint gradient = new GradientPaint(0, 0, izq, btnWidth, 0, der);
            g2.setPaint(gradient);
        } else {
            Color colorBase = getBackground();
            Color colorFinal = hover ? oscurecer(colorBase, 0.85f) : colorBase;
            g2.setColor(colorFinal);
        }
        g2.fill(roundedRect);
        
        // Dibujar borde si está activado
        if (tipoEfecto == CON_BORDE) {
            g2.setColor(colorBorde);
            g2.setStroke(new BasicStroke(grosorBorde));
            float offset = grosorBorde / 2.0f;
            RoundRectangle2D bordeRect = new RoundRectangle2D.Double(
                offset, offset, 
                btnWidth - grosorBorde, btnHeight - grosorBorde, 
                redondeo, redondeo
            );
            g2.draw(bordeRect);
        }

        // Dibujar icono y texto centrados
        dibujarContenido(g2, btnWidth, btnHeight);

        g2.dispose();
    }

    private void dibujarContenido(Graphics2D g2, int btnWidth, int btnHeight) {
        Icon icon = getIcon();
        String text = getText();
        FontMetrics fm = g2.getFontMetrics(getFont());
        
        int iconWidth = (icon != null) ? icon.getIconWidth() : 0;
        int iconHeight = (icon != null) ? icon.getIconHeight() : 0;
        int textWidth = (text != null && !text.isEmpty()) ? fm.stringWidth(text) : 0;
        int gap = (icon != null && text != null && !text.isEmpty()) ? getIconTextGap() : 0;
        
        int totalWidth = iconWidth + gap + textWidth;
        int startX = (btnWidth - totalWidth) / 2;
        
        // Dibujar icono
        if (icon != null) {
            int iconY = (btnHeight - iconHeight) / 2;
            icon.paintIcon(this, g2, startX, iconY);
            startX += iconWidth + gap;
        }
        
        // Dibujar texto
        if (text != null && !text.isEmpty()) {
            g2.setColor(getForeground());
            g2.setFont(getFont());
            int textY = (btnHeight + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(text, startX, textY);
        }
    }
}
