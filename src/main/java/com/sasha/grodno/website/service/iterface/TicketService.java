package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.service.CrudService;

public interface TicketService extends CrudService<Ticket> {
    void deleteById(Integer id);
}
