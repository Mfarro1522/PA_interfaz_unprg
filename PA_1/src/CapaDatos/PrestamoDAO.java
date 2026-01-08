package CapaDatos;

import CapaLogica.modelos.Prestamo;
import java.time.LocalDate;

/**
 *
 * @author jackh
 */
public class PrestamoDAO {

    public static UsuarioDAO objUsuario = new UsuarioDAO();
    private static final int MAX = 10;
    private static Prestamo[] prestamos = new Prestamo[MAX];
    private static int cantidad;

    public PrestamoDAO() {
        cantidad = 0;
    }

    public static void agregar(Prestamo objPrestamo) {
        if (cantidad < MAX) {
            prestamos[cantidad] = objPrestamo;
            cantidad++;
        }
    }

    static {
        inicializarDatos();
    }

    private static void inicializarDatos() {
        agregar(new Prestamo(
            "P001",
            LibroDAO.getElemento("0"),
            objUsuario.buscar("2"),
            LocalDate.now().minusDays(3), // Hace 3 días
            null,
            true
        ));

        agregar(new Prestamo(
            "P002",
            LibroDAO.getElemento("14"),
            objUsuario.buscar("4"),
            LocalDate.now().minusDays(1), // Ayer
            null,
            true
        ));

        agregar(new Prestamo(
            "P003",
            LibroDAO.getElemento("15"),
            objUsuario.buscar("7"),
            LocalDate.now(), // Hoy
            null,
            true
        ));

        agregar(new Prestamo(
            "P004",
            LibroDAO.getElemento("7"),
            objUsuario.buscar("1"),
            LocalDate.now().minusDays(5), // Hace 5 días
            null,
            true
        ));

        agregar(new Prestamo(
            "P005",
            LibroDAO.getElemento("23"),
            objUsuario.buscar("5"),
            LocalDate.now().minusDays(2), // Hace 2 días
            null,
            true
        ));
    }

    public static Prestamo[] obtener() {
        return prestamos;
    }
    
    public static int getCantidad() {
        return cantidad;
    }
    
    public static int posicion(String id) {
        int pos = -1;
        for (int i = 0; i < cantidad; i++) {
            if (prestamos[i].getId().equals(id)) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public static Prestamo getElemento(String id) {
        int pos = posicion(id);
        return (pos != -1) ? prestamos[pos] : null;
    }
    
    public static void darBaja(String idl) {
        int id = Integer.parseInt(idl);
        prestamos[id].setEstado(false);
    }
    
    public static void eliminar(int pos) {
        for (int i = 0; i < cantidad; i++) {
            prestamos[pos] = prestamos[pos + i];
        }
        cantidad--;
    }
    
    public static void modificar(int pos, Prestamo objPrestamo) {
        prestamos[pos] = objPrestamo;
    }
    
    public static Prestamo[] prestamosPorEstado(boolean estado) {
        int cantreg = 0;
        for (int i = 0; i < cantidad; i++) {
            if (prestamos[i].isEstado() == estado) cantreg++;
        }
        
        Prestamo[] datos = new Prestamo[cantreg];
        int c=0;
        
        for (int i = 0; i < cantidad; i++) {
            if (prestamos[i].isEstado() == estado) {
                datos[c] = prestamos[i];
                c++;
            }
        }
        return datos;
    }
}
