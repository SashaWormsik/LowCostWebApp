package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.service.convert.DateTimeConverter;
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

    @GetMapping("/mainPage")
    public String get() {
        return "mainPage";
    }


    @GetMapping("/mainPage/get-result")
    public String getResult(@RequestParam(value = "cityFrom", required = false) String cityFrom,
                            @RequestParam(value = "cityTo", required = false) String cityTo,
                            @RequestParam(value = "startFlight", required = false) String date, Model model) {
        if (cityFrom.equals("")){
            cityFrom = null;
        }
        if (cityTo.equals("")){
            cityTo = null;
        }
        if (date.equals("")){
            date = null;
        }
        Date startFlight = (date != null ? new DateTimeConverter().convert(date) : null);
        List<Schedule> result = scheduleService.findAll(cityFrom, cityTo, startFlight);
        model.addAttribute("result", result);
        return "result";
    }
}
