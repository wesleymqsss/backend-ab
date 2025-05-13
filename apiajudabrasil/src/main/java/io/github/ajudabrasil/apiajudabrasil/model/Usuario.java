package io.github.ajudabrasil.apiajudabrasil.model;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @Column
    private String id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false)
    private Integer tipoPerfil;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 20)
    private String cpfCnpj;

    @Column(nullable = false, length = 20)
    private String cep;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(length = 255)
    private String tipoDoacao;

    @Column(nullable = false, length = 255)
    private String bairro;

    @Column(nullable = false)
    private Integer numero;

    @Column(length = 300)
    private String referenciaEndereco;

    @Column(length = 2)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String sobreNos;
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getReferenciaEndereco() {
        return referenciaEndereco;
    }

    public void setReferenciaEndereco(String referenciaEndereco) {
        this.referenciaEndereco = referenciaEndereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSobreNos() {
        return sobreNos;
    }

    public void setSobreNos(String sobreNos) {
        this.sobreNos = sobreNos;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoDoacao() {
        return tipoDoacao;
    }

    public void setTipoDoacao(String tipoDoacao) {
        this.tipoDoacao = tipoDoacao;
    }

    public Integer getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Integer tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tipoPerfil=" + tipoPerfil +
                ", nome='" + nome + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", cep='" + cep + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cidade='" + cidade + '\'' +
                ", tipoDoacao='" + tipoDoacao + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero=" + numero +
                ", referenciaEndereco='" + referenciaEndereco + '\'' +
                ", estado='" + estado + '\'' +
                ", sobreNos='" + sobreNos + '\'' +
                '}';
    }
}
