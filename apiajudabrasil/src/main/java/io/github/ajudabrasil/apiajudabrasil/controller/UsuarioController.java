package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario){
        System.out.println("Usuario recebido" + usuario);
        usuario.setId(UUID.randomUUID().toString());

        usuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @GetMapping("{id}")
    public Usuario obterPorId(@PathVariable("id") String id){
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + id));
    }

    @GetMapping("/por-tipo-perfil/{tipo}")
    public List<Usuario> getUsuarioPorPerfil(@PathVariable("tipo") Integer tipoPerfil){
        return usuarioRepository.findByTipoPerfil(tipoPerfil);
    }

    @DeleteMapping("{id}")
    public void deletar (@PathVariable("id") String id){
        usuarioRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id, @RequestBody Usuario usuario){
        usuario.setId(id);
        usuarioRepository.save(usuario);
    }
}
