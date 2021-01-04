package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.repositories.AirplaneRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneServiceImpl extends CrudServiceJpaImpl<Airplane> implements AirplaneService {

    @Autowired
    public AirplaneRepository repo;

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void updateAirplaneById(Airplane airplane, Integer id) {
        Airplane airplaneForUpdate = repo.getOne(id);
        airplaneForUpdate.setModel(airplane.getModel());
        airplaneForUpdate.setNumber(airplane.getNumber());
        airplaneForUpdate.setNumberOfSeats(airplane.getNumberOfSeats());
        repo.save(airplaneForUpdate);
    }
}
