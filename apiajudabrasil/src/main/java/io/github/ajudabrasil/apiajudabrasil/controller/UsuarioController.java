package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("teste")
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
        return usuarioRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar (@PathVariable("id") String id){
        usuarioRepository.deleteById(id);
    }
}
