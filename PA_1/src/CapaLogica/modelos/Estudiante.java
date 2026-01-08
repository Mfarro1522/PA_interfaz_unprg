
package CapaLogica.modelos;

/**
 *
 * @author jackh
 */
public class Estudiante extends Usuario{
    private String carrera;
    
    public Estudiante(String id, String nombres, String dni, String carrera) {
        super(id, nombres, dni, "Estudiante");
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
