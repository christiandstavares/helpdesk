package com.christiandstavares.helpdesk.entities;

import com.christiandstavares.helpdesk.enums.PrioridadeEnum;
import com.christiandstavares.helpdesk.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Ticket {

    @Id
    private String id;

    private Integer numero;

    private Date dataAbertura;

    private String titulo;

    private String descricao;

    private String imagem;

    @DBRef(lazy = true)
    private Usuario usuario;

    @DBRef(lazy = true)
    private Usuario tecnico;

    private StatusEnum status;

    private PrioridadeEnum prioridade;

    @Transient
    private List<AlteracaoStatus> alteracoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public List<AlteracaoStatus> getAlteracoes() {
        return alteracoes;
    }

    public void setAlteracoes(List<AlteracaoStatus> alteracoes) {
        this.alteracoes = alteracoes;
    }
}
