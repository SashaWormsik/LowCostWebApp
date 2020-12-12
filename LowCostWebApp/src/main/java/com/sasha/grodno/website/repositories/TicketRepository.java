package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
