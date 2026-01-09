package Recursos.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

/**
 * ComboBox con bordes redondeados y estilo minimalista.
 * 
 * USO EN NETBEANS (Custom Creation Code):
 * new Recursos.componentes.RoundedComboBox()
 * 
 * Opcionalmente se puede especificar el radio del borde:
 * new Recursos.componentes.RoundedComboBox(20)
 */
public class RoundedComboBox<E> extends JComboBox<E> {

    private int radius = 20;
    private Color borderColor = new Color(131, 89, 244); // Morado claro por defecto
    private Color arrowColor = new Color(84, 73, 229);   // Morado oscuro por defecto
    private Color backgroundColor = Color.WHITE;

    public RoundedComboBox() {
        this(20);
    }

    public RoundedComboBox(int radius) {
        super();
        this.radius = radius;
        init();
    }

    private void init() {
        setOpaque(false);
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBackground(Color.WHITE);
        setForeground(new Color(33, 33, 33));
        setBorder(new EmptyBorder(5, 10, 5, 10)); // Padding interno para el texto
        setUI(new RoundedComboBoxUI());
        
        // Renderer personalizado para los items y la selección
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JComponent comp = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                comp.setBorder(new EmptyBorder(5, 10, 5, 10));
                
                if (isSelected) {
                    comp.setBackground(borderColor);
                    comp.setForeground(Color.WHITE);
                } else {
                    comp.setBackground(Color.WHITE);
                    comp.setForeground(getForeground());
                }
                
                // Importante: para que no dibuje el fondo "gris" default en la vista principal
                if (index == -1) { 
                    comp.setOpaque(false); 
                    comp.setBackground(new Color(0,0,0,0)); // Transparente
                } else {
                    comp.setOpaque(true);
                }
                
                return comp;
            }
        });
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    // Métodos setters para personalización
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }
    
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
    
    public void setArrowColor(Color color) {
        this.arrowColor = color;
        repaint();
    }

    // UI interna
    private class RoundedComboBoxUI extends BasicComboBoxUI {

        @Override
        protected JButton createArrowButton() {
            JButton btn = new JButton();
            btn.setContentAreaFilled(false);
            btn.setBorder(new EmptyBorder(0, 0, 0, 0));
            btn.setFocusable(false);
            return btn;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = c.getWidth();
            int height = c.getHeight();

            // 1. Dibujar fondo redondeado
            g2.setColor(backgroundColor);
            g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, radius, radius));

            // 2. Dibujar borde redondeado
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1));
            g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, radius, radius));

            // 3. Dibujar flecha
            int iconSize = 12;
            int x = width - 20;
            int y = (height - iconSize) / 2 + 3;
            
            int[] xPoints = {x, x + 5, x + 10};
            int[] yPoints = {y, y + 5, y};
            
            g2.setColor(arrowColor);
            g2.fillPolygon(xPoints, yPoints, 3);
            
            g2.dispose();
            
            // 4. Pintar el item seleccionado
            // Usamos un area de recorte para que no sobresalga del borde redondeado si fuera opaco
            // Pero como el renderer principal lo pusimos transparente (index -1), no debería haber problema.
            // Ajustamos el rectángulo para que no pise el borde ni la flecha
            Rectangle r = rectangleForCurrentValue();
            paintCurrentValue(g, r, false); // false = no tiene foco visual borroso standard
        }
        
        @Override
        public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
             // Delegamos al renderer configurado en el componente
             // Ajuste de bounds para respetar padding
             bounds.x += 5;
             bounds.width -= 25; // espacio para la flecha
             super.paintCurrentValue(g, bounds, false);
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    JScrollPane scroller = new JScrollPane(list);
                    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    
                    // Scrollbar minimalista
                    scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                         @Override
                        protected void configureScrollBarColors() {
                            this.thumbColor = borderColor;
                            this.trackColor = new Color(245, 245, 245);
                        }
                        
                        @Override
                        protected JButton createDecreaseButton(int orientation) {
                            return createZeroButton();
                        }

                        @Override
                        protected JButton createIncreaseButton(int orientation) {
                            return createZeroButton();
                        }
                        
                        private JButton createZeroButton() {
                            JButton btn = new JButton();
                            btn.setPreferredSize(new Dimension(0, 0));
                            return btn;
                        }
                    });
                    return scroller;
                }
            };
            popup.setBorder(BorderFactory.createLineBorder(borderColor, 1));
            return popup;
        }
    }
}
