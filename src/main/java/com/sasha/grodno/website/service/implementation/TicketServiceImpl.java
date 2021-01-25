package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.repositories.TicketRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl extends CrudServiceJpaImpl<Ticket> implements TicketService {

    @Autowired
    TicketRepository ticketRepository;


    @Override
    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> findByUser(UserInfo user) {
        List<Ticket> tickets = ticketRepository.findAllByUserDetailTicket_IdUserInfo(user);
        return tickets;
    }
}
