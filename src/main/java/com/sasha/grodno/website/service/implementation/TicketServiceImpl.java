package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.repositories.TicketRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl extends CrudServiceJpaImpl<Ticket> implements TicketService {

    @Autowired
    TicketRepository ticketRepository;


    @Override
    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }
}
