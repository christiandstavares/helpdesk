package com.christiandstavares.helpdesk.entities;

import com.christiandstavares.helpdesk.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class AlteracaoStatus {

    @Id
    private String id;

    private Date dataAlteracao;

    private StatusEnum novoStatus;

    @DBRef
    private Ticket ticket;

    @DBRef
    private Usuario usuario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public StatusEnum getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(StatusEnum novoStatus) {
        this.novoStatus = novoStatus;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
