package co.com.axelis.axelisBack.services.implementations;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.com.axelis.axelisBack.models.Publicacion;
import co.com.axelis.axelisBack.repository.PublicacionRepository;
import co.com.axelis.axelisBack.services.PublicacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PublicacionServiceImplement implements PublicacionService {

    private final PublicacionRepository publicacionRepository;

    @Override
    public Publicacion crear(Publicacion publicacion) {
        log.info("Guardando nueva publicación: {}", publicacion.getTitulo());
        return publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion obtener(Long id) {
        log.info("Obteniendo publicacion por Id: {}", id);
        return publicacionRepository.findById(id).get();
    }

    @Override
    public Collection<Publicacion> sugerencias(String titulo) {
        log.info("Obteniendo sugerencias por titulo: {}", titulo);
        return publicacionRepository.findAllByTituloContains(titulo);
    }

    @Override
    public Collection<Publicacion> listar(int limit) {
        log.info("Listando todas las publicaciones");
        return publicacionRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Publicacion actualizar(Publicacion publicacion) {
        log.info("Actualizando publicación: {}", publicacion.getTitulo());
        return publicacionRepository.save(publicacion);
    }

    @Override
    public boolean eliminar(Long id) {
        log.info("Eliminando la publicación con Id: {}", id);
        publicacionRepository.deleteById(id);
        return Boolean.TRUE;
    }
    
}
