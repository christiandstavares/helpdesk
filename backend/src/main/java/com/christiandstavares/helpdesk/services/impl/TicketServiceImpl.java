package com.christiandstavares.helpdesk.services.impl;

import com.christiandstavares.helpdesk.entities.AlteracaoStatus;
import com.christiandstavares.helpdesk.entities.Ticket;
import com.christiandstavares.helpdesk.repositories.AlteracaoStatusRepository;
import com.christiandstavares.helpdesk.repositories.TicketRepository;
import com.christiandstavares.helpdesk.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    private AlteracaoStatusRepository alteracaoStatusRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, AlteracaoStatusRepository alteracaoStatusRepository) {
        this.ticketRepository = ticketRepository;
        this.alteracaoStatusRepository = alteracaoStatusRepository;
    }

    @Override
    public Ticket salvar(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket buscarPorId(String id) {
        return ticketRepository.findOne(id);
    }

    @Override
    public void excluir(String id) {
        ticketRepository.delete(id);
    }

    @Override
    public Iterable<Ticket> buscarTodos() {
        return ticketRepository.findAll();
    }

    @Override
    public Page<Ticket> buscarTodosComPaginacao(int page, int limit) {
        Pageable pageable = new PageRequest(page, limit);
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Page<Ticket> buscarTodosPorNumero(int page, int limit, Integer numero) {
        Pageable pageable = new PageRequest(page, limit);
        return ticketRepository.findByNumero(numero, pageable);
    }

    @Override
    public Page<Ticket> buscarTodosPorUsuario(int page, int limit, String idUsuario) {
        Pageable pageable = new PageRequest(page, limit);
        return ticketRepository.findByUsuarioIdOrderByDataAberturaDesc(idUsuario, pageable);
    }

    @Override
    public Page<Ticket> buscarTodosPorFiltro(int page, int limit, String titulo, String status, String prioridade) {
        Pageable pageable = new PageRequest(page, limit);
        return ticketRepository.findByTituloIgnoreCaseContainingAndStatusAndPrioridadeOrderByDataAberturaDesc(titulo, status, prioridade, pageable);
    }

    @Override
    public Page<Ticket> buscarTodosPorFiltroEUsuario(int page, int limit, String titulo, String status, String prioridade, String idUsuario) {
        Pageable pageable = new PageRequest(page, limit);
        return ticketRepository.findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndUsuarioIdOrderByDataAberturaDesc(titulo, status, prioridade, idUsuario, pageable);
    }

    @Override
    public Page<Ticket> buscarTodosPorFiltroETecnico(int page, int limit, String titulo, String status, String prioridade, String idTecnico) {
        Pageable pageable = new PageRequest(page, limit);
        return ticketRepository.findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndTecnicoIdOrderByDataAberturaDesc(titulo, status, prioridade, idTecnico, pageable);
    }

    @Override
    public AlteracaoStatus salvarAlteracaoStatus(AlteracaoStatus alteracaoStatus) {
        return alteracaoStatusRepository.save(alteracaoStatus);
    }

    @Override
    public Iterable<AlteracaoStatus> buscarAlteracoesPorTicket(String idTicket) {
        return alteracaoStatusRepository.findByTicketIdOrderByDataAlteracaoDesc(idTicket);
    }
}
