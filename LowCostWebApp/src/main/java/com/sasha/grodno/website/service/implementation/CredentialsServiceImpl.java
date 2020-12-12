package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Credentials;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.CredentialsService;
import org.springframework.stereotype.Service;

@Service
public class CredentialsServiceImpl extends CrudServiceJpaImpl<Credentials> implements CredentialsService {
}
