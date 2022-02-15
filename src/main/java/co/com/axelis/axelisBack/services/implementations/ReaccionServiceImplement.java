package co.com.axelis.axelisBack.services.implementations;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.com.axelis.axelisBack.models.Publicacion;
import co.com.axelis.axelisBack.models.Reaccion;
import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.repository.PublicacionRepository;
import co.com.axelis.axelisBack.repository.ReaccionRepository;
import co.com.axelis.axelisBack.repository.UsuarioRepository;
import co.com.axelis.axelisBack.services.ReaccionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ReaccionServiceImplement implements ReaccionService {

    private final ReaccionRepository reaccionRepository;
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Reaccion crear(Reaccion reaccion) {
        log.info("Guardando nueva reacción: {}", reaccion.getId());
        return reaccionRepository.save(reaccion);
    }

    @Override
    public Reaccion obtener(Long id) {
        log.info("Obteniendo reacción por Id: {}", id);
        return reaccionRepository.findById(id).get();
    }

    @Override
    public Collection<Reaccion> listar(int limit) {
        log.info("Listando todas las reacciones");
        return reaccionRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Collection<Reaccion> listarDePublicacion(Long id) {
        log.info("Listando todas las reacciones de la publicación: {}", id);
        Publicacion publicacionAsociada = publicacionRepository.findById(id).get();
        return publicacionAsociada != null ? reaccionRepository.findAllByPublicacionAsociada(publicacionAsociada) : null;
    }

    @Override
    public Collection<Reaccion> listarDeAutor(Long id) {
        log.info("Listando todas las reacciones del usuario: {}", id);
        Usuario usuarioAsociado = usuarioRepository.findById(id).get();
        return reaccionRepository.findAllByAutor(usuarioAsociado);
    }

    @Override
    public Reaccion actualizar(Reaccion reaccion) {
        log.info("Actualizando reacción: {}", reaccion.getId());
        return reaccionRepository.save(reaccion);
    }

    @Override
    public boolean eliminar(Long id) {
        log.info("Eliminando reacción: {}", id);
        reaccionRepository.deleteById(id);
        return Boolean.TRUE;
    }
    
}
