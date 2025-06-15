package io.github.ajudabrasil.apiajudabrasil.repository;

import io.github.ajudabrasil.apiajudabrasil.model.SolicitacaoDoacaoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoDoacaoHistoricoRepository extends JpaRepository <SolicitacaoDoacaoHistorico, String> {
    List<SolicitacaoDoacaoHistorico> findByIdSolicitante(String idSolicitante);

    List<SolicitacaoDoacaoHistorico> findByUsuarioId(String usuarioId);
}
