package io.github.ajudabrasil.apiajudabrasil.repository;
import io.github.ajudabrasil.apiajudabrasil.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, String> {
    List<Doacao> findByUsuarioId(String usuarioId);
}
