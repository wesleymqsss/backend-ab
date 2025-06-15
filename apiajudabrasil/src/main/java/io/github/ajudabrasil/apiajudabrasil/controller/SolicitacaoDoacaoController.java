package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.DTO.SolicitacaoDoacaoResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.DTO.UsuarioResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.model.SolicitacaoDoacao;
import io.github.ajudabrasil.apiajudabrasil.model.SolicitacaoDoacaoHistorico;
import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import io.github.ajudabrasil.apiajudabrasil.repository.SolicitacaoDoacaoHistoricoRepository;
import io.github.ajudabrasil.apiajudabrasil.repository.SolicitacaoDoacaoRepository;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("solicitacao")
public class SolicitacaoDoacaoController {

    private final SolicitacaoDoacaoRepository solicitacaoDoacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SolicitacaoDoacaoHistoricoRepository solicitacaoDoacaoHistoricoRepository;

    public SolicitacaoDoacaoController (SolicitacaoDoacaoRepository solicitacaoDoacaoRepository, UsuarioRepository usuarioRepository, SolicitacaoDoacaoHistoricoRepository solicitacaoDoacaoHistoricoRepository){
        this.solicitacaoDoacaoRepository = solicitacaoDoacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.solicitacaoDoacaoHistoricoRepository = solicitacaoDoacaoHistoricoRepository;
    }

    @PostMapping("{idBeneficiado}")
    public SolicitacaoDoacao createSolicitacao(
            @PathVariable("idBeneficiado") String idUsuarioBeneficiado,
            @RequestBody SolicitacaoDoacao solicitacaoDoacao
    ) {
        System.out.println("Doacao recebida: " + solicitacaoDoacao);


        Usuario usuarioBeneficiado = usuarioRepository.findById(idUsuarioBeneficiado)
                .orElseThrow(()-> new RuntimeException("Usuário beneficiado não encontrado: " + idUsuarioBeneficiado));

        if (solicitacaoDoacao.getIdSolicitante() == null || solicitacaoDoacao.getIdSolicitante().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do solicitante é obrigatório.");
        }
        Usuario solicitante = usuarioRepository.findById(solicitacaoDoacao.getIdSolicitante())
                .orElseThrow(() -> new RuntimeException("Solicitante não encontrado: " + solicitacaoDoacao.getIdSolicitante()));

        solicitacaoDoacao.setId(UUID.randomUUID().toString());
        solicitacaoDoacao.setUsuario(usuarioBeneficiado);


        return solicitacaoDoacaoRepository.save(solicitacaoDoacao);
    }

    @GetMapping("{id}")
    public List<SolicitacaoDoacaoResponseDTO> solicitacaoPorId(@PathVariable("id") String id){
        List<SolicitacaoDoacao> solicitacoes = solicitacaoDoacaoRepository.findByUsuarioId(id);

        return solicitacoes.stream().map(solicitacaoDoacao -> new SolicitacaoDoacaoResponseDTO(
                solicitacaoDoacao.getId(),
                solicitacaoDoacao.getSolicitante(),
                solicitacaoDoacao.getIdSolicitante(),
                solicitacaoDoacao.getTipoSolicitacao(),
                solicitacaoDoacao.getDataSolicitacao(),
                new UsuarioResponseDTO(
                        solicitacaoDoacao.getUsuario().getId(),
                        solicitacaoDoacao.getUsuario().getNome()
                )
        )).toList();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable("id") String idSolicitacao) {

        SolicitacaoDoacao solicitacao = solicitacaoDoacaoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doacao nao encontrada"));

        System.out.println("ID Solicitante na Solicitacao original: " + solicitacao.getIdSolicitante()); // Adicione esta linha

        SolicitacaoDoacaoHistorico historico = new SolicitacaoDoacaoHistorico(solicitacao);
        solicitacaoDoacaoHistoricoRepository.save(historico);


        solicitacaoDoacaoRepository.deleteById(idSolicitacao);
        return ResponseEntity.noContent().build();
    }
}