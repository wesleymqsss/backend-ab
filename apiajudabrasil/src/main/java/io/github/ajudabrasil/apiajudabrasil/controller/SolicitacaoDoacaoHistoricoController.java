package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.model.SolicitacaoDoacaoHistorico;
import io.github.ajudabrasil.apiajudabrasil.repository.SolicitacaoDoacaoHistoricoRepository;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("historico")
public class SolicitacaoDoacaoHistoricoController {
    private final SolicitacaoDoacaoHistoricoRepository solicitacaoDoacaoHistoricoRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitacaoDoacaoHistoricoController(SolicitacaoDoacaoHistoricoRepository solicitacaoDoacaoHistoricoRepository, UsuarioRepository usuarioRepository) {
        this.solicitacaoDoacaoHistoricoRepository = solicitacaoDoacaoHistoricoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("solicitacao/{id}")
    public SolicitacaoDoacaoHistorico buscarHistoricoPorId(@PathVariable("id") String id){
        return solicitacaoDoacaoHistoricoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Histórico da solicitação não encontrado"));
    }

    @GetMapping("solicitante/{idSolicitante}")
    public List<SolicitacaoDoacaoHistorico> historicoPorIdSolicitante(@PathVariable("idSolicitante") String idSolicitante){
        usuarioRepository.findById(idSolicitante)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitante não encontrado com o ID: " + idSolicitante));

        return solicitacaoDoacaoHistoricoRepository.findByIdSolicitante(idSolicitante);
    }

    @GetMapping("beneficiado/{usuarioId}")
    public List<SolicitacaoDoacaoHistorico> historicoPorUsuarioBeneficiado(@PathVariable("usuarioId") String usuarioId){
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário beneficiado não encontrado com o ID: " + usuarioId));

        return solicitacaoDoacaoHistoricoRepository.findByUsuarioId(usuarioId);
    }

    @GetMapping
    public List<SolicitacaoDoacaoHistorico> buscarTodosHistoricos(){
        return solicitacaoDoacaoHistoricoRepository.findAll();
    }
}