package CapaDatos;

import CapaLogica.modelos.Prestamo;
import java.time.LocalDate;

/**
 *
 * @author jackh
 */
public class PrestamoDAO {

    private static final int MAX = 10;
    private static Prestamo[] prestamos = new Prestamo[MAX];
    private static int cantidad = 0;

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
            LibroDAO.getElemento("L001"),
            UsuarioDAO.buscar("2"),
            LocalDate.now().minusDays(3), // Hace 3 días
            LocalDate.now().plusDays(2),
            true
        ));

        agregar(new Prestamo(
            "P002",
            LibroDAO.getElemento("L014"),
            UsuarioDAO.buscar("4"),
            LocalDate.now().minusDays(1), // Ayer
            null,
            true
        ));

        agregar(new Prestamo(
            "P003",
            LibroDAO.getElemento("L015"),
            UsuarioDAO.buscar("7"),
            LocalDate.now(), // Hoy
            null,
            true
        ));

        agregar(new Prestamo(
            "P004",
            LibroDAO.getElemento("L007"),
            UsuarioDAO.buscar("1"),
            LocalDate.now().minusDays(5), // Hace 5 días
            null,
            false
        ));

        agregar(new Prestamo(
            "P005",
            LibroDAO.getElemento("L023"),
            UsuarioDAO.buscar("5"),
            LocalDate.now().minusDays(2), // Hace 2 días
            LocalDate.now().plusDays(5),
            false
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
        for (int i = 0; i < cantidad - 1; i++) {
            prestamos[i] = prestamos[i + 1];
        }
        prestamos[cantidad - 1] = null;
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
