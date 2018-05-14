package com.christiandstavares.helpdesk.security.controller;

import com.christiandstavares.helpdesk.entities.Usuario;
import com.christiandstavares.helpdesk.security.jwt.JwtAuthenticationRequest;
import com.christiandstavares.helpdesk.security.jwt.JwtTokenUtil;
import com.christiandstavares.helpdesk.security.model.CurrentUser;
import com.christiandstavares.helpdesk.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    private UsuarioService usuarioService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/api/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getEmail(), jwtAuthenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final Usuario usuario = usuarioService.buscarPorEmail(jwtAuthenticationRequest.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        usuario.setSenha(null);

        return ResponseEntity.ok(new CurrentUser(token, usuario));
    }

    @PostMapping(value = "/api/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        String username = jwtTokenUtil.getUsernameFromToken(token);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            final Usuario usuario = usuarioService.buscarPorEmail(username);
            return ResponseEntity.ok(new CurrentUser(refreshedToken, usuario));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}