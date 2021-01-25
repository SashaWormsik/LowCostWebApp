package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Role;
import com.sasha.grodno.website.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByLogin(String login);
    UserInfo findByEmail(String email);
    UserInfo findAllByFirstNameAndLastName(String firstName, String lastName);
    Optional<UserInfo> getByLogin(String login);
    List<UserInfo> findAllByRole(Role role);
}
