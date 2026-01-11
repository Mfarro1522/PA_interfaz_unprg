package CapaPresentacion;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mauricio
 */
public class Main {

    public static void main(String[] args)  throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        
        //en linux para que el preview coincida con la ejecucion ; en el foco darle a aceptar todos los throws
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
         
        frMenu menu = new frMenu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
