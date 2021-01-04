package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.convert.DateTimeConverter;
import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Schedule;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.service.iterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(path = "admin")
public class AdminController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("")
    public String getAdminPage() {
        return "admin";
    }


    // AIRPLANE
    @GetMapping("/airplane")
    public String getAllAirplane(Model model) {
        List<Airplane> airplanes = airplaneService.getAll();
        model.addAttribute("airplanes", airplanes);
        return "airplane";
    }

    @PostMapping("/airplane/add-airplane")
    public String addAirplane(@ModelAttribute Airplane airplane) {
        airplaneService.save(airplane);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/airplane/{id}/delete")
    public String deleteAirplane(@PathVariable Integer id) {
        airplaneService.deleteById(id);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/airplane/{id}/edit")
    public String getAirplaneForEdit(@PathVariable Integer id, Model model, RedirectAttributes red) {

        Airplane airplane = airplaneService.getById(id);
        red.addFlashAttribute("editAirplane", airplane);
        return "redirect:/admin/airplane";
    }

    @PostMapping("/airplane/{id}/update")
    public String editAirplane(@ModelAttribute Airplane editAirplane) {
        Integer id = editAirplane.getId();
        airplaneService.updateAirplaneById(editAirplane, id);
        return "redirect:/admin/airplane";
    }


    // ROUTE
    @GetMapping("/route")
    public String getAllRoute(Model model) {
        List<Route> routes = routeService.getAll();
        model.addAttribute("routes", routes);
        return "route";
    }

    @PostMapping("/route/add-route")
    public String addRoute(@ModelAttribute Route route) {
        routeService.save(route);
        return "redirect:/admin/route";
    }

    @GetMapping("/route/{id}/delete")
    String deleteRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/admin/route";
    }

    //TICKET
    @GetMapping("/ticket")
    public String getAllTicket(Model model) {
        List<Ticket> tickets = ticketService.getAll();
        model.addAttribute("tickets", tickets);
        return "ticket";
    }

    @PostMapping("/ticket/add-ticket")
    public String addTicket(@ModelAttribute Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/admin/ticket";
    }

    @GetMapping("/ticket/{id}/delete")
    String deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
        return "redirect:/admin/ticket";
    }

    //Schedule
    @GetMapping("/schedule")
    public String getAllSchedule(Model model) {
        model.addAttribute("airplanes", airplaneService.getAll());
        model.addAttribute("routes", routeService.getAll());
        model.addAttribute("schedules", scheduleService.getAll());
        return "schedule";
    }

    @GetMapping("/schedule/{id}/delete")
    String deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteById(id);
        return "redirect:/admin/schedule";
    }

    @PostMapping("/schedule/add-schedule")
    public String addSchedule(@ModelAttribute Route route, @ModelAttribute Airplane airplane,
                              @RequestParam String departure, @RequestParam String arrival) {

        Date dateDeparture = new DateTimeConverter().convert(departure);
        Date dateArrival = new DateTimeConverter().convert(arrival);
        Integer place = airplane.getNumberOfSeats();
        Schedule schedule = new Schedule(null, dateDeparture, dateArrival, place, airplane, route, null);
        scheduleService.save(schedule);
        return "redirect:/admin/schedule";
    }

}
