package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.service.iterface.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;


    // ROUTE
    @GetMapping("/admin/route")
    public String getAllRoute(Model model) {
        List<Route> routes = routeService.getAll();
        model.addAttribute("routes", routes);
        return "route";
    }

    @PostMapping("/admin/route/add-route")
    public String addRoute(@ModelAttribute Route route) {
        routeService.save(route);
        return "redirect:/admin/route";
    }

    @GetMapping("/admin/route/{id}/delete")
    String deleteRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/admin/route";
    }

    @GetMapping("/admin/route/{id}/edit")
    public String getRouteForEdit(@PathVariable Integer id, RedirectAttributes red) {
        Route editRoute = routeService.getById(id);
        red.addFlashAttribute("editRoute", editRoute);
        return "redirect:/admin/route";
    }

    @PostMapping("/admin/route/{id}/update")
    public String editRoute(@ModelAttribute Route editRoute, @PathVariable Integer id) {
        routeService.updateRouteById(editRoute, id);
        return "redirect:/admin/route";
    }
}
