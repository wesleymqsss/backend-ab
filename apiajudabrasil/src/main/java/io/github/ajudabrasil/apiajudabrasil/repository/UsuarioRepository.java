package io.github.ajudabrasil.apiajudabrasil.repository;

import io.github.ajudabrasil.apiajudabrasil.DTO.LoginResponse;
import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByTipoPerfil(Integer tipoPerfil);

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT new io.github.ajudabrasil.apiajudabrasil.DTO.LoginResponse(u.email, u.senha, u.id) FROM Usuario u WHERE u.email = :email") // <<-- ')' REMOVIDO DO FINAL
    Optional<LoginResponse> findLoginInfoByEmail(@Param("email") String email);
}
