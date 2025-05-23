package io.github.ajudabrasil.apiajudabrasil.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Doacao {

    @Id
    private String id;

    @Column(name = "status_doacao")
    private String statusDoacao;

    private String doador;

    private String tipoDoacao;
    private LocalDateTime dataDoacao;

    public String getStatusDoacao() {
        return statusDoacao;
    }

    public void setStatusDoacao(String statusDoacao) {
        this.statusDoacao = statusDoacao;
    }

    public LocalDateTime getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDateTime dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public String getDoador() {
        return doador;
    }

    public void setDoador(String doador) {
        this.doador = doador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDoacao() {
        return tipoDoacao;
    }

    public void setTipoDoacao(String tipoDoacao) {
        this.tipoDoacao = tipoDoacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Doacao() {
        this.id = UUID.randomUUID().toString();
    }
}
