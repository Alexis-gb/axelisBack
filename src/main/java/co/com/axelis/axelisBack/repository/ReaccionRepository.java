package co.com.axelis.axelisBack.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.axelis.axelisBack.models.Publicacion;
import co.com.axelis.axelisBack.models.Reaccion;
import co.com.axelis.axelisBack.models.Usuario;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    Collection<Reaccion> findAllByPublicacionAsociada(Publicacion publicacionAsociada);
    Collection<Reaccion> findAllByAutor(Usuario usuario);
    Long countByPublicacionAsociada(Publicacion publicacionAsociada);
}
