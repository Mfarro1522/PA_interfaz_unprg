
package CapaLogica.modelos;

/**
 *
 * @author jackh
 */
public class Docente extends Usuario{
    private String facultad;

    public Docente(String id, String nombres, String dni, String facultad) {
        super(id, nombres, dni, "Docente");
        this.facultad = facultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
