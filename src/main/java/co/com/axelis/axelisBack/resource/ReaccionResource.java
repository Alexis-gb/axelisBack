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

import co.com.axelis.axelisBack.models.Reaccion;
import co.com.axelis.axelisBack.models.Response;
import co.com.axelis.axelisBack.services.implementations.ReaccionServiceImplement;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reaccion")
@RequiredArgsConstructor
public class ReaccionResource {
    
    private final ReaccionServiceImplement reaccionService;
    private final UsuarioServiceImplement usuarioService;

    // Crear reaccion, by Usuario
    @PostMapping("/crear")
    public ResponseEntity<Response> crearReaccion(@RequestHeader("auth") String token, @RequestBody @Valid Reaccion reaccion){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("reaccion", reaccionService.crear(reaccion)))
                    .message("Reaccion creada")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build(), responseHeaders, HttpStatus.CREATED
        );
    }

    // Obtener reaccion por Id, by All
    @GetMapping("/reaccion/{id}")
    public ResponseEntity<Response> obtenerReaccionPorId(@PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("reaccion", reaccionService.obtener(id)))
                    .message("Reaccion recuperada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener todos los reacciones, by Admin
    @GetMapping("/listar")
    public ResponseEntity<Response> obtenerReacciones(@RequestHeader("auth") String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("reacciones", reaccionService.listar(30)))
                    .message("Reacciones recuperadas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener reacciones de publicación especifica, by All
    @GetMapping("/listarDePublicacion/{id}")
    public ResponseEntity<Response> reaccionesDePublicacion(@PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("reacciones", reaccionService.listarDePublicacion(id)))
                    .message("Reacciones recuperadas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener reacciones de un usuario en especifico, by Admin
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
                    .data(Map.of("reacciones", reaccionService.listarDeAutor(id)))
                    .message("Reacciones recuperadas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Actualizar reaccion, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<Response> actualizarReaccion(@RequestHeader("auth") String token, @RequestBody @Valid Reaccion reaccion){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("reaccion", reaccionService.actualizar(reaccion)))
                    .message("Reaccion actualizada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Eliminar reaccion, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarReaccion(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("eliminado", reaccionService.eliminar(id)))
                    .message("Reaccion eliminada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // ELiminar reaccion, by Usuario
    /* @DeleteMapping("/eliminar/u/{id}")
    public ResponseEntity<Response> eliminarReaccionPorUsuario(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("eliminado", reaccionService.eliminar(id)))
                    .message("Reaccion eliminado")
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