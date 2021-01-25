package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.service.iterface.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;


    // ROUTE
    @GetMapping("/admin/route")
    public String getAllRoute(Route route, Model model) {
        List<Route> routes = routeService.getAll();
        if (route == null) {
            route = new Route();
        }
        model.addAttribute("route", route);
        model.addAttribute("routes", routes);
        return "route";
    }

    @PostMapping("/admin/route/add-route")
    public String addRoute(@Valid Route route, BindingResult bindingResult, Model model) {
        List<Route> routes = routeService.getAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("route", route);
            model.addAttribute("routes", routes);
            return "route";
        }
        routeService.save(route);
        return "redirect:/admin/route";
    }

    @GetMapping("/admin/route/{id}/delete")
    String deleteRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/admin/route";
    }

    @GetMapping("/admin/route/{id}/edit")
    public String getRouteForEdit(@PathVariable Integer id, Model model) {
        Route route = routeService.getById(id);
        List<Route> routes = routeService.getAll();
        model.addAttribute("routes", routes);
        model.addAttribute("route", route);
        return "route";
    }

    @PostMapping("/admin/route/{id}/update")
    public String editRoute(@PathVariable Integer id,
                            @Valid Route route, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("routes", routeService.getAll());
            model.addAttribute("route", route);
            return "route";
        }
        routeService.updateRouteById(route, id);
        return "redirect:/admin/route";
    }
}
