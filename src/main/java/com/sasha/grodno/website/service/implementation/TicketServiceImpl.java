package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.*;
import com.sasha.grodno.website.repositories.TicketRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl extends CrudServiceJpaImpl<Ticket> implements TicketService {

    static private final Integer SIZE = 5;
    static private final BigDecimal MULTIPLIER = new BigDecimal("0.05");


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

    @Override
    public BigDecimal calculatingTheTotalPrice(List<Ticket> tickets) {
        return tickets.stream().map(Ticket::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Ticket> createTicketFromView(List<Ticket> tickets, UserInfo userInfo, Schedule schedule) {
        for (Ticket ticket : tickets) {
            newTicket(ticket, userInfo, schedule);
        }
        return tickets;
    }

    public void newTicket(Ticket ticket, UserInfo userInfo, Schedule schedule) {
        BigDecimal price = schedule.getPrice().
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
        if (ticket.getLuggage()) {
            price = price.add(price.multiply(MULTIPLIER)).
                    setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
        if (ticket.getPriorityBoarding()) {
            price = price.add(price.multiply(MULTIPLIER)).
                    setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
        if (ticket.getPriorityRegistration()) {
            price = price.add(price.multiply(MULTIPLIER)).
                    setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
        ticket.setPurchaseDate(new Date());
        ticket.setTotalPrice(price);
        ticket.setSchedule(schedule);
        ticket.setIdUserInfo(userInfo);
    }
}
