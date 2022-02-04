package co.com.axelis.axelisBack.resource;

import org.springframework.web.bind.annotation.RestController;

import co.com.axelis.axelisBack.models.Response;
import co.com.axelis.axelisBack.models.Usuario;
import co.com.axelis.axelisBack.services.implementations.UsuarioServiceImplement;
import co.com.axelis.axelisBack.utils.JWTUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class AuthResource {
    private final UsuarioServiceImplement usuarioService;
    
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<Response> login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioService.verificarCredenciales(usuario);

        if(usuarioLogueado != null){
            String token = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getCorreo());
            return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("token", token))
                    .message("Logueado correctamente")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
            );
        }
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("No se ha podido loguear")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build()
            );
    }
    
}
