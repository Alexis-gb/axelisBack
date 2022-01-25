package co.com.axelis.axelisBack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.repository.UsuarioRepository;

@SpringBootApplication
public class AxelisBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxelisBackApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UsuarioRepository usuarioRepository){
		return args -> {
			usuarioRepository.save(new Usuario(null, "Alexis", "alexis.gb249@gmail.com", "adawdawbdyhabwd"));
			usuarioRepository.save(new Usuario(null, "Liliana", "lili@gmail.com", "sfefseaffaseef"));
			usuarioRepository.save(new Usuario(null, "Juan", "juan@gmail.com", "aesfsaefvdbrr"));
			usuarioRepository.save(new Usuario(null, "Miguel", "miguel@gmail.com", "asefaefbdtb"));
			usuarioRepository.save(new Usuario(null, "Vale", "vale@gmail.com", "bxfvzdrvdr"));
			usuarioRepository.save(new Usuario(null, "Mary", "mary@gmail.com", "drzgdrgszrg"));
		};
	}
}
