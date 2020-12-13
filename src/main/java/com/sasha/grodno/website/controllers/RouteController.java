package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Aircraft;
import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.service.iterface.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/route")
    public String getAllRoute(Model model){
        List<Route> routes = routeService.getAll();
        model.addAttribute("route", routes);
        return "route";
    }

    @PostMapping("/route/add-route")
    public String addRoute(@ModelAttribute("Route") Route route){
        routeService.save(route);
        return "redirect:/route";
    }

    @GetMapping("/route/{id}/delete")
    String deleteRoute(@PathVariable Integer id){
        routeService.deleteById(id);
        return "redirect:/route";
    }
}
