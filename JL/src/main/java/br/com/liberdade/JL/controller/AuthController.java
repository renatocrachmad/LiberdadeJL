package br.com.liberdade.JL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.liberdade.JL.dto.LoginDTO;
import br.com.liberdade.JL.dto.UsuarioDTO;
import br.com.liberdade.JL.model.Usuario;
import br.com.liberdade.JL.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO.getLogin(), loginDTO.getSenha());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(authService.register(usuarioDTO));
    }
}
