
package CapaLogica.modelos;

/**
 *
 * @author jackh
 */
public abstract class Usuario {
    private String id;
    private String nombres;
    private String dni;
    private String rol;

    public Usuario(String id, String nombres, String dni, String rol) {
        this.id = id;
        this.nombres = nombres;
        this.dni = dni;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
