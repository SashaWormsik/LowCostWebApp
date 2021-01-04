package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.repositories.RouteRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteServiceImpl extends CrudServiceJpaImpl<Route> implements RouteService {
    @Autowired
    RouteRepository repo;

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void updateRouteById(Route route, Integer id) {
        Route routeForUpdate = repo.getOne(id);
        routeForUpdate.setCityFrom(route.getCityFrom());
        routeForUpdate.setCityTo(route.getCityTo());
        routeForUpdate.setPrice(route.getPrice());
        repo.save(routeForUpdate);
    }


    @Override
    public Route getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

}

