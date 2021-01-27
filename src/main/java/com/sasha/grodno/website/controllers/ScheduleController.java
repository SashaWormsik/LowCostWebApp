package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.convert.DateTimeConverter;
import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.service.iterface.AirplaneService;
import com.sasha.grodno.website.service.iterface.RouteService;
import com.sasha.grodno.website.service.iterface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class ScheduleController {

    @Autowired
    private RouteService routeService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private AirplaneService airplaneService;


    //SCHEDULE
    @GetMapping("/admin/schedule")
    public String getAllSchedule(Model model) {
        model.addAttribute("airplanes", airplaneService.getAll());
        model.addAttribute("routes", routeService.getAll());
        model.addAttribute("schedules", scheduleService.getAll());
        return "schedule";
    }

    @GetMapping("/admin/schedule/{id}/delete")
    String deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteById(id);
        return "redirect:/admin/schedule";
    }

    @PostMapping("/admin/schedule/add-schedule")
    public String addSchedule(@ModelAttribute Route route, @ModelAttribute Airplane airplane,
                              @RequestParam String departure, @RequestParam String arrival) {

        Date dateDeparture = new DateTimeConverter().convert(departure);
        Date dateArrival = new DateTimeConverter().convert(arrival);
        Integer place = airplane.getNumberOfSeats();
        Schedule schedule = new Schedule(null, dateDeparture, dateArrival, place, route.getPrice() ,airplane, route, null);
        scheduleService.save(schedule);
        return "redirect:/admin/schedule";
    }


    @GetMapping("/admin/schedule/{id}/edit")
    public String getScheduleForEdit(@PathVariable Integer id, RedirectAttributes red) {
        Schedule editSchedule = scheduleService.getById(id);
        red.addFlashAttribute("editSchedule", editSchedule);
        return "redirect:/admin/schedule";
    }

    @PostMapping("/admin/schedule/{id}/update")
    public String editSchedule(@RequestParam Route route, @RequestParam Airplane airplane,
                               @RequestParam String departure, @RequestParam String arrival,
                               @PathVariable Integer id) {
        Date dateDeparture = new DateTimeConverter().convert(departure);
        Date dateArrival = new DateTimeConverter().convert(arrival);
        Schedule editSchedule = new Schedule(id, dateDeparture, dateArrival,null,null ,airplane, route, null);
        scheduleService.updateScheduleById(editSchedule, id);
        return "redirect:/admin/schedule";
    }
}
