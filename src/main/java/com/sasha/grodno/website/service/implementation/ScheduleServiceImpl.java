package com.sasha.grodno.website.service.implementation;


import com.sasha.grodno.website.model.Aircraft;
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
        Schedule schedule = new Schedule(null, date, null, null, rote, null);
        Example<Schedule> example = Example.of(schedule);
        return repo.findAll(example);
    }
}
