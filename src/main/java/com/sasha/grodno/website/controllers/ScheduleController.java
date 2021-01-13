package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.convert.DateTimeConverter;
import com.sasha.grodno.website.service.iterface.RouteService;
import com.sasha.grodno.website.service.iterface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    RouteService routeService;

    @GetMapping("/main")
    public String get(Model model) {
        Date now = new Date();
        model.addAttribute("dateNow", now);
        return "main";
    }


    @GetMapping("/main/result")
    public String getResult(@RequestParam(value = "cityFrom", required = false) String cityFrom,
                            @RequestParam(value = "cityTo", required = false) String cityTo,
                            @RequestParam(value = "startFlight", required = false) String date,
                            @RequestParam(value = "passengersCount", required = false) Integer passCount, Model model) {


        cityTo = (cityTo.equals("") ? null : cityTo);
        cityFrom = (cityFrom.equals("") ? null : cityFrom);
        Date departure = (date.equals("") ? new Date() : new DateTimeConverter().convert(date));
        passCount = (passCount == null ? 1: passCount);
        model.addAttribute("result", scheduleService.findSchedule(passCount, departure, cityFrom,cityTo));
        return "result";
    }
}
