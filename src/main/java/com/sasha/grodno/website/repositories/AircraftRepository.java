package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Aircraft;
import com.sasha.grodno.website.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

    Aircraft findByNumber(Integer number);

    List<Aircraft> findAllBySchedules(Schedule schedules);

}
