package co.com.axelis.axelisBack.services;

import java.util.List;

import co.com.axelis.axelisBack.enumeration.Seccion;
import co.com.axelis.axelisBack.models.Publicacion;

public interface PublicacionService {

    // Create
    Publicacion crear(Publicacion publicacion);

    // Read
    Publicacion obtener(Long id);
    List<Publicacion> listar(int limit);
    List<Publicacion> sugerencias(String titulo);
    List<Publicacion> listarPorSeccion(Seccion seccion);

    // Update
    Publicacion actualizar(Publicacion publicacion);

    // Delete
    boolean eliminar(Long id);
}
