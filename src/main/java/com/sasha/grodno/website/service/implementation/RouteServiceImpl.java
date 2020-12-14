package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.repositories.RouteRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends CrudServiceJpaImpl<Route> implements RouteService {
    @Autowired
    RouteRepository repository;

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Route getByCity(String cityFrom, String cityTo) {
        return repository.getByCityFromAndCityTo(cityFrom, cityTo);
    }


}
