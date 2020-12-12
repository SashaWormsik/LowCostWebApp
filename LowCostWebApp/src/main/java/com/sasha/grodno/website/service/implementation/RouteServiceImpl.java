package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends CrudServiceJpaImpl<Route> implements RouteService {
}
