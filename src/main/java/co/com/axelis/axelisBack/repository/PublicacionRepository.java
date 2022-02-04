package co.com.axelis.axelisBack.repository;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.axelis.axelisBack.models.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    Collection<Publicacion> findAllByFechaBetween(Calendar publicationTimeStart, Calendar publicationTimeEnd);
    Collection<Publicacion> findAllByTituloContains(String titulo);
}
