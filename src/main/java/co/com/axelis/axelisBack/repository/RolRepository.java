package co.com.axelis.axelisBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.axelis.axelisBack.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
