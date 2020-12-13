package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends CrudServiceJpaImpl<UserInfo> implements UserInfoService {
}
