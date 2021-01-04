package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {

    Airplane findByNumber(Integer number);

    List<Airplane> findAllBySchedules(Schedule schedules);

}
