package CapaLogica.modelos;

/**
 *
 * @author jackh
 */
public class Administrativo extends Usuario {

    private String area;
    private String usuario;
    private String clave;

    public Administrativo() {
        super("", "", "", "");
    }

    public Administrativo(String id, String nombres, String dni, String area) {
        super(id, nombres, dni, "Administrativo");
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean iniciaSesion() {
        return (usuario.equals("ADMIN") && clave.equals("12345"));
    }
}
