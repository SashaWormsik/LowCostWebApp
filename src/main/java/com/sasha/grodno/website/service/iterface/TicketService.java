package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.CrudService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketService extends CrudService<Ticket> {
    void deleteById(Integer id);

    Page<Ticket> getAllPage(Integer pageNumber);

    Page<Ticket> getTicketsPageByUser(UserInfo userInfo, Integer pageNumber);

    Page<Ticket> getTicketsPageByUserId(Integer id, Integer pageNumber);
}

