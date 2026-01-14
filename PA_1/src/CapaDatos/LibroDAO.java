package CapaDatos;

import CapaLogica.modelos.Libro;

/**
 *
 * @author jackh
 */
public class LibroDAO {

    private static final int MAX = 30;
    private static Libro[] libros = new Libro[MAX];
    private static int cantidad;

    public LibroDAO() {
        cantidad = 0;
    }

    public static void agregar(Libro objLibro) {
        if (cantidad < MAX) {
            libros[cantidad] = objLibro;
            cantidad++;
        }
    }

    static {
        inicializarDatos();
    }

    private static void inicializarDatos() {
        agregar(new Libro("L001", "Cien años de soledad", "Gabriel García Márquez", "Sudamericana", 1967, "Literatura", true, 5));
        agregar(new Libro("L002", "Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles", 1605, "Clásico", true, 2));
        agregar(new Libro("L003", "Clean Code", "Robert C. Martin", "Prentice Hall", 2008, "Programación", true, 1));
        agregar(new Libro("L004", "El código Da Vinci", "Dan Brown", "Doubleday", 2003, "Suspenso", true, 6));
        agregar(new Libro("L005", "1984", "George Orwell", "Secker & Warburg", 1949, "Distopía", true, 3));
        agregar(new Libro("L006", "El resplandor", "Stephen King", "Doubleday", 1977, "Terror", true, 2));
        agregar(new Libro("L007", "Harry Potter y la piedra filosofal", "J.K. Rowling", "Bloomsbury", 1997, "Fantasía", true, 8));
        agregar(new Libro("L008", "El Hobbit", "J.R.R. Tolkien", "George Allen & Unwin", 1937, "Fantasía", true, 1));
        agregar(new Libro("L009", "El nombre de la rosa", "Umberto Eco", "Bompiani", 1980, "Misterio", true, 2));
        agregar(new Libro("L010", "Orgullo y prejuicio", "Jane Austen", "T. Egerton", 1813, "Romance", true, 3));
        agregar(new Libro("L011", "Crónica de una muerte anunciada", "Gabriel García Márquez", "La Oveja Negra", 1981, "Literatura", true, 4));
        agregar(new Libro("L012", "Fahrenheit 451", "Ray Bradbury", "Ballantine Books", 1953, "Ciencia Ficción", true, 3));
        agregar(new Libro("L013", "El Alquimista", "Paulo Coelho", "Rocco", 1988, "Autoayuda", true, 5));
        agregar(new Libro("L014", "La ciudad y los perros", "Mario Vargas Llosa", "Seix Barral", 1963, "Literatura", true, 5));
        agregar(new Libro("L015", "Breve historia del tiempo", "Stephen Hawking", "Bantam Books", 1988, "Ciencia", true, 3));
        agregar(new Libro("L016", "Java: The Complete Reference", "Herbert Schildt", "McGraw-Hill", 2018, "Programación", true, 4));
        agregar(new Libro("L017", "El principito", "Antoine de Saint-Exupéry", "Reynal & Hitchcock", 1943, "Infantil", true, 3));
        agregar(new Libro("L018", "Rayuela", "Julio Cortázar", "Editorial Sudamericana", 1963, "Clásico", true,5));
        agregar(new Libro("L019", "Los miserables", "Victor Hugo", "A. Lacroix", 1862, "Histórico", true, 2));
        agregar(new Libro("L020", "Sapiens: De animales a dioses", "Yuval Noah Harari", "Harvill Secker", 2011, "Historia", true, 1));
        agregar(new Libro("L021", "La sombra del viento", "Carlos Ruiz Zafón", "Planeta", 2001, "Misterio", true, 6));
        agregar(new Libro("L022", "El retrato de Dorian Gray", "Oscar Wilde", "Lippincott's", 1890, "Filosofía", true, 2));
        agregar(new Libro("L023", "Design Patterns", "Erich Gamma", "Addison-Wesley", 1994, "Programación", true, 2));
        agregar(new Libro("L024", "Cosmos", "Carl Sagan", "Random House", 1980, "Ciencia", true, 4));
        agregar(new Libro("L025", "La tía Julia y el escribidor", "Mario Vargas Llosa", "Seix Barral", 1977, "Literatura", true, 2));
    }

    public static Libro[] obtener() {
        return libros;
    }
    
    public static int getCantidad() {
        return cantidad;
    }
    
    public static int posicion(String id) {
        int pos = -1;
        for (int i = 0; i < cantidad; i++) {
            if (libros[i].getId().equalsIgnoreCase(id)) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public static Libro getElemento(String id) {
        int pos = posicion(id);
        return (pos != -1) ? libros[pos] : null;
    }
    
    public static void darBaja(String idl) {
        int id = Integer.parseInt(idl);
        libros[id].setDisponible(false);
    }
    
    public static void eliminar(int pos) {
        for (int i = pos; i < cantidad - 1; i++) {
            libros[i] = libros[i + 1];
        }
        libros[cantidad - 1] = null;
        cantidad--;
    }
    
    public static void modificar(int pos, Libro objLibro) {
        libros[pos] = objLibro;
    }
    
    public static Libro[] libroPorCategoria(String cat) {
        int cantreg = 0;
        
        for (int i = 0; i < cantidad; i++) {
            if (libros[i].getCategoria().equalsIgnoreCase(cat)) cantreg++;
        }
        
        Libro[] datos = new Libro[cantreg];
        int c = 0;
        
        for (int i = 0; i < cantidad; i++) {
            if (libros[i].getCategoria().equalsIgnoreCase(cat)) {
                datos[c] = libros[i];
                c++;
            }
        }
        return datos;
    }
}
