// src/main/java/io/github/ajudabrasil/apiajudabrasil/model/SolicitacaoDoacaoHistorico.java
package io.github.ajudabrasil.apiajudabrasil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacao_doacao_historico")
public class SolicitacaoDoacaoHistorico {
    @Id
    private String id;

    private String solicitante;

    @Column(name = "id_solicitante")
    private String idSolicitante;

    @Column(name = "tipo_solicitacao")
    private String tipoSolicitacao;

    @Column(name = "data_solicitacao")
    private LocalDateTime dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_rejeicao")
    private LocalDateTime dataRejeicao;

    public SolicitacaoDoacaoHistorico() {
    }


    public SolicitacaoDoacaoHistorico(SolicitacaoDoacao solicitacaoDoacao) {
        this.id = solicitacaoDoacao.getId();
        this.solicitante = solicitacaoDoacao.getSolicitante();
        this.idSolicitante = solicitacaoDoacao.getIdSolicitante();
        this.tipoSolicitacao = solicitacaoDoacao.getTipoSolicitacao();
        this.dataSolicitacao = solicitacaoDoacao.getDataSolicitacao();
        this.usuario = solicitacaoDoacao.getUsuario();
        this.dataRejeicao = LocalDateTime.now();
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

    public String getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(String idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(String tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataRejeicao() {
        return dataRejeicao;
    }

    public void setDataRejeicao(LocalDateTime dataRejeicao) {
        this.dataRejeicao = dataRejeicao;
    }
}