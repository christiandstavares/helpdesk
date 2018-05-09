package com.christiandstavares.helpdesk.repositories;

import com.christiandstavares.helpdesk.entities.AlteracaoStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlteracaoStatusRepository extends MongoRepository<AlteracaoStatus, String> {

    Iterable<AlteracaoStatus> findByTicketIdOrderByDataAlteracaoDesc(String ticketId);
}
