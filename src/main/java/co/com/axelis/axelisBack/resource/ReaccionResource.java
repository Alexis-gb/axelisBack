package co.com.axelis.axelisBack.resource;

import javax.validation.Valid;

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
import co.com.axelis.axelisBack.services.implementations.ReaccionServiceImplement;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/reaccion")
@RequiredArgsConstructor
public class ReaccionResource {
    
    private final ReaccionServiceImplement reaccionService;
    private final UsuarioServiceImplement usuarioService;

    // Crear reaccion, by Usuario
    @PostMapping("/crear")
    public ResponseEntity<String> crearReaccion(@RequestHeader("auth") String token, @RequestBody @Valid Reaccion reaccion){
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(reaccionService.crear(reaccion).toString(), HttpStatus.CREATED);
    }

    // Obtener reaccion por Id, by All
    @GetMapping("/reaccion/{id}")
    public ResponseEntity<String> obtenerReaccionPorId(@PathVariable("id") Long id){
        return new ResponseEntity<String>(reaccionService.obtener(id).toString(), HttpStatus.OK);
    }

    // Obtener todos los reacciones, by Admin
    @GetMapping("/listar")
    public ResponseEntity<String> obtenerReacciones(@RequestHeader("auth") String token) {
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(reaccionService.listar(30).toString(), HttpStatus.OK);
    }

    // Obtener reacciones de publicación especifica, by All
    @GetMapping("/listarDePublicacion/{id}")
    public ResponseEntity<String> reaccionesDePublicacion(@PathVariable("id") Long id){
        return new ResponseEntity<String>(reaccionService.listarDePublicacion(id).toString(), HttpStatus.OK);
    }

    // Obtener reacciones de un usuario en especifico, by Admin
    @GetMapping("/listarDeAutor/{id}")
    public ResponseEntity<String> listarDeAutor(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(reaccionService.listarDeAutor(id).toString(), HttpStatus.OK);
    }

    // Actualizar reaccion, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarReaccion(@RequestHeader("auth") String token, @RequestBody @Valid Reaccion reaccion){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(reaccionService.actualizar(reaccion).toString(), HttpStatus.OK);
    }

    // Eliminar reaccion, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarReaccion(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(reaccionService.eliminar(id), HttpStatus.OK);
    }

    // ELiminar reaccion, by Usuario
    /* @DeleteMapping("/eliminar/u/{id}")
    public ResponseEntity<String> eliminarReaccionPorUsuario(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 1L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(reaccionService.eliminar(id).toString(), HttpStatus.OK);
    } */
    
    public ResponseEntity<String> respuestaNegativa(){
        return new ResponseEntity<String>("unathorized", HttpStatus.UNAUTHORIZED);
    }
}