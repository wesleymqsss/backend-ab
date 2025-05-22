package io.github.ajudabrasil.apiajudabrasil.controller;

import io.github.ajudabrasil.apiajudabrasil.DTO.LoginRequestDTO;
import io.github.ajudabrasil.apiajudabrasil.DTO.LoginResponse;
import io.github.ajudabrasil.apiajudabrasil.model.Usuario;
import io.github.ajudabrasil.apiajudabrasil.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder =  passwordEncoder;
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario){
        System.out.println("Usuario recebido" + usuario);

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setId(UUID.randomUUID().toString());

        usuarioRepository.save(usuario);
        return usuario;
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginRequestDTO loginRequest){
        System.out.println("Tentativa de login para: " + loginRequest.getEmail());
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(loginRequest.getEmail());

        if(usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if(passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())){
                String token = "TOKEN_JWT_FICTICIO_PARA_" + usuario.getEmail();

                return ResponseEntity.ok(new LoginResponse(token, usuario.getEmail()));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
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
