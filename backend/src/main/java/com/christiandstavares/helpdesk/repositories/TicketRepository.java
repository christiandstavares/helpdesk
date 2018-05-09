package com.christiandstavares.helpdesk.repositories;

import com.christiandstavares.helpdesk.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    Page<Ticket> findByUsuarioIdOrderByDataAberturaDesc(String usuarioId, Pageable pages);

    Page<Ticket> findByTituloIgnoreCaseContainingAndStatusAndPrioridadeOrderByDataAberturaDesc(String titulo, String status, String prioridade, Pageable pages);

    Page<Ticket> findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndUsuarioIdOrderByDataAberturaDesc(String titulo, String status, String prioridade, Pageable pages);

    Page<Ticket> findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndTecnicoIdOrderByDataAberturaDesc(String titulo, String status, String prioridade, Pageable pages);

    Page<Ticket> findByNumero(Integer numero, Pageable pages);
}
