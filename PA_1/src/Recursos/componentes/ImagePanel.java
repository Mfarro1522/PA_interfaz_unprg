package Recursos.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Panel con imagen de fondo y sombra opcional
 * 
 * USO EN NETBEANS:
 *   new Recursos.componentes.ImagePanel("/Recursos/Assets/fondo.png")
 *   new Recursos.componentes.ImagePanel("/Recursos/Assets/fondo.png", 30)
 *   new Recursos.componentes.ImagePanel("/Recursos/Assets/fondo.png", 30, true)
 */
public class ImagePanel extends JPanel {

    private Image imagen;
    private int redondeo;
    private boolean mostrarSombra;
    private int shadowSize = 15;
    private Color shadowColor = new Color(0, 0, 0, 60);


    public ImagePanel(String ruta) {
        this(ruta, 0, false);
    }

    public ImagePanel(String ruta, int cantRedondeo) {
        this(ruta, cantRedondeo, false);
    }

    public ImagePanel(String ruta, int cantRedondeo, boolean sombra) {
        this.redondeo = cantRedondeo;
        this.mostrarSombra = sombra;
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
        setOpaque(false);
    }
    
    public void setSombra(boolean mostrar) {
        this.mostrarSombra = mostrar;
        repaint();
    }
    
    public void setShadowSize(int size) {
        this.shadowSize = size;
        repaint();
    }
    
    public void setShadowColor(Color color) {
        this.shadowColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        int offsetSombra = mostrarSombra ? shadowSize : 0;
        int panelWidth = getWidth() - offsetSombra;
        int panelHeight = getHeight() - offsetSombra;
        
        // Dibujar sombra usando ShadowBorder
        if (mostrarSombra) {
            ShadowBorder.paintShadow(g2, 0, 0, panelWidth, panelHeight, redondeo, shadowSize);
        }
        
        // Dibujar imagen con recorte redondeado
        if (redondeo > 0) {
            g2.setClip(new RoundRectangle2D.Double(0, 0, panelWidth, panelHeight, redondeo, redondeo));
        }
        g2.drawImage(imagen, 0, 0, panelWidth, panelHeight, this);
        g2.dispose();
    }
}
