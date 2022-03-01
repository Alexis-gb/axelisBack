package co.com.axelis.axelisBack.resource;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/usuario")
public class UsuarioResource {
    private final UsuarioServiceImplement usuarioService;
    
    // Listar usuarios, by Admin
    @GetMapping("/listar")
    public ResponseEntity<String> obtenerUsuarios(@RequestHeader(value = "auth") String token){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(usuarioService.listar(30).toString(), HttpStatus.OK);
    }

    // Buscar usuario por id, by All
    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return new ResponseEntity<Usuario>(usuarioService.obtener(id), HttpStatus.OK);
    }

    // Creación de usuario, by Admin
    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestHeader(value = "auth") String token, @RequestBody @Valid Usuario usuario){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }
        
        return new ResponseEntity<String>(usuarioService.crear(usuario).toString(), HttpStatus.CREATED);
    }

    // Creación de usuario, by Usuario
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario){
        return new ResponseEntity<Usuario>(usuarioService.registro(usuario), HttpStatus.CREATED);
    }

    // Buscar usuario por correo, by Admin
    @GetMapping("/correo/{correo}")
    public ResponseEntity<String> obtenerUsuarioPorCorreo(@RequestHeader(value = "auth") String token, @PathVariable("correo") String correo){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }
        
        return new ResponseEntity<String>(usuarioService.obtenerPorCorreo(correo).toString(), HttpStatus.OK);
    }

    // Eliminar usuario por id, by Admin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> Eliminar(@RequestHeader(value = "auth") String token, @PathVariable("id") Long id){
        if(!usuarioService.validarRol(token, 2L)){
            return respuestaNegativa();
        }

        return new ResponseEntity<String>(usuarioService.eliminar(id), HttpStatus.OK);
    }

    //Verificar si puede acceder a X modulo XD
    @PostMapping("/verificarAcceso")
    public boolean verificarToken(@RequestHeader("auth") String token, @RequestBody Long nivel){
        return usuarioService.validarRol(token, nivel);
    }

    public ResponseEntity<String> respuestaNegativa(){
        return new ResponseEntity<String>("unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
