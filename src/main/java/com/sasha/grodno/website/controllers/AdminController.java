package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Aircraft;
import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.service.iterface.AircraftService;
import com.sasha.grodno.website.service.iterface.RouteService;
import com.sasha.grodno.website.service.iterface.TicketService;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "admin")
public class AdminController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private AircraftService aircraftService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("")
    public String getAdminPage() {
        return "admin";
    }


    // AIRPLANE
    @GetMapping("/airplane")
    public String getAllAircraft(Model model) {
        List<Aircraft> aircrafts = aircraftService.getAll();
        model.addAttribute("aircrafts", aircrafts);
        return "airplane";
    }

    @PostMapping("/airplane/add-airplane")
    public String addAircraft(@ModelAttribute Aircraft aircraft) {
        aircraftService.save(aircraft);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/airplane/{id}/delete")
    String deleteAircraft(@PathVariable Integer id) {
        aircraftService.deleteById(id);
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
    public String addRoute(@ModelAttribute("Route") Route route) {
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
    public String addTicket(@ModelAttribute("Ticket") Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/admin/ticket";
    }

    @GetMapping("/ticket/{id}/delete")
    String deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
        return "redirect:/admin/ticket";
    }




}
