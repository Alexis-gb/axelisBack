package co.com.axelis.axelisBack.resource;

import org.springframework.web.bind.annotation.RestController;

import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import co.com.axelis.axelisBack.utils.JWTUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthResource {
    private final UsuarioServiceImplement usuarioService;
    
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioService.verificarCredenciales(usuario);

        if(usuarioLogueado != null){
            String token = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getCorreo());
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("unathorized", HttpStatus.UNAUTHORIZED);
    }
}
