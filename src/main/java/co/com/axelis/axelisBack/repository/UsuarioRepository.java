package co.com.axelis.axelisBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.axelis.axelisBack.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
