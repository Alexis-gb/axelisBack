package co.com.axelis.axelisBack.services;

import java.util.Collection;

import co.com.axelis.axelisBack.enumeration.Seccion;
import co.com.axelis.axelisBack.models.Publicacion;

public interface PublicacionService {

    // Create
    Publicacion crear(Publicacion publicacion);

    // Read
    Publicacion obtener(Long id);
    Collection<Publicacion> listar(int limit);
    Collection<Publicacion> sugerencias(String titulo);
    Collection<Publicacion> listarPorSeccion(Seccion seccion);

    // Update
    Publicacion actualizar(Publicacion publicacion);

    // Delete
    boolean eliminar(Long id);
}
