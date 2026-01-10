
package CapaDatos;

import CapaLogica.modelos.Usuario;

/**
 *
 * @author jackh
 */
public interface IUsuarioDAO {
    boolean agregar(Usuario usuario);
    boolean actualizar(Usuario usuario);
    boolean eliminar(String id);
    
    Usuario buscarPorId(String id);
    Usuario[] listarTodos();
    Usuario[] listarPorTipo(String tipo);
    int getCantidadActual();
}
