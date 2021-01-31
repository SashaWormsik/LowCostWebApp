package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByPlacesAvailableGreaterThanEqualAndDepartureBetweenAndRoute_CityFromIgnoreCaseAndRoute_CityToIgnoreCaseOrderByDepartureAsc(Integer placesAvailable, Date departure, Date departure2, String route_cityFrom, String route_cityTo);

    List<Schedule> findByPlacesAvailableGreaterThanEqualAndDepartureBetweenAndRoute_CityFromIgnoreCaseOrderByDepartureAsc(Integer placesAvailable, Date departure, Date departure2, String route_cityFrom);

    List<Schedule> findByPlacesAvailableGreaterThanEqualAndDepartureBetweenAndRoute_CityToIgnoreCaseOrderByDepartureAsc(Integer placesAvailable, Date departure, Date departure2, String route_cityTo);

    List<Schedule> findByPlacesAvailableGreaterThanEqualAndDepartureBetweenOrderByDepartureAsc(Integer placesAvailable, Date departure, Date departure2);

    List<Schedule> findAllByDepartureBetween(Date departure, Date departure2);

}


