package com.christiandstavares.helpdesk.services;

import com.christiandstavares.helpdesk.entities.Usuario;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UsuarioService {

    Usuario buscarPorEmail(String email);

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorId(String id);

    void excluir(String id);

    Page<Usuario> buscarTodos(int page, int count);
}
