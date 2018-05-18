package com.christiandstavares.helpdesk.controllers;

import com.christiandstavares.helpdesk.entities.Usuario;
import com.christiandstavares.helpdesk.response.Resposta;
import com.christiandstavares.helpdesk.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Resposta<Usuario>> cadastrarUsuario(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result) {

        Resposta<Usuario> resposta =  new Resposta<>();

        try {
            validarEmailUsuario(usuario, result);

            if (result.hasErrors()) {
                result.getAllErrors().forEach(erro -> resposta.getErros().add(erro.getDefaultMessage()));
                return ResponseEntity.badRequest().body(resposta);
            }

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            Usuario usuarioPersistido = usuarioService.salvar(usuario);
            resposta.setDados(usuarioPersistido);
        } catch (DuplicateKeyException dke) {
            resposta.getErros().add("E-mail já em registrado");
            return ResponseEntity.badRequest().body(resposta);
        } catch (Exception e) {
            resposta.getErros().add(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }

        return ResponseEntity.ok(resposta);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Resposta<Usuario>> alterarUsuario(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result) {

        Resposta<Usuario> resposta =  new Resposta<>();

        try {
            validarIdUsuario(usuario, result);
            validarEmailUsuario(usuario, result);

            if (result.hasErrors()) {
                result.getAllErrors().forEach(erro -> resposta.getErros().add(erro.getDefaultMessage()));
                return ResponseEntity.badRequest().body(resposta);
            }

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            Usuario usuarioPersistido = usuarioService.salvar(usuario);
            resposta.setDados(usuarioPersistido);
        } catch (Exception e) {
            resposta.getErros().add(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Resposta<Usuario>> buscarPorId(@PathVariable String id) {
        Resposta<Usuario> resposta =  new Resposta<>();
        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            resposta.getErros().add("Usuário não encontrado");
            return ResponseEntity.badRequest().body(resposta);
        }

        resposta.setDados(usuario);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Resposta<String>> excluirUsuario(@PathVariable String id) {
        Resposta<String> resposta =  new Resposta<>();
        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            resposta.getErros().add("Usuário não encontrado");
            return ResponseEntity.badRequest().body(resposta);
        }

        usuarioService.excluir(id);
        resposta.setDados("Usuário excluído com sucesso");

        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "{page}/{limit}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Resposta<Page<Usuario>>> buscarUsuarios(@PathVariable int page, @PathVariable int limit) {
        Resposta<Page<Usuario>> resposta =  new Resposta<>();
        Page<Usuario> usuarios = usuarioService.buscarTodos(page, limit);
        resposta.setDados(usuarios);

        return ResponseEntity.ok(resposta);
    }

    private void validarEmailUsuario(Usuario usuario, BindingResult result) {
        if (usuario.getEmail() == null) {
            result.addError(new ObjectError("Usuario", "E-mail não informado"));
        }
    }

    private void validarIdUsuario(Usuario usuario, BindingResult result) {
        if (usuario.getId() == null) {
            result.addError(new ObjectError("Usuario", "Id não informado"));
        }
    }
}
