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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.axelis.axelisBack.models.Publicacion;
import co.com.axelis.axelisBack.models.Response;
import co.com.axelis.axelisBack.services.implementations.PublicacionServiceImplement;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("publicacion")
@RequiredArgsConstructor
public class PublicacionResource {
    
    private final PublicacionServiceImplement publicacionService;
    private final UsuarioServiceImplement usuarioService;

    // Crear publicación, by admin
    @PostMapping("/crear")
    public ResponseEntity<Response> crearPublicacion(@RequestHeader(value = "auth") String token, @RequestBody @Valid Publicacion publicacion){
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
                    .data(Map.of("publicacion", publicacionService.crear(publicacion)))
                    .message("Publicación creada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    // Obtener publicación por id, by Admin, usuario y invitado
    @GetMapping("/id/{id}")
    public ResponseEntity<Response> obtenerPublicacionPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("publicacion", publicacionService.obtener(id)))
                    .message("Publicación recuperada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    // Obtener sugerencias de publicaciones, by Admin, usuario y invitado
    @GetMapping("/sugerencias/{titulo}")
    public ResponseEntity<Response> obtenerSugerencias(@PathVariable("titulo") String titulo){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("sugerencias", publicacionService.sugerencias(titulo)))
                    .message("Sugerencias obtenidas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    // Obtener lista de publicaciones, by Admin, usuario y invitado
    @GetMapping("/listar")
    public ResponseEntity<Response> obtenerUsuarios(){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("publicaciones", publicacionService.listar(15)))
                    .message("Publicaciones recuperadas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    // Actualizar publicación, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<Response> actualizarUsuarios(@RequestHeader(value = "auth") String token, @RequestBody @Valid Publicacion publicacion){
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
                    .data(Map.of("publicacion", publicacionService.actualizar(publicacion)))
                    .message("Publicación actualizada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarPublicacion(@RequestHeader(value = "auth") String token, @PathVariable("id") Long id){
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
                    .data(Map.of("eliminado", publicacionService.eliminar(id)))
                    .message("Publicacion eliminada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
        );
    }

}
