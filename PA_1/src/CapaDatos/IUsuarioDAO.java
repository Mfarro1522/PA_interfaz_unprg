
package CapaDatos;

import CapaLogica.modelos.Usuario;
import java.util.List;

/**
 *
 * @author jackh
 */
public interface IUsuarioDAO {
    boolean agregar(Usuario usuario);
    boolean actualizar(Usuario usuario);
    boolean eliminar(String id);
    
    Usuario buscar(String id);
    List<Usuario> listarTodos();
    
    List<Usuario> listarPorTipo(String tipo);
}
