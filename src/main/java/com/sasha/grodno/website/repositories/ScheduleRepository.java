package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Schedule;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}


