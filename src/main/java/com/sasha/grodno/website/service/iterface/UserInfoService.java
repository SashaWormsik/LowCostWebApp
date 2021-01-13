package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoService extends CrudService<UserInfo>, UserDetailsService {
    UserInfo findByEmail(String email);
    UserInfo findByLogin(String login);
}
