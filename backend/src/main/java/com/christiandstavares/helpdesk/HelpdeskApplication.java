package com.christiandstavares.helpdesk;

import com.christiandstavares.helpdesk.entities.Usuario;
import com.christiandstavares.helpdesk.enums.PerfilEnum;
import com.christiandstavares.helpdesk.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> initUsers(usuarioRepository, passwordEncoder);
	}

	private void initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		if (usuarioRepository.findByEmail("admin@helpdesk.com") == null) {
			Usuario admin = new Usuario();
			admin.setEmail("admin@helpdesk.com");
			admin.setSenha(passwordEncoder.encode("123456"));
			admin.setPerfil(PerfilEnum.ROLE_ADMIN);

			usuarioRepository.save(admin);
		}
	}
}
