
package CapaLogica.modelos;

/**
 *
 * @author jackh
 */
public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private String editorial;
    private int apublicacion;
    private String categoria;
    private boolean disponible;

    public Libro() {
        this.id = "";
        this.titulo = "";
        this.autor = "";
        this.editorial = "";
        this.apublicacion = 0;
        this.categoria = "";
        this.disponible = false;
    }
    
    public Libro(String codigo, String titulo, String autor, String editorial, int apublicacion, String categoria, boolean disponible) {
        this.id = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.apublicacion = apublicacion;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getApublicacion() {
        return apublicacion;
    }

    public void setApublicacion(int apublicacion) {
        this.apublicacion = apublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
