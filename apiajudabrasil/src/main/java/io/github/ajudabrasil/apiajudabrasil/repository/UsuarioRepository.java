package io.github.ajudabrasil.apiajudabrasil.repository;

import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
