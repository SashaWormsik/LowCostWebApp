package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByLogin(String login);
    UserInfo findAllByFirstNameAndLastName(String firstName, String lastName);
}
