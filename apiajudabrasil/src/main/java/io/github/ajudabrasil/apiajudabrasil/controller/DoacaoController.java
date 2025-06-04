package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.DTO.AtualizarStatusDoacaoDTO;
import io.github.ajudabrasil.apiajudabrasil.DTO.DoacaoResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.DTO.UsuarioResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.model.Doacao;
import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import io.github.ajudabrasil.apiajudabrasil.repository.DoacaoRepository;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("doacao")
public class DoacaoController {

    private final DoacaoRepository doacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public DoacaoController(DoacaoRepository doacaoRepository, UsuarioRepository usuarioRepository) {
        this.doacaoRepository = doacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("{id}")
    public Doacao createDoacao(@PathVariable("id") String idUsuario, @org.jetbrains.annotations.NotNull @RequestBody Doacao doacao){
        System.out.println("Doacao recebida: "+ doacao);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(()-> new RuntimeException("usuario nao encontrado: "+ idUsuario));

        doacao.setId(UUID.randomUUID().toString());
        doacao.setUsuario(usuario);
        doacao.setStatusDoacao("pendente");

        return doacaoRepository.save(doacao);
    }

    @GetMapping("/usuario/{id}")
    public List<DoacaoResponseDTO> doacaoPorId(@PathVariable("id") String id){
       List<Doacao> doacoes = doacaoRepository.findByUsuarioId(id);

       return doacoes.stream().map(doacao -> new DoacaoResponseDTO(
               doacao.getId(),
               doacao.getDoador(),
               doacao.getTipoDoacao(),
               doacao.getDataDoacao(),
               doacao.getStatusDoacao(),
               new UsuarioResponseDTO(
                       doacao.getUsuario().getId(),
                       doacao.getUsuario().getNome()
               )
       )).toList();
    }


    @PatchMapping("/{idDoacao}/status")
    public ResponseEntity<DoacaoResponseDTO> atualizarStatusDoacao(
            @PathVariable("idDoacao") String idDoacao,
            @RequestBody AtualizarStatusDoacaoDTO statusUpdateRequest) {

        Doacao doacaoExistente = doacaoRepository.findById(idDoacao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doacao não encontrada para o id: " + idDoacao));

        String novoStatus = statusUpdateRequest.getStatus();
        if(novoStatus == null || novoStatus.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status não pode ser vazio.");
        }

        doacaoExistente.setStatusDoacao(novoStatus);

        Doacao doacaoSalva = doacaoRepository.save(doacaoExistente);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(
                doacaoSalva.getUsuario().getId(),
                doacaoSalva.getUsuario().getNome()
        );

        DoacaoResponseDTO responseDTO =  new DoacaoResponseDTO(
                doacaoSalva.getId(),
                doacaoSalva.getDoador(),
                doacaoSalva.getTipoDoacao(),
                doacaoSalva.getDataDoacao(),
                doacaoSalva.getStatusDoacao(),
                usuarioResponseDTO
        );

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDoacao(@PathVariable("{id}") String idDoacao){
        if(!doacaoRepository.existsById(idDoacao)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doacao nao encontrada");
        }

        doacaoRepository.deleteById(idDoacao);
        return ResponseEntity.noContent().build();
    }
}
