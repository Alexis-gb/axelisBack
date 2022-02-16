package co.com.axelis.axelisBack.services;

import java.util.Collection;

import co.com.axelis.axelisBack.models.Rol;
import co.com.axelis.axelisBack.models.Usuario;

public interface UsuarioService {
    // Gestion de usuarios
    Usuario crear(Usuario usuario);
    Usuario registro(Usuario usuario);
    Collection<Usuario> listar(int limit);
    Usuario obtener(Long id);
    Usuario obtenerPorCorreo(String correo);
    Usuario actualizar(Usuario usuario);
    String eliminar(Long id);

    // Gestión de roles
    Rol guardarRol(Rol rol);
    Rol obtenerRol(Long id);
    void agregarRolUsuario(String correo, Long idRol);

    // Login
    Usuario verificarCredenciales(Usuario usuario);

    // Permisos
    boolean validarRol(String token, Long nivel);
}
