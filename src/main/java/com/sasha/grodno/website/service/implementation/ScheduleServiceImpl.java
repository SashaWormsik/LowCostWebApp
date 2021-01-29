package com.sasha.grodno.website.service.implementation;


import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.repositories.ScheduleRepository;
import com.sasha.grodno.website.service.CrudServiceJpaImpl;
import com.sasha.grodno.website.service.iterface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ScheduleServiceImpl extends CrudServiceJpaImpl<Schedule> implements ScheduleService {

    private static final Integer DAYS_PLUS = 5;
    private static final Integer MONTH_PLUS = 2;
    static private final BigDecimal MULTIPLIER = new BigDecimal("0.016");

    @Autowired
    private ScheduleRepository repo;



    public List<Schedule> findAll(String from, String to, Date date) {
        Route rote = new Route(null, from, to, null, null);
        Schedule schedule = new Schedule(null, date, null, null, null, null, rote, null);
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


    @Override
    public List<Schedule> findSchedule(Integer placesAvailable, Date departure, String cityFrom, String cityTo) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(departure);
        calendar.add(Calendar.DATE, DAYS_PLUS);
        Date departure2 = calendar.getTime();

        if (cityFrom != null && cityTo != null) {
            return repo.findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityFromAndRoute_CityToOrderByDepartureAsc
                    (placesAvailable, departure, departure2, cityFrom, cityTo);
        } else if (cityFrom != null) {
            return repo.findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityFromOrderByDepartureAsc
                    (placesAvailable, departure, departure2, cityFrom);
        } else if (cityTo != null) {
            return repo.findByPlacesAvailableAfterAndDepartureBetweenAndRoute_CityToOrderByDepartureAsc
                    (placesAvailable, departure, departure2, cityTo);
        } else {
            return repo.findByPlacesAvailableAfterAndDepartureBetweenOrderByDepartureAsc
                    (placesAvailable, departure, departure2);
        }
    }

    @Override
    public void updatePlacesAvailable(List<Ticket> tickets) {
        Schedule schedule = tickets.get(0).getSchedule();
        schedule.setPlacesAvailable(schedule.getPlacesAvailable()-tickets.size());
        repo.save(schedule);
    }

    @Override
    @Scheduled(cron = "0 0 0 ? * *")
    public void updatePriceInSchedule() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, MONTH_PLUS);
        Date plusTwoMonths = calendar.getTime();
        List<Schedule> schedules = repo.findAllByDepartureBetween(now, plusTwoMonths);
        for (Schedule schedule : schedules) {
            schedule.
                    setPrice(schedule.getRoute().getPrice().
                            multiply(MULTIPLIER).add(schedule.getPrice()).
                            setScale(2, BigDecimal.ROUND_HALF_EVEN));
        }
    }
}

