package co.com.axelis.axelisBack.services;

import java.util.Collection;

import co.com.axelis.axelisBack.models.Rol;
import co.com.axelis.axelisBack.models.Usuario;

public interface UsuarioService {
    Usuario crear(Usuario usuario);
    Rol guardarRol(Rol rol);
    void agregarRolUsuario(String correo, String rolNombre);
    Collection<Usuario> listar(int limit);
    Usuario obtener(Long id);
    Usuario obtenerPorCorreo(String correo);
    Usuario actualizar(Usuario usuario);
    Boolean eliminar(Long id);
}
