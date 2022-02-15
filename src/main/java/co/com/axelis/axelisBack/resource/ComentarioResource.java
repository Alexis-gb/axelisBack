package co.com.axelis.axelisBack.resource;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.axelis.axelisBack.models.Comentario;
import co.com.axelis.axelisBack.models.Response;
import co.com.axelis.axelisBack.services.implementations.ComentarioServiceImplement;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("comentario")
@RequiredArgsConstructor
public class ComentarioResource {
    
    private final ComentarioServiceImplement comentarioService;
    private final UsuarioServiceImplement usuarioService;

    // Crear comentario, by Usuario
    @PostMapping("/crear")
    public ResponseEntity<Response> crearComentario(@RequestHeader("auth") String token, @RequestBody @Valid Comentario comentario){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("comentario", comentarioService.crear(comentario)))
                    .message("Comentario creado")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build(), responseHeaders, HttpStatus.CREATED
        );
    }

    // Obtener comentario por Id, by All
    @GetMapping("/comentario/{id}")
    public ResponseEntity<Response> obtenerComentarioPorId(@PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("comentario", comentarioService.obtener(id)))
                    .message("Comentario recuperado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener todos los comentarios, by Admin
    @GetMapping("/listar")
    public ResponseEntity<Response> obtenerComentarios(@RequestHeader("auth") String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("comentarios", comentarioService.listar(30)))
                    .message("Comentarios recuperado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener comentarios de publicación especifica, by All
    @GetMapping("/listarDePublicacion/{id}")
    public ResponseEntity<Response> comentariosDePublicacion(@PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("comentarios", comentarioService.listarDePublicacion(id)))
                    .message("Comentarios recuperado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener comentarios de un usuario en especifico, by Admin
    @GetMapping("/listarDeAutor/{id}")
    public ResponseEntity<Response> listarDeAutor(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("comentarios", comentarioService.listarDeAutor(id)))
                    .message("Comentarios recuperado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Actualizar comentario, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<Response> actualizarComentario(@RequestHeader("auth") String token, @RequestBody @Valid Comentario comentario){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("comentario", comentarioService.actualizar(comentario)))
                    .message("Comentario actualizado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Eliminar comentario, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarComentario(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("eliminado", comentarioService.eliminar(id)))
                    .message("Comentario eliminado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // ELiminar comentario, by Usuario
    /* @DeleteMapping("/eliminar/u/{id}")
    public ResponseEntity<Response> eliminarComentarioPorUsuario(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("eliminado", comentarioService.eliminar(id)))
                    .message("Comentario eliminado")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    } */

    public ResponseEntity<Response> respuestaNegativa(HttpHeaders responseHeaders){
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("No cuentas con los permisos para realizar esta acción")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build(), responseHeaders, HttpStatus.UNAUTHORIZED
        );
    }
    
}
