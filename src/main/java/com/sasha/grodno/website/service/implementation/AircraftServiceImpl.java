package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Aircraft;
import com.sasha.grodno.website.repositories.AircraftRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AircraftServiceImpl extends CrudServiceJpaImpl<Aircraft> implements AircraftService {

    @Autowired
    public AircraftRepository repo;

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
