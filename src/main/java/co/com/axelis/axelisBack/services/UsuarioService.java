package co.com.axelis.axelisBack.services;

import java.util.Collection;

import co.com.axelis.axelisBack.models.Usuario;

public interface UsuarioService {
    Usuario crear(Usuario usuario);
    Collection<Usuario> listar(int limit);
    Usuario obtener(Long id);
    Usuario obtenerPorCorreo(String correo);
    Usuario actualizar(Usuario usuario);
    Boolean eliminar(Long id);
}
