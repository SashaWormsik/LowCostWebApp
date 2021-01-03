package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.UserDetailTicket;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.UserDetailService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl extends CrudServiceJpaImpl<UserDetailTicket> implements UserDetailService {
}
