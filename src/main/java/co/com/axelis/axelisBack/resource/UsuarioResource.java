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
import org.springframework.web.bind.annotation.RequestHeader;
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
    
    // Listar usuarios, by Admin
    @GetMapping("/listar")
    public ResponseEntity<Response> obtenerUsuarios(@RequestHeader(value = "auth") String token){
        if(!usuarioService.validarRol(token, 2L)){
            return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("No cuentas con los permisos para realizar esta acción")
                        .status(HttpStatus.UNAUTHORIZED)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build()
            );
        }

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

    // Buscar usuario por id, by Admin
    @GetMapping("/id/{id}")
    public ResponseEntity<Response> obtenerUsuarioPorId(@RequestHeader(value = "auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("No cuentas con los permisos para realizar esta acción")
                        .status(HttpStatus.UNAUTHORIZED)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build()
            );
        }
        
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

    // Creación de usuario, by Admin
    @PostMapping("/crear")
    public ResponseEntity<Response> crearUsuario(@RequestHeader(value = "auth") String token, @RequestBody @Valid Usuario usuario){
        if(!usuarioService.validarRol(token, 2L)){
            return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("No cuentas con los permisos para realizar esta acción")
                        .status(HttpStatus.UNAUTHORIZED)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build()
            );
        }
        
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

    // Creación de usuario, by Usuario
    @PostMapping("/registro")
    public ResponseEntity<Response> registrar(@RequestBody @Valid Usuario usuario){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("usuario", usuarioService.registro(usuario)))
                    .message("Usuario creado")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build()
        );
    }

    // Buscar usuario por correo, by Admin
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Response> obtenerUsuarioPorCorreo(@RequestHeader(value = "auth") String token, @PathVariable("correo") String correo){
        if(!usuarioService.validarRol(token, 2L)){
            return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("No cuentas con los permisos para realizar esta acción")
                        .status(HttpStatus.UNAUTHORIZED)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build()
            );
        }
        
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

    // Eliminar usuario por id, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> Eliminar(@RequestHeader(value = "auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("No cuentas con los permisos para realizar esta acción")
                        .status(HttpStatus.UNAUTHORIZED)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build()
            );
        }

        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("eliminado", usuarioService.eliminar(id)))
                    .message("Usuario eliminado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }
}
