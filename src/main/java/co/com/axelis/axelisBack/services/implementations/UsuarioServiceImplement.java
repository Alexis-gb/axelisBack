package co.com.axelis.axelisBack.services.implementations;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.com.axelis.axelisBack.models.Rol;
import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.repository.RolRepository;
import co.com.axelis.axelisBack.repository.UsuarioRepository;
import co.com.axelis.axelisBack.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UsuarioServiceImplement implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public Usuario crear(Usuario usuario) {
        log.info("Guardando un nuevo usuario {}", usuario.getNombre());
        return usuarioRepository.save(usuario);
    }

    @Override
    public Collection<Usuario> listar(int limit) {
        log.info("Listando todos los usuarios");
        return usuarioRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Usuario obtener(Long id) {
        log.info("Obteniendo usuario por Id: {}", id);
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario obtenerPorCorreo(String correo) {
        log.info("Obteniendo usuario por Correo: {}", correo);
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        log.info("Actualizando el usuario {}", usuario.getNombre());
        return usuarioRepository.save(usuario);
    }

    @Override
    public Boolean eliminar(Long id) {
        log.info("Eliminando el usuario con Id: {}", id);
        usuarioRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Rol guardarRol(Rol rol) {
        // TODO: Log.info
        return rolRepository.save(rol);
    }

    @Override
    public void agregarRolUsuario(String correo, String rolNombre) {
        // TODO: Log.info
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        Rol rol = rolRepository.findByNombre(rolNombre);
        usuario.getRoles().add(rol);
    }
    
}
