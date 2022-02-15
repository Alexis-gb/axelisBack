package co.com.axelis.axelisBack.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.axelis.axelisBack.models.Comentario;
import co.com.axelis.axelisBack.models.Publicacion;
import co.com.axelis.axelisBack.models.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Collection<Comentario> findAllByPublicacionAsociada(Publicacion publicacionAsociada);
    Collection<Comentario> findAllByAutor(Usuario usuario);
}