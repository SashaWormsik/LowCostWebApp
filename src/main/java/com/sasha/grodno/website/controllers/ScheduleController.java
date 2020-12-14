package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Aircraft;
import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.repositories.RouteRepository;
import com.sasha.grodno.website.service.convert.DateTimeConverter;
import com.sasha.grodno.website.service.implementation.RouteServiceImpl;
import com.sasha.grodno.website.service.iterface.RouteService;
import com.sasha.grodno.website.service.iterface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    RouteService routeService;

    @GetMapping("/mainPage")
    public String get() {
        return "mainPage";
    }


    @GetMapping("/mainPage/get-result")
    public String getResult(@RequestParam(value = "cityFrom", required = false, defaultValue = "") String cityFrom,
                            @RequestParam(value = "cityTO", required = false, defaultValue = "") String cityTo,
                            @RequestParam(value = "startFlight", required = false) String date, Model model) {
        Date startFlight = (Date) new DateTimeConverter().convert(date);
        List<Schedule> result = scheduleService.findAll(cityFrom, cityTo, startFlight);
        model.addAttribute("result", result);
        return "result";
    }
}
