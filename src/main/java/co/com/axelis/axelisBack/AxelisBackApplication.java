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

	
}
