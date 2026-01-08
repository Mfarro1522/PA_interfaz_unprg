
package CapaLogica.modelos;

import java.time.LocalDate;

/**
 *
 * @author jackh
 */
public class Prestamo {
    private String id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaSalida;
    private LocalDate fechaDevolucion;
    private boolean activo;

    public Prestamo(String id, Libro libro, Usuario usuario, LocalDate fechaSalida, LocalDate fechaDevolucion, boolean activo) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaSalida = fechaSalida;
        this.fechaDevolucion = null;
        this.activo = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public void finalizarPrestamo() {
        this.fechaDevolucion = LocalDate.now();
        this.activo = false;
        this.libro.setDisponible(true);
    }
}
