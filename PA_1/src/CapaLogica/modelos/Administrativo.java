
package CapaLogica.modelos;

/**
 *
 * @author jackh
 */
public class Administrativo extends Usuario{
    private String area;

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
}
