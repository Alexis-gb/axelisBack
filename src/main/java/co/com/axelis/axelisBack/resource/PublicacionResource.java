package co.com.axelis.axelisBack.resource;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

import co.com.axelis.axelisBack.enumeration.Seccion;
import co.com.axelis.axelisBack.models.Publicacion;
import co.com.axelis.axelisBack.services.implementations.PublicacionServiceImplement;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/publicacion")
public class PublicacionResource {
    
    private final PublicacionServiceImplement publicacionService;
    private final UsuarioServiceImplement usuarioService;

    // Crear publicación, by admin
    @PostMapping("/crear")
    public ResponseEntity<Object> crearPublicacion(@RequestHeader(value = "auth") String token, @RequestBody @Valid Publicacion publicacion){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<Object>(publicacionService.crear(publicacion), HttpStatus.CREATED);
    }

    // Obtener publicación por id, by All
    @GetMapping("/post/{id}")
    public ResponseEntity<Object> obtenerPublicacionPorId(@PathVariable("id") Long id){
        return new ResponseEntity<Object>(publicacionService.obtener(id), HttpStatus.OK
        );
    }

    // Obtener lista de publicaciones, by All
    @GetMapping("/listar")
    public ResponseEntity<Object> obtenerPublicaciones(){
        return new ResponseEntity<Object>(publicacionService.listar(15), HttpStatus.OK);
    }

    // Obtener sugerencias de publicaciones, by All
    @GetMapping("/sugerencias/{titulo}")
    public ResponseEntity<Object> obtenerSugerencias(@PathVariable("titulo") String titulo){
        return new ResponseEntity<Object>(publicacionService.sugerencias(titulo), HttpStatus.OK);
    }

    // Listar por seccion.
    @GetMapping("/seccion/{seccion}")
    public ResponseEntity<Object> listarPorSeccion(@PathVariable("seccion") Seccion seccion){
        return new ResponseEntity<Object>(publicacionService.listarPorSeccion(seccion), HttpStatus.OK);
    }

    // Actualizar publicación, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<Object> actualizarPublicacion(@RequestHeader("auth") String token, @RequestBody @Valid Publicacion publicacion){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<Object>(publicacionService.actualizar(publicacion), HttpStatus.OK);
    }

    // Eliminar publicacion por Id, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> eliminarPublicacion(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<Object>(publicacionService.eliminar(id), HttpStatus.OK);
    }

    public ResponseEntity<Object> respuestaNegativa(){
        return new ResponseEntity<Object>("unauthorized", HttpStatus.UNAUTHORIZED);
    }

}
