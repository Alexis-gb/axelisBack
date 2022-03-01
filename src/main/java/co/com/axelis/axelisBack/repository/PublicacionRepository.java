package co.com.axelis.axelisBack.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.axelis.axelisBack.enumeration.Seccion;
import co.com.axelis.axelisBack.models.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findAllByFechaBetween(Calendar publicationTimeStart, Calendar publicationTimeEnd);
    List<Publicacion> findAllByTituloContains(String titulo);
    List<Publicacion> findAllBySeccion(Seccion seccion);
}
