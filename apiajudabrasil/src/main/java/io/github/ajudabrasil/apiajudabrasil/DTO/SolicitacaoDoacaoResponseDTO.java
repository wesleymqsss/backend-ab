package io.github.ajudabrasil.apiajudabrasil.DTO;

import java.time.LocalDateTime;

public class SolicitacaoDoacaoResponseDTO {
    private String id;
    private String solicitante;
    private String tipoSolicitacao;
    private LocalDateTime dataSolicitacao;
    private UsuarioResponseDTO usuario;

    public SolicitacaoDoacaoResponseDTO(String id, String solicitante, String tipoSolicitacao, LocalDateTime dataSolicitacao, UsuarioResponseDTO usuario) {
        this.dataSolicitacao = dataSolicitacao;
        this.id = id;
        this.solicitante = solicitante;
        this.tipoSolicitacao = tipoSolicitacao;
        this.usuario = usuario;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(String tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }
}
