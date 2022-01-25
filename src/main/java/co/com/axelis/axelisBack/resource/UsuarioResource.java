package co.com.axelis.axelisBack.resource;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.axelis.axelisBack.models.Response;
import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioResource {
    private final UsuarioServiceImplement usuarioService;
    
    @GetMapping("/listar")
    public ResponseEntity<Response> obtenerUsuarios(){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("usuarios", usuarioService.listar(30)))
                    .message("Usuarios recuperados")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Response> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("usuario", usuarioService.obtener(id)))
                    .message("Usuario recuperado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    @PostMapping("/crear")
    public ResponseEntity<Response> crearUsuario(@RequestBody @Valid Usuario usuario){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("usuario", usuarioService.crear(usuario)))
                    .message("Usuario creado")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build()
        );
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Response> obtenerUsuarioPorCorreo(@PathVariable("correo") String correo){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("usuario", usuarioService.obtenerPorCorreo(correo)))
                    .message("Usuario recuperado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> Eliminar(@PathVariable("id") Long id){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("Eliminado", usuarioService.eliminar(id)))
                    .message("Usuario eliminado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }
}
