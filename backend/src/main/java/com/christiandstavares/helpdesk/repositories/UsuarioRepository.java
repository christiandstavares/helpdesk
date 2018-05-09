package com.christiandstavares.helpdesk.repositories;

import com.christiandstavares.helpdesk.entities.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByEmail(String email);
}
