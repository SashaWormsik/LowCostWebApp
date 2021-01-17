package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.CrudService;

import java.util.List;

public interface TicketService extends CrudService<Ticket> {
    void deleteById(Integer id);

    List<Ticket> findByUser(UserInfo user);
}
