package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityFromAndRoute_CityToOrderByDepartureAsc(Integer placesAvailable, Date departure, Date departure2, String route_cityFrom, String route_cityTo);

    List<Schedule> findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityFromOrderByDepartureAsc(Integer placesAvailable, Date departure, Date departure2, String route_cityFrom);

    List<Schedule> findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityToOrderByDepartureAsc(Integer placesAvailable, Date departure, Date departure2, String route_cityTo);

    List<Schedule> findByPlacesAvailableAfterAndDepartureBetweenOrderByDeparture(Integer placesAvailable, Date departure, Date departure2);

}


