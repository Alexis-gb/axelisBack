package co.com.axelis.axelisBack.resource;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import co.com.axelis.axelisBack.services.implementations.ComentarioServiceImplement;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/comentario")
public class ComentarioResource {
    
    private final ComentarioServiceImplement comentarioService;
    private final UsuarioServiceImplement usuarioService;

    // Crear comentario, by Usuario
    @PostMapping("/crear")
    public ResponseEntity<String> crearComentario(@RequestHeader("auth") String token, @RequestBody @Valid Comentario comentario){
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(comentarioService.crear(comentario).toString(), HttpStatus.CREATED);
    }

    // Obtener comentario por Id, by All
    @GetMapping("/comentario/{id}")
    public ResponseEntity<String> obtenerComentarioPorId(@PathVariable("id") Long id){
        return new ResponseEntity<String>(comentarioService.obtener(id).toString(), HttpStatus.OK);
    }

    // Obtener todos los comentarios, by Admin
    @GetMapping("/listar")
    public ResponseEntity<String> obtenerComentarios(@RequestHeader("auth") String token) {
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(comentarioService.listar(30).toString(), HttpStatus.OK);
    }

    // Obtener comentarios de publicación especifica, by All
    @GetMapping("/listarDePublicacion/{id}")
    public ResponseEntity<Object> comentariosDePublicacion(@PathVariable("id") Long id){
        return new ResponseEntity<Object>(comentarioService.listarDePublicacion(id), HttpStatus.OK);
    }

    // Obtener comentarios de un usuario en especifico, by Admin
    @GetMapping("/listarDeAutor/{id}")
    public ResponseEntity<String> listarDeAutor(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(comentarioService.listarDeAutor(id).toString(), HttpStatus.OK);
    }

    // Actualizar comentario, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarComentario(@RequestHeader("auth") String token, @RequestBody @Valid Comentario comentario){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(comentarioService.actualizar(comentario).toString(), HttpStatus.OK);
    }

    // Eliminar comentario, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarComentario(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(comentarioService.eliminar(id), HttpStatus.OK);
    }

    // ELiminar comentario, by Usuario
    /* @DeleteMapping("/eliminar/u/{id}")
    public ResponseEntity<String> eliminarComentarioPorUsuario(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(comentarioService.eliminar(id).toString(), HttpStatus.OK);
    } */

    public ResponseEntity<String> respuestaNegativa(){
        return new ResponseEntity<String>("unathorized", HttpStatus.UNAUTHORIZED);
    }
    
}
