package co.com.axelis.axelisBack.services.implementations;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.com.axelis.axelisBack.models.Rol;
import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.repository.RolRepository;
import co.com.axelis.axelisBack.repository.UsuarioRepository;
import co.com.axelis.axelisBack.services.UsuarioService;
import co.com.axelis.axelisBack.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UsuarioServiceImplement implements UsuarioService {

    @PersistenceContext
    EntityManager entityManager;

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Usuario crear(Usuario usuario) {
        log.info("Guardando un nuevo usuario {}", usuario.getNombre());
        // Formateo del correo a minusculas.
        usuario.setCorreo(usuario.getCorreo().toLowerCase());

        // Hasheo de la contraseña, "deprecated" pero será cambiado en futuras versiones.
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        usuario.setContrasena(argon2.hash(1, 1024, 1, usuario.getContrasena()));

        // Guardando usuario
        Usuario creado = usuarioRepository.save(usuario);

        // Asignación rol por defecto, "usuario".
        agregarRolUsuario(creado.getCorreo(), 2L);

        return creado;
    }

    @Override
    public Usuario registro(Usuario usuario) {
        log.info("Guardando un nuevo usuario {}", usuario.getNombre());
        // Formateo del correo a minusculas.
        usuario.setCorreo(usuario.getCorreo().toLowerCase());

        // Hasheo de la contraseña, "deprecated" pero será cambiado en futuras versiones.
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        usuario.setContrasena(argon2.hash(1, 1024, 1, usuario.getContrasena()));

        // Guardando usuario
        Usuario creado = usuarioRepository.save(usuario);

        // Asignación rol por defecto, "usuario".
        agregarRolUsuario(creado.getCorreo(), 1L);

        return creado;
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
    public void agregarRolUsuario(String correo, Long idRol) {
        // TODO: Log.info
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        Rol rol = rolRepository.findById(idRol).get();
        usuario.getRoles().add(rol);
    }

    @Override
    public Usuario verificarCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE correo = :correo";
        List<Usuario> lista = entityManager.createQuery(query)
                            .setParameter("correo", usuario.getCorreo())
                            .getResultList();

        if(lista.isEmpty()){ return null; }
        String contrasenaHashed = lista.get(0).getContrasena();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(contrasenaHashed, usuario.getContrasena())){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public Rol obtenerRol(Long id){
        return rolRepository.getById(id);
    }

    @Override
    public boolean validarRol(String token, Long nivel) {
        Long idUsuario = Long.parseLong(jwtUtil.getKey(token));
        Usuario usuarioAuth = idUsuario != null ? obtener(idUsuario) : null;
        if(usuarioAuth == null){return false;}
        Collection<Rol> rolesAuth = usuarioAuth.getRoles();
        Rol nivelAcceso = obtenerRol(nivel);
        return rolesAuth.contains(nivelAcceso);
    }
    
}
