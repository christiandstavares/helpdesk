package com.christiandstavares.helpdesk.services.impl;

import com.christiandstavares.helpdesk.entities.Usuario;
import com.christiandstavares.helpdesk.repositories.UsuarioRepository;
import com.christiandstavares.helpdesk.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario buscarPorEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public void excluir(String id) {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public Page<Usuario> buscarTodos(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return this.usuarioRepository.findAll(pageable);
    }
}
