package io.github.ajudabrasil.apiajudabrasil.repository;

import io.github.ajudabrasil.apiajudabrasil.model.SolicitacaoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoDoacaoRepository extends JpaRepository<SolicitacaoDoacao, String> {
    List<SolicitacaoDoacao> findByUsuarioId(String usuarioId);
}
