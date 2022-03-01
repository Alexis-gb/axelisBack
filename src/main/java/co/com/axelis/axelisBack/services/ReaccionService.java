package co.com.axelis.axelisBack.services;

import java.util.Collection;

import co.com.axelis.axelisBack.models.Reaccion;

public interface ReaccionService {
   
    // Create
    Reaccion crear(Reaccion reaccion);

    // Read
    Reaccion obtener(Long id);
    Collection<Reaccion> listar(int limit);
    Collection<Reaccion> listarDePublicacion(Long id);
    Collection<Reaccion> listarDeAutor(Long id);
    Long contarPorPublicacion(Long id);

    // Update
    Reaccion actualizar(Reaccion reaccion);

    // Delete
    String eliminar(Long id);
}
