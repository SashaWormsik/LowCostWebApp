package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.service.CrudService;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RouteService extends CrudService<Route> {

    void deleteById(Integer id);
    void updateRouteById(Route route, Integer id);
    Page<Route> getRoutesPage(Integer pageNumber);
}
