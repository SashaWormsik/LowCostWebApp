package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.service.CrudService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

public interface ScheduleService extends CrudService<Schedule> {

    void deleteById(Integer id);

    void updateScheduleById(Schedule schedule, Integer id);

    List<Schedule> findSchedule(Integer placesAvailable, Date departure, String route_cityFrom, String route_cityTo);

    void updatePlacesAvailable(List<Ticket> tickets);

    @Scheduled(cron = "0 0 0 ? * *")
    void updatePriceInSchedule();
}
