package br.com.liberdade.JL.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.liberdade.JL.config.JwtTokenProvider;
import br.com.liberdade.JL.dto.UsuarioDTO;
import br.com.liberdade.JL.model.Usuario;
import br.com.liberdade.JL.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return jwtTokenProvider.generateToken(authentication);
    }

    public Usuario register(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        return usuarioRepository.save(usuario);
    }
}
