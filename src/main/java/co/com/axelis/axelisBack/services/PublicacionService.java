package co.com.axelis.axelisBack.services;

import java.util.Collection;

import co.com.axelis.axelisBack.models.Publicacion;

public interface PublicacionService {
    Publicacion crear(Publicacion publicacion);
    Publicacion obtener(Long id);
    Collection<Publicacion> sugerencias(String titulo);
    Collection<Publicacion> listar(int limit);
    Publicacion actualizar(Publicacion publicacion);
    boolean eliminar(Long id);
}
