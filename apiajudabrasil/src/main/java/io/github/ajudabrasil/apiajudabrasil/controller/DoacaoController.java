package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.DTO.DoacaoResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.DTO.UsuarioResponseDTO;
import io.github.ajudabrasil.apiajudabrasil.model.Doacao;
import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import io.github.ajudabrasil.apiajudabrasil.repository.DoacaoRepository;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
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
               new UsuarioResponseDTO(
                       doacao.getUsuario().getId(),
                       doacao.getUsuario().getNome()
               )
       )).toList();
    }


}
