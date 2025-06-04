package io.github.ajudabrasil.apiajudabrasil.DTO;

import java.time.LocalDateTime;

public class DoacaoResponseDTO {
    private String id;
    private String doador;
    private String tipoDoacao;
    private LocalDateTime dataDoacao;
    private UsuarioResponseDTO usuario;
    private String statusDoacao;

    public DoacaoResponseDTO(String id, String doador, String tipoDoacao, LocalDateTime dataDoacao, UsuarioResponseDTO usuario) {
        this.id = id;
        this.doador = doador;
        this.tipoDoacao = tipoDoacao;
        this.dataDoacao = dataDoacao;
        this.statusDoacao = statusDoacao;
        this.usuario = usuario;
    }

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

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }
}
