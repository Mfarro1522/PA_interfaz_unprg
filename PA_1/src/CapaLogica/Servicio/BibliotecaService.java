package CapaLogica.Servicio;

import CapaDatos.LibroDAO;
import CapaDatos.PrestamoDAO;
import CapaDatos.UsuarioDAO;
import CapaLogica.modelos.Libro;
import CapaLogica.modelos.Prestamo;
import CapaLogica.modelos.Usuario;
import java.time.LocalDate;

/**
 *
 * @author jackh
 */
public class BibliotecaService {

    public static boolean registrarPrestamo(String idUsuario, String idLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        Libro objLibro = LibroDAO.buscarPorId(idLibro);
        if (objLibro == null || objLibro.getCantidad() <= 0) {
            return false;
        }

        Usuario usuario = UsuarioDAO.buscar(idUsuario);
        if (usuario == null) {
            return false;
        }

        String idPrestamo = generarIdPrestamo();

        Prestamo prestamo = new Prestamo(
            idPrestamo,
            objLibro,
            usuario,
            fechaPrestamo,
            fechaDevolucion,
            true
        );

        boolean registrado = PrestamoDAO.agregar(prestamo);

        if (registrado) {
            objLibro.setCantidad(objLibro.getCantidad() - 1);
            int pos = LibroDAO.posicion(idLibro);
            LibroDAO.modificar(pos, objLibro);
        }
        return registrado;
    }

    public static boolean devolverPrestamo(String idPrestamo) {
        Prestamo prestamo = PrestamoDAO.getElemento(idPrestamo);
        if (prestamo == null || !prestamo.isEstado()) {
            return false;
        }

        Libro libro = prestamo.getLibro();
        if (libro == null) {
            return false;
        }

        prestamo.setEstado(false);
        int posPrestamo = PrestamoDAO.posicion(idPrestamo);
        boolean actualizado = PrestamoDAO.modificar(posPrestamo, prestamo);

        if (actualizado) {
            libro.setCantidad(libro.getCantidad() + 1);
            int posLibro = LibroDAO.posicion(libro.getId());
            LibroDAO.modificar(posLibro, libro);
        }
        return actualizado;
    }

    public static String generarIdPrestamo() {
        int ultimoId = 0;
        int cantidad = PrestamoDAO.getCantidad();
        
        if (cantidad > 0) {
            Prestamo[] prestamos = PrestamoDAO.obtener();
            for (int i = 0; i < cantidad; i++) {
                String id = prestamos[i].getId();
                int num = Integer.parseInt(id.substring(1));
                if(num > ultimoId) {
                    ultimoId = num;
                }
            }
        }
        ultimoId++;
        return String.format("P%03d", ultimoId);
    }

}
