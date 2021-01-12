package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Schedule;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(value = " SELECT sc.* FROM schedule AS sc" +
            " JOIN route AS r ON sc.route_id=r.id" +
            " WHERE sc.places_available > ?4 AND sc.departure >= ?3" +
            " AND r.city_from LIKE ?1 AND r.city_to LIKE ?2" +
            " ORDER BY sc.departure ASC" +
            " LIMIT 2 OFFSET 0", nativeQuery = true)
    List<Schedule> findSchedule(String cityFrom, String cityTo, String date, String seats);

    List<Schedule> findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityFromAndRoute_CityTo(Integer placesAvailable, Date departure, Date departure2, String route_cityFrom, String route_cityTo);

}


