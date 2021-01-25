package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    Route getByCityFromAndCityTo(String cityFrom, String cityTo);

}
