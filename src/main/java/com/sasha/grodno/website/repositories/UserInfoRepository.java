package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByLogin(String login);
    UserInfo findByEmail(String email);
    UserInfo findAllByFirstNameAndLastName(String firstName, String lastName);
    Optional<UserInfo> getByLogin(String login);
}
