package io.github.ajudabrasil.apiajudabrasil.DTO;

public class LoginRequestDTO {
    private String email;
    private String senha; // Senha em texto plano enviada pelo usuário

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
