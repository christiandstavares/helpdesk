package com.christiandstavares.helpdesk.security.services;

import com.christiandstavares.helpdesk.entities.Usuario;
import com.christiandstavares.helpdesk.security.jwt.JwtUserFactory;
import com.christiandstavares.helpdesk.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private UsuarioService usuarioService;

    @Autowired
    public JwtUserDetailsServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            return JwtUserFactory.create(usuario);
        }
    }
}
