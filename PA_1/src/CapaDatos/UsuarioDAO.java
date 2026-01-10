package CapaDatos;

import CapaLogica.modelos.Administrativo;
import CapaLogica.modelos.Docente;
import CapaLogica.modelos.Estudiante;
import CapaLogica.modelos.Usuario;

/**
 *
 * @author jackh
 */
public class UsuarioDAO implements IUsuarioDAO {

    private static final int MAX = 30;
    private static Usuario[] listaUsuarios = new Usuario[MAX];
    private static int contador = 0;

    @Override
    public boolean agregar(Usuario usuario) {
        if (existeId(usuario.getId())) {
            return false;
        }

        if (existeDni(usuario.getDni())) {
            return false;
        }
        listaUsuarios[contador] = usuario;
        contador++;

        return true;
    }

     {
        inicializarDatos();
    }

    private void inicializarDatos() {
        agregar(new Estudiante("1", "Ricardo Perez", "71234567", "EPICI"));
        agregar(new Estudiante("2", "Gianluca Saenz", "72345678", "FACHSE"));
        agregar(new Estudiante("3", "Christian Tunoque", "73456789", "FICSA"));
        agregar(new Estudiante("4", "David Santamaria", "74567890", "FIQUIA"));

        agregar(new Docente("5", "Dra. Giuliana Lecca", "01234567", "FACFYM"));
        agregar(new Docente("6", "Dr. Roger Alarcon", "02345678", "FACFYM"));
        agregar(new Docente("7", "Dr. Carlos Valdivia", "03456789", "FACFYM"));
        agregar(new Docente("11", "Est. Fernando Carranza", "03456789", "ESTADISTICA"));

        agregar(new Administrativo("8", "Luis Barrios", "45678901", "TI"));
        agregar(new Administrativo("9", "Yoshimar Ok", "46789012", "TI"));
        agregar(new Administrativo("10", "Pedro Reyes", "47890123", "TI"));
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        for (int i = 0; i < contador; i++) {
            if (listaUsuarios[i].getId().equalsIgnoreCase(usuario.getId())) {
                listaUsuarios[i] = usuario;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        int posicion = -1;
        for (int i = 0; i < contador; i++) {
            if (listaUsuarios[i].getId().equalsIgnoreCase(id)) {
                posicion = i;
                break;
            }
        }
        if (posicion != -1) {
            for (int i = posicion; i < contador - 1; i++) {
                listaUsuarios[i] = listaUsuarios[i + 1];
            }
            listaUsuarios[contador - 1] = null;
            contador--;
            return true;
        }
        return false;
    }

    public static Usuario buscar(String id) {
        int pos = posicion(id);
        return (pos != -1) ? listaUsuarios[pos] : null;
    }
    
    @Override
    public Usuario[] listarTodos() {
        return listaUsuarios;
    }

    @Override
    public Usuario[] listarPorTipo(String tipo) {
        int totalCoincidencias = 0;
        
        for (int i = 0; i < contador; i++) {
            boolean coincide = false;
            if (tipo.equalsIgnoreCase("Estudiante") && listaUsuarios[i] instanceof Estudiante) {
                coincide = true;
            } else if (tipo.equalsIgnoreCase("Docente") && listaUsuarios[i] instanceof Docente) {
                coincide = true;
            } else if (tipo.equalsIgnoreCase("Administrativo") && listaUsuarios[i] instanceof Administrativo) {
                coincide = true;
            }

            if (coincide) {
                totalCoincidencias++;
            }
        }
        
        Usuario[] filtrados = new Usuario[totalCoincidencias];
        int indiceFiltrados = 0;
        
        for (int i = 0; i < contador; i++) {
            boolean coincide = false;
            if (tipo.equalsIgnoreCase("Estudiante") && listaUsuarios[i] instanceof Estudiante) {
                coincide = true;
            } else if (tipo.equalsIgnoreCase("Docente") && listaUsuarios[i] instanceof Docente) {
                coincide = true;
            } else if (tipo.equalsIgnoreCase("Administrativo") && listaUsuarios[i] instanceof Administrativo) {
                coincide = true;
            }
            
            if (coincide) {
                filtrados[indiceFiltrados] = listaUsuarios[i];
                indiceFiltrados++;
            }
        }
        return filtrados;
    }

    @Override
    public int getCantidadActual() {
        return contador;
    }
 
    @Override
    public Usuario buscarPorId(String id) {
        return buscar(id);
    }

    public static int posicion(String id) {
        int pos = -1;
        for (int i = 0; i < contador; i++) {
            if(listaUsuarios[i].getId().equalsIgnoreCase(id)) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    private boolean existeId(String id) {
        return posicion(id) != -1;
    }

    private boolean existeDni(String dni) {
        for (int i = 0; i < contador; i++) {
            if (listaUsuarios[i].getDni().equalsIgnoreCase(dni)) {
                return true;
            }
        }
        return false;
    }
}
