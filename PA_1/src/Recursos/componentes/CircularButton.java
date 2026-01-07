package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/**
 * Panel circular con imagen centrada y sombra opcional.
 * 
 * USO EN NETBEANS:
 * Custom creation: new Custom.CircularButton()
 * Custom creation con sombra: new Custom.CircularButton(true)
 * Custom creation con imagen: new Custom.CircularButton("/res/imagenes/icono.png")
 * Custom creation con imagen y sombra: new Custom.CircularButton("/res/imagenes/icono.png", true)
 * 
 * Luego usar setBackground() para el color de fondo.
 * La imagen se muestra centrada SIN escalar (tamaño original).
 * 
 * IMPORTANTE: Para que sea un círculo perfecto, el componente debe tener
 * ancho y alto iguales (cuadrado). Si no son iguales, se usará el menor.
 */
public class CircularButton extends JPanel {

    private boolean mostrarSombra;
    private int shadowSize = 10;
    private boolean hover = false;
    private Image imagen;

    /**
     * Constructor sin parámetros (ideal para NetBeans custom creation)
     */
    public CircularButton() {
        this(false);
    }
    
    /**
     * Constructor solo con sombra
     */
    public CircularButton(boolean sombra) {
        super();
        this.mostrarSombra = sombra;
        this.imagen = null;
        init();
    }

    /**
     * Constructor con ruta de imagen
     */
    public CircularButton(String rutaImagen) {
        this(rutaImagen, false);
    }

    /**
     * Constructor con imagen y sombra
     */
    public CircularButton(String rutaImagen, boolean sombra) {
        super();
        this.mostrarSombra = sombra;
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            try {
                this.imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
            } catch (Exception e) {
                this.imagen = null;
            }
        }
        init();
    }
    
    /**
     * Inicialización común para todos los constructores
     */
    private void init() {
        setOpaque(false);
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

    /**
     * Asigna una imagen para dibujar centrada en el botón.
     * La imagen NO se escala, mantiene su tamaño original.
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
        repaint();
    }

    /**
     * Conveniencia: asigna una imagen desde un ImageIcon.
     */
    public void setImagenIcon(ImageIcon icono) {
        this.imagen = (icono != null) ? icono.getImage() : null;
        repaint();
    }

    /**
     * Devuelve la imagen asignada (puede ser null).
     */
    public Image getImagen() {
        return imagen;
    }

    private Color oscurecer(Color c, float factor) {
        int r = (int) (c.getRed() * factor);
        int g = (int) (c.getGreen() * factor);
        int b = (int) (c.getBlue() * factor);
        return new Color(Math.max(r, 0), Math.max(g, 0), Math.max(b, 0));
    }

    /**
     * Pinta sombra circular difusa.
     */
    private void paintCircularShadow(Graphics2D g2, int x, int y, int diameter) {
        int shadowOffsetX = 0;
        int shadowOffsetY = 4;
        
        for (int i = shadowSize; i >= 0; i--) {
            float ratio = (float) i / shadowSize;
            int alpha = (int) (35 * Math.pow(1 - ratio, 2));
            g2.setColor(new Color(0, 0, 0, alpha));
            
            int blur = i * 2;
            double size = diameter + blur - (shadowSize - i);
            double posX = x + shadowOffsetX + (shadowSize - i) / 2.0;
            double posY = y + shadowOffsetY + (shadowSize - i) / 2.0;
            g2.fill(new Ellipse2D.Double(posX, posY, size, size));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int offsetSombra = mostrarSombra ? shadowSize : 0;
        int availableWidth = getWidth() - offsetSombra;
        int availableHeight = getHeight() - offsetSombra;
        
        // Usar el menor para hacer un círculo perfecto
        int diameter = Math.min(availableWidth, availableHeight);
        
        // Centrar el círculo en el área disponible
        int circleX = (availableWidth - diameter) / 2;
        int circleY = (availableHeight - diameter) / 2;

        // Dibujar sombra circular
        if (mostrarSombra) {
            paintCircularShadow(g2, circleX, circleY, diameter);
        }

        // Dibujar fondo circular
        Color colorBase = getBackground();
        Color colorFinal = hover ? oscurecer(colorBase, 0.8f) : colorBase;
        g2.setColor(colorFinal);
        g2.fill(new Ellipse2D.Double(circleX, circleY, diameter, diameter));

        // Dibujar imagen centrada (sin escalar), si existe
        if (imagen != null) {
            int imgW = imagen.getWidth(this);
            int imgH = imagen.getHeight(this);
            if (imgW > 0 && imgH > 0) {
                int imgX = circleX + (diameter - imgW) / 2;
                int imgY = circleY + (diameter - imgH) / 2;
                g2.drawImage(imagen, imgX, imgY, this);
            }
        }

        g2.dispose();
    }
}
