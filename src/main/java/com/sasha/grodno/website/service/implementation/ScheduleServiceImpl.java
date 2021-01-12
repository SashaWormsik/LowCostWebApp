package com.sasha.grodno.website.service.implementation;


import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.repositories.ScheduleRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl extends CrudServiceJpaImpl<Schedule> implements ScheduleService {

    @Autowired
    ScheduleRepository repo;

    @Override
    public List<Schedule> findAll(String from, String to, Date date) {
        Route rote = new Route(null, from, to, null, null);
        Schedule schedule = new Schedule(null, date, null, null, null, rote, null);
        Example<Schedule> example = Example.of(schedule);
        return repo.findAll(example);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void updateScheduleById(Schedule schedule, Integer id) {
        Schedule scheduleForUpdate = repo.getOne(id);
        scheduleForUpdate.setDeparture(schedule.getDeparture());
        scheduleForUpdate.setArrival(schedule.getArrival());
        scheduleForUpdate.setRoute(schedule.getRoute());
        scheduleForUpdate.setAirplane(schedule.getAirplane());
        repo.save(scheduleForUpdate);
    }

    @Override
    public Schedule getById(Integer id) {
        return repo.findById(id).orElse(null);
    }


    public List<Schedule> findSchedule(Integer placesAvailable, Date departure, Date departure2, String route_cityFrom, String route_cityTo){
        return repo.findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityFromAndRoute_CityTo(placesAvailable, departure, departure2, route_cityFrom, route_cityTo);
    }
}

