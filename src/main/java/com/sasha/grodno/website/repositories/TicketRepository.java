package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Page<Ticket> findAllByIdUserInfo(UserInfo idUserInfo, Pageable pageable);
    Page<Ticket> findAllByIdUserInfo_Id(Integer idUserInfo_id, Pageable pageable);
}
