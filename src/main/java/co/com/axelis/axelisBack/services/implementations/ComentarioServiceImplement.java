package co.com.axelis.axelisBack.services.implementations;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.com.axelis.axelisBack.models.Comentario;
import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.repository.ComentarioRepository;
import co.com.axelis.axelisBack.repository.UsuarioRepository;
import co.com.axelis.axelisBack.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ComentarioServiceImplement implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Comentario crear(Comentario comentario) {
        log.info("Guardando nuevo comentario: {}", comentario.getComentario());
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario obtener(Long id) {
        log.info("Obteniendo comentario por Id: {}", id);
        return comentarioRepository.findById(id).get();
    }

    @Override
    public List<Comentario> listar(int limit) {
        log.info("Listando todos los comentarios");
        return comentarioRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public List<Comentario> listarDePublicacion(Long id) {
        log.info("Listando todos los comentarios de la publicación: {}", id);
        return comentarioRepository.findAllByPublicacionAsociada(id);
    }

    @Override
    public List<Comentario> listarDeAutor(Long id) {
        log.info("Listando todos los comentarios del usuario: {}", id);
        Usuario usuarioAsociado = usuarioRepository.findById(id).get();
        return comentarioRepository.findAllByAutor(usuarioAsociado);
    }

    @Override
    public Comentario actualizar(Comentario comentario) {
        log.info("Actualizando comentario: {}", comentario.getComentario());
        return comentarioRepository.save(comentario);
    }

    @Override
    public String eliminar(Long id) {
        log.info("Eliminando comentario: {}", id);
        comentarioRepository.deleteById(id);
        return Boolean.TRUE.toString();
    }
    
}
