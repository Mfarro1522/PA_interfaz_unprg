package Recursos.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Clase unificada para aplicar estilos Material Design a JTable
 * Incluye estilos para header y body con bordes redondeados
 * Fuente : https://gitlab.com/martinezcesarmonkey15/tablamateriajswing/-/blob/main/src/main/java/com/mycompany/tablasmaterial/metodos.java?ref_type=heads
 * en el Scroll panel 
 * usar: jScrollPane1 = new Recursos.componentes.RoundedScrollPane(tablaLibros(nombredetucalse), 20)
 * en postcreation :Recursos.componentes.EstiloTablas.aplicarEstilo(tablaLibros);
 */
public class EstiloTablas {

    // Colores personalizables - Tema morado/violeta que combina con la interfaz
    private static Color colorHeaderFondo = new Color(84, 73, 229);      // Morado principal
    private static Color colorHeaderTexto = Color.WHITE;
    private static Color colorHeaderBorde = new Color(131, 89, 244);     // Morado claro
    private static Color colorSeleccion = new Color(131, 89, 244);       // Morado para selección
    private static Color colorFilaPar = new Color(240, 240, 240);
    private static Color colorFilaImpar = new Color(255, 255, 255);
    private static Color colorTexto = new Color(33, 33, 33);

    /**
     * Aplica el estilo completo a un JTable
     * @param tabla El JTable a estilizar
     */
    public static void aplicarEstilo(JTable tabla) {
        aplicarEstilo(tabla, 30);
    }

    /**
     * Aplica el estilo completo a un JTable con altura de fila personalizada
     * @param tabla El JTable a estilizar
     * @param alturaFila Altura de las filas
     */
    public static void aplicarEstilo(JTable tabla, int alturaFila) {
        JTableHeader header = tabla.getTableHeader();
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new HeaderRenderer(20)); // Radio para esquinas
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 48));
        header.setOpaque(false);
        
        tabla.setDefaultRenderer(Object.class, new BodyRenderer());
        tabla.setRowHeight(alturaFila);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setOpaque(false);
    }

    /**
     * Centra el texto de todas las columnas de la tabla.
     * Llamar DESPUÉS de setModel() para que funcione correctamente.
     * @param tabla El JTable a centrar
     */
    public static void centrarColumnas(JTable tabla) {
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(new BodyRenderer() {
                {
                    setHorizontalAlignment(JLabel.CENTER);
                }
            });
        }
    }

    /**
     * Renderer para el encabezado de la tabla con esquinas redondeadas
     */
    public static class HeaderRenderer extends DefaultTableCellRenderer {
        
        private int radius;
        private int column;
        private int totalColumns;

        public HeaderRenderer(int radius) {
            this.radius = radius;
            setHorizontalAlignment(JLabel.LEFT);
            setOpaque(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            this.column = column;
            this.totalColumns = table.getColumnCount();
            
            setFont(new Font("Roboto", Font.BOLD, 16));
            setForeground(colorHeaderTexto);
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            return this;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int width = getWidth();
            int height = getHeight();
            
            // Primera columna: esquina superior izquierda redondeada
            if (column == 0) {
                Area area = new Area(new RoundRectangle2D.Double(0, 0, width + radius, height, radius, radius));
                area.add(new Area(new Rectangle2D.Double(radius, height - radius, width - radius, radius)));
                g2.setColor(colorHeaderFondo);
                g2.fill(area);
            }
            // Última columna: esquina superior derecha redondeada
            else if (column == totalColumns - 1) {
                Area area = new Area(new RoundRectangle2D.Double(-radius, 0, width + radius, height, radius, radius));
                area.add(new Area(new Rectangle2D.Double(0, height - radius, width - radius, radius)));
                g2.setColor(colorHeaderFondo);
                g2.fill(area);
            }
            // Columnas intermedias: sin redondeo
            else {
                g2.setColor(colorHeaderFondo);
                g2.fillRect(0, 0, width, height);
            }
            
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            d.height = 48;
            return d;
        }
    }

    /**
     * Renderer para el cuerpo de la tabla
     */
    public static class BodyRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            c.setFont(new Font("Roboto", Font.PLAIN, 14));

            if (isSelected) {
                c.setBackground(colorSeleccion);
                c.setForeground(Color.WHITE);
            } else {
                if (row % 2 == 0) {
                    c.setBackground(colorFilaPar);
                } else {
                    c.setBackground(colorFilaImpar);
                }
                c.setForeground(colorTexto);
            }
            
            ((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 2, Color.WHITE));
            return c;
        }
    }

    // Métodos para personalizar colores
    public static void setColorHeaderFondo(Color color) {
        colorHeaderFondo = color;
    }

    public static void setColorHeaderTexto(Color color) {
        colorHeaderTexto = color;
    }

    public static void setColorSeleccion(Color color) {
        colorSeleccion = color;
    }

    public static void setColorFilaPar(Color color) {
        colorFilaPar = color;
    }

    public static void setColorFilaImpar(Color color) {
        colorFilaImpar = color;
    }
}
