package co.com.axelis.axelisBack.resource;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
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

import co.com.axelis.axelisBack.enumeration.Seccion;
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
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("publicacion", publicacionService.crear(publicacion)))
                    .message("Publicación creada")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build(), responseHeaders, HttpStatus.CREATED
        );
    }

    // Obtener publicación por id, by All
    @GetMapping("/post/{id}")
    public ResponseEntity<Response> obtenerPublicacionPorId(@PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("publicacion", publicacionService.obtener(id)))
                    .message("Publicación recuperada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener lista de publicaciones, by All
    @GetMapping("/listar")
    public ResponseEntity<Response> obtenerPublicaciones(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("publicaciones", publicacionService.listar(15)))
                    .message("Publicaciones recuperadas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Obtener sugerencias de publicaciones, by All
    @GetMapping("/sugerencias/{titulo}")
    public ResponseEntity<Response> obtenerSugerencias(@PathVariable("titulo") String titulo){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("sugerencias", publicacionService.sugerencias(titulo)))
                    .message("Sugerencias obtenidas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Listar por seccion.
    @GetMapping("/seccion/{seccion}")
    public ResponseEntity<Response> listarPorSeccion(@PathVariable("seccion") Seccion seccion){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("publicaciones", publicacionService.listarPorSeccion(seccion)))
                    .message("Publicaciones obtenidas")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Actualizar publicación, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<Response> actualizarPublicacion(@RequestHeader("auth") String token, @RequestBody @Valid Publicacion publicacion){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("publicacion", publicacionService.actualizar(publicacion)))
                    .message("Publicación actualizada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

    // Eliminar publicacion por Id, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarPublicacion(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin","http://localhost:4200");
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa(responseHeaders);
        }

        return new ResponseEntity<Response>(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("eliminado", publicacionService.eliminar(id)))
                    .message("Publicacion eliminada")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build(), responseHeaders, HttpStatus.OK
        );
    }

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
