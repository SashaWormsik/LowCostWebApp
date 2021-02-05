package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Role;
import com.sasha.grodno.website.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByLogin(String login);
    UserInfo findByEmail(String email);
    Optional<UserInfo> getByLogin(String login);
    Page<UserInfo> findAllByRole(Role role, Pageable pageable);
    UserInfo findByToken(String token);
}
