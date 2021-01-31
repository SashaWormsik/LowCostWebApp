package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.CrudService;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService extends CrudService<Ticket> {
    void deleteById(Integer id);

    Page<Ticket> getAllPage(Integer pageNumber);

    Page<Ticket> getTicketsPageByUser(UserInfo userInfo, Integer pageNumber);

    Page<Ticket> getTicketsPageByUserId(Integer id, Integer pageNumber);

    BigDecimal calculatingTheTotalPrice(List<Ticket> tickets);

    List<Ticket>  createTicketFromView(List<Ticket> tickets, UserInfo userInfo, Schedule schedule);
}

