package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserDetail;
import com.sasha.grodno.website.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    List<UserDetail> findAllByUser (UserInfo user);
    UserDetail findByPassportId(String passportId);
    List<UserDetail> findAllByPassengerFirstNameAndAndPassengerLastName(String firstName, String lastName);
    List<UserDetail> findAllByTicket(Ticket ticket);
}
