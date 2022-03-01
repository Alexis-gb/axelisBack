package co.com.axelis.axelisBack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.axelis.axelisBack.models.Comentario;
import co.com.axelis.axelisBack.models.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findAllByPublicacionAsociada(Long publicacionAsociada);
    List<Comentario> findAllByAutor(Usuario usuario);
}