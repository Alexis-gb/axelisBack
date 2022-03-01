package co.com.axelis.axelisBack.services;

import java.util.List;

import co.com.axelis.axelisBack.models.Comentario;

public interface ComentarioService {

    // Create
    Comentario crear(Comentario comentario);

    // Read
    Comentario obtener(Long id);
    List<Comentario> listar(int limit);
    List<Comentario> listarDePublicacion(Long id);
    List<Comentario> listarDeAutor(Long id);

    // Update
    Comentario actualizar(Comentario comentario);

    // Delete
    String eliminar(Long id);
}
