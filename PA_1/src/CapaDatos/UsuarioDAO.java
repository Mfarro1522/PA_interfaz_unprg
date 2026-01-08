package CapaDatos;

import CapaLogica.modelos.Administrativo;
import CapaLogica.modelos.Docente;
import CapaLogica.modelos.Estudiante;
import CapaLogica.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jackh
 */
public class UsuarioDAO implements IUsuarioDAO {

    private static List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    public boolean agregar(Usuario usuario) {
        return listaUsuarios.add(usuario);
    }

    static {
        inicializarDatos();
    }

    private static void inicializarDatos() {
        listaUsuarios.add(new Estudiante("1", "Ricardo Perez", "71234567", "EPICI"));
        listaUsuarios.add(new Estudiante("2", "Gianluca Saenz", "72345678", "FACHSE"));
        listaUsuarios.add(new Estudiante("3", "Christian Tunoque", "73456789", "FICSA"));
        listaUsuarios.add(new Estudiante("4", "David Santamaria", "74567890", "FIQUIA"));

        listaUsuarios.add(new Docente("5", "Dra. Giuliana Lecca", "01234567", "FACFYM"));
        listaUsuarios.add(new Docente("6", "Dr. Roger Alarcon", "02345678", "FACFYM"));
        listaUsuarios.add(new Docente("7", "Dr. Carlos Valdivia", "03456789", "FACFYM"));
        listaUsuarios.add(new Docente("11", "Est. Fernando Carranza", "03456789", "ESTADISTICA"));

        listaUsuarios.add(new Administrativo("8", "Luis Barrios", "45678901", "TI"));
        listaUsuarios.add(new Administrativo("9", "Yoshimar Ok", "46789012", "TI"));
        listaUsuarios.add(new Administrativo("10", "Pedro Reyes", "47890123", "TI"));
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId().equals(usuario.getId())) {
                listaUsuarios.set(i, usuario);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        return listaUsuarios.removeIf(u -> u.getId().equals(id));
    }

    @Override
    public Usuario buscar(String id) {
        return listaUsuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(listaUsuarios);
    }

    @Override
    public List<Usuario> listarPorTipo(String tipo) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : listaUsuarios) {
            if (tipo.equalsIgnoreCase("Estudiante") && u instanceof Estudiante) {
                filtrados.add(u);
            } else if (tipo.equalsIgnoreCase("Docente") && u instanceof Docente) {
                filtrados.add(u);
            } else if (tipo.equalsIgnoreCase("Administrativo") && u instanceof Administrativo) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }
}
