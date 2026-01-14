package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Campo de contraseña con esquinas redondeadas y soporte para icono.
 * Muestra "•••••••••••" por defecto que desaparece al hacer clic.
 * 
 * USO EN NETBEANS:
 *   - Solo redondeo: new Recursos.componentes.RoundedPasswordField(15)
 *   - Icono + redondeo: new Recursos.componentes.RoundedPasswordField("/Recursos/Assets/icono.png", 15)
 */
public class RoundedPasswordField extends JPasswordField {

    private int redondeo;
    private Color borderColor = new Color(200, 200, 200);
    private int borderWidth = 1;
    private Color placeholderColor = new Color(150, 150, 150);
    private String placeholder = "•••••••••••";
    private boolean showBorder = true;
    private Image icono;
    private int iconoSize = 16;
    private int iconoPadding = 8;

    /**
     * Constructor solo con redondeo.
     * @param cantRedondeo Cantidad de redondeo en las esquinas
     */
    public RoundedPasswordField(int cantRedondeo) {
        super();
        this.redondeo = cantRedondeo;
        this.icono = null;
        setOpaque(false);
        actualizarBorde();
        setBackground(Color.WHITE);
    }

    /**
     * Constructor con icono y redondeo.
     * @param rutaIcono Ruta del archivo de imagen del icono
     * @param cantRedondeo Cantidad de redondeo en las esquinas
     */
    public RoundedPasswordField(String rutaIcono, int cantRedondeo) {
        super();
        this.redondeo = cantRedondeo;
        cargarIcono(rutaIcono);
        setOpaque(false);
        actualizarBorde();
        setBackground(Color.WHITE);
    }

    private void cargarIcono(String rutaIcono) {
        try {
            Image img = null;
            // Intentar cargar desde classpath primero
            java.net.URL url = getClass().getResource(rutaIcono);
            if (url != null) {
                img = ImageIO.read(url);
            } else {
                // Si no está en classpath, intentar como archivo
                File file = new File(rutaIcono);
                if (file.exists()) {
                    img = ImageIO.read(file);
                }
            }
            
            if (img != null) {
                this.icono = img.getScaledInstance(iconoSize, iconoSize, Image.SCALE_SMOOTH);
            } else {
                System.err.println("No se encontró el icono: " + rutaIcono);
                this.icono = null;
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar el icono: " + rutaIcono + " - " + e.getMessage());
            this.icono = null;
        }
    }

    private void actualizarBorde() {
        int leftPadding = (icono != null) ? iconoSize + iconoPadding * 2 : 12;
        setBorder(BorderFactory.createEmptyBorder(5, leftPadding, 5, 12));
    }

    public void setIcono(String rutaIcono) {
        cargarIcono(rutaIcono);
        actualizarBorde();
        repaint();
    }

    public void setIconoSize(int size) {
        this.iconoSize = size;
        if (icono != null) {
            actualizarBorde();
        }
        repaint();
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

        // Dibujar icono centrado verticalmente en el lado izquierdo
        if (icono != null) {
            int iconY = (getHeight() - iconoSize) / 2;
            g2.drawImage(icono, iconoPadding, iconY, null);
        }
        
        g2.dispose();
        
        super.paintComponent(g);
        
        // Placeholder - se muestra solo cuando está vacío y NO tiene foco
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
