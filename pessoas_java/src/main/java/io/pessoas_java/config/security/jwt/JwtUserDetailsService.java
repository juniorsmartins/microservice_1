package io.pessoas_java.config.security.jwt;

import io.pessoas_java.config.security.dto.JwtTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = this.usuarioService.buscarPorUsername(username);
        return new JwtUserDetails(usuario);
    }

    public JwtTokenDto getTokenAuthenticated(String username) {
        var role = this.usuarioService.buscarRolePorUsername(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}

