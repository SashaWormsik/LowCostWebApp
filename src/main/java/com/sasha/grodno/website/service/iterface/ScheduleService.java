package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.service.CrudService;

import java.util.Date;
import java.util.List;

public interface ScheduleService extends CrudService<Schedule> {

    List<Schedule> findAll(String from, String to, Date date);

    void deleteById(Integer id);

    void updateScheduleById(Schedule schedule, Integer id);

    List<Schedule> findSchedule(Integer placesAvailable, Date departure, Date departure2, String route_cityFrom, String route_cityTo);

}
