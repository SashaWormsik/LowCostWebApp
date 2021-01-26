package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.repositories.TicketRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl extends CrudServiceJpaImpl<Ticket> implements TicketService {

    static private final Integer SIZE = 5;

    @Autowired
    TicketRepository ticketRepository;


    @Override
    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Page<Ticket> getAllPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        return ticketRepository.findAll(pageable);
    }


    @Override
    public Page<Ticket> getTicketsPageByUser(UserInfo userInfo, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        return ticketRepository.findAllByIdUserInfo(userInfo, pageable);
    }

    @Override
    public Page<Ticket> getTicketsPageByUserId(Integer id, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        return ticketRepository.findAllByIdUserInfo_Id(id, pageable);
    }
}
