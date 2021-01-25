package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllByUserDetailTicket_IdUserInfo(UserInfo userDetailTicket_idUserInfo);
    List<Ticket> findAllByUserDetailTicket_IdUserInfo_IdOrderBySchedule(Integer userDetailTicket_idUserInfo_id);
}
