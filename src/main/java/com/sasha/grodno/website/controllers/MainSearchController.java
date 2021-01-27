package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.convert.DateTimeConverter;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.TicketsList;
import com.sasha.grodno.website.service.iterface.RouteService;
import com.sasha.grodno.website.service.iterface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class MainSearchController {

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
                            @RequestParam(value = "passengersCount", required = false) Integer passCount,
                            Model model, HttpSession session) {


        cityTo = (cityTo.equals("") ? null : cityTo);
        cityFrom = (cityFrom.equals("") ? null : cityFrom);
        Date departure = (date.equals("") ? new Date() : new DateTimeConverter().convert(date));
        passCount = (passCount == null ? 1 : passCount);
        session.setAttribute("countTickets", passCount);
        model.addAttribute("results", scheduleService.findSchedule(passCount, departure, cityFrom, cityTo));
        return "result";
    }

    @GetMapping("/main/result/{id}/applyTicket")
    public String applyTicket(@PathVariable Integer id, Model model, HttpSession session) {
        Integer countTickets = (Integer) session.getAttribute("countTickets");
        List<Ticket> ticketList = IntStream
                .range(0, countTickets)
                .mapToObj(i -> new Ticket())
                .collect(Collectors.toList());
        model.addAttribute("schedule", scheduleService.getById(id));
        model.addAttribute("ticketList", new TicketsList(ticketList));
        return "applyTickets";
    }

    @PostMapping("main/result/{id}/applyTicket")
    public String checkTickets(){
        return null;
    }

}
