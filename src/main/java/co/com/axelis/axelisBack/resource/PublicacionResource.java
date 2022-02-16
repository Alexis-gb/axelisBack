package co.com.axelis.axelisBack.resource;

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

import co.com.axelis.axelisBack.enumeration.Seccion;
import co.com.axelis.axelisBack.models.Publicacion;
import co.com.axelis.axelisBack.services.implementations.PublicacionServiceImplement;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/publicacion")
@RequiredArgsConstructor
public class PublicacionResource {
    
    private final PublicacionServiceImplement publicacionService;
    private final UsuarioServiceImplement usuarioService;

    // Crear publicación, by admin
    @PostMapping("/crear")
    public ResponseEntity<String> crearPublicacion(@RequestHeader(value = "auth") String token, @RequestBody @Valid Publicacion publicacion){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(publicacionService.crear(publicacion).toString(), HttpStatus.CREATED);
    }

    // Obtener publicación por id, by All
    @GetMapping("/post/{id}")
    public ResponseEntity<String> obtenerPublicacionPorId(@PathVariable("id") Long id){
        return new ResponseEntity<String>(publicacionService.obtener(id).toString(), HttpStatus.OK
        );
    }

    // Obtener lista de publicaciones, by All
    @GetMapping("/listar")
    public ResponseEntity<String> obtenerPublicaciones(){
        return new ResponseEntity<String>(publicacionService.listar(15).toString(), HttpStatus.OK);
    }

    // Obtener sugerencias de publicaciones, by All
    @GetMapping("/sugerencias/{titulo}")
    public ResponseEntity<String> obtenerSugerencias(@PathVariable("titulo") String titulo){
        return new ResponseEntity<String>(publicacionService.sugerencias(titulo).toString(), HttpStatus.OK);
    }

    // Listar por seccion.
    @GetMapping("/seccion/{seccion}")
    public ResponseEntity<String> listarPorSeccion(@PathVariable("seccion") Seccion seccion){
        return new ResponseEntity<String>(publicacionService.listarPorSeccion(seccion).toString(), HttpStatus.OK);
    }

    // Actualizar publicación, by Admin
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarPublicacion(@RequestHeader("auth") String token, @RequestBody @Valid Publicacion publicacion){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(publicacionService.actualizar(publicacion).toString(), HttpStatus.OK);
    }

    // Eliminar publicacion por Id, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPublicacion(@RequestHeader("auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(publicacionService.eliminar(id), HttpStatus.OK);
    }

    public ResponseEntity<String> respuestaNegativa(){
        return new ResponseEntity<String>("unauthorized", HttpStatus.UNAUTHORIZED);
    }

}
