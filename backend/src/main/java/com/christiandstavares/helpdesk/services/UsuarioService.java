package com.christiandstavares.helpdesk.services;

import com.christiandstavares.helpdesk.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UsuarioService {

    Usuario buscarPorEmail(String email);

    Usuario salvar(Usuario usuario);

    Usuario buscarPorId(String id);

    void excluir(String id);

    Page<Usuario> buscarTodos(int page, int count);
}
