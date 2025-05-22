package io.github.ajudabrasil.apiajudabrasil.repository;

import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByTipoPerfil(Integer tipoPerfil);
}
