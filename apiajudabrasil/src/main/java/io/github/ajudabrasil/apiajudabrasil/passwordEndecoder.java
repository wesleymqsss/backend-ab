package io.github.ajudabrasil.apiajudabrasil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordEndecoder {
    public static String gerar(String senha) {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia.");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }
}
