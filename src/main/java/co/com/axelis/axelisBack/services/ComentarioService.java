package co.com.axelis.axelisBack.services;

import java.util.Collection;

import co.com.axelis.axelisBack.models.Comentario;

public interface ComentarioService {

    // Create
    Comentario crear(Comentario comentario);

    // Read
    Comentario obtener(Long id);
    Collection<Comentario> listar(int limit);
    Collection<Comentario> listarDePublicacion(Long id);
    Collection<Comentario> listarDeAutor(Long id);

    // Update
    Comentario actualizar(Comentario comentario);

    // Delete
    boolean eliminar(Long id);
}
