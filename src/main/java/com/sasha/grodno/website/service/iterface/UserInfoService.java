package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.CrudService;

public interface UserInfoService extends CrudService<UserInfo> {
    UserInfo findByEmail(String email);
    UserInfo findByLogin(String login);
}
