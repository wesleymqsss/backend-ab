package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.DTO.DoacaoResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.DTO.SolicitacaoDoacaoResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.DTO.UsuarioResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.model.SolicitacaoDoacao;
import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import io.github.ajudabrasil.apiajudabrasil.repository.SolicitacaoDoacaoRepository;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("solicitacao")
public class SolicitacaoDoacaoController {

    private final SolicitacaoDoacaoRepository solicitacaoDoacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitacaoDoacaoController (SolicitacaoDoacaoRepository solicitacaoDoacaoRepository, UsuarioRepository usuarioRepository){
        this.solicitacaoDoacaoRepository = solicitacaoDoacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @PostMapping("{id}")
    public SolicitacaoDoacao createSolicitacao(@PathVariable("id") String idUsuario, @org.jetbrains.annotations.NotNull @RequestBody SolicitacaoDoacao solicitacaoDoacao){
        System.out.println("Doacao recebida: " + solicitacaoDoacao);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(()-> new RuntimeException("usuario nao encontrado: "+ idUsuario));

        solicitacaoDoacao.setId(UUID.randomUUID().toString());
        solicitacaoDoacao.setUsuario(usuario);

        return solicitacaoDoacaoRepository.save(solicitacaoDoacao);
    }

    @GetMapping("{id}")
    public List<SolicitacaoDoacaoResponseDTO> solicitacaoPorId(@PathVariable("id") String id){
        List<SolicitacaoDoacao> solicitacoes = solicitacaoDoacaoRepository.findByUsuarioId(id);

        return solicitacoes.stream().map(solicitacaoDoacao -> new SolicitacaoDoacaoResponseDTO(
                solicitacaoDoacao.getId(),
                solicitacaoDoacao.getSolicitante(),
                solicitacaoDoacao.getTipoSolicitacao(),
                solicitacaoDoacao.getDataSolicitacao(),
                new UsuarioResponseDTO(
                        solicitacaoDoacao.getUsuario().getId(),
                        solicitacaoDoacao.getUsuario().getNome()
                )
        )).toList();
    }
}
