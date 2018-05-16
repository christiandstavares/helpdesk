package com.christiandstavares.helpdesk.services;

import com.christiandstavares.helpdesk.entities.AlteracaoStatus;
import com.christiandstavares.helpdesk.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface TicketService {

    Ticket salvar(Ticket ticket);

    Ticket buscarPorId(String id);

    void excluir(String id);

    Iterable<Ticket> buscarTodos();

    Page<Ticket> buscarTodosComPaginacao(int page, int limit);

    Page<Ticket> buscarTodosPorNumero(int page, int limit, Integer numero);

    Page<Ticket> buscarTodosPorUsuario(int page, int limit, String idUsuario);

    Page<Ticket> buscarTodosPorFiltro(int page, int limit, String titulo, String status, String prioridade);

    Page<Ticket> buscarTodosPorFiltroEUsuario(int page, int limit, String titulo, String status, String prioridade, String idUsuario);

    Page<Ticket> buscarTodosPorFiltroETecnico(int page, int limit, String titulo, String status, String prioridade, String idTecnico);

    AlteracaoStatus salvarAlteracaoStatus(AlteracaoStatus alteracaoStatus);

    Iterable<AlteracaoStatus> buscarAlteracoesPorTicket(String idTicket);
}
