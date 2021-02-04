package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Route;
import com.sasha.grodno.website.service.iterface.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;


    // ROUTE
    @GetMapping("/routes")
    public String getAllRoute(Route route, Model model,
                              @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        if (route == null) {
            route = new Route();
        }
        addAttributeModel(route, model, pageNumber);
        return "route";
    }

    @PostMapping("/routes")
    public String addRoute(@Valid Route route, BindingResult bindingResult, Model model,
                           @RequestParam(required = false, name = "pn") Integer pageNumber){
        pageNumber = getPageNumber(pageNumber);
        if (bindingResult.hasErrors()) {
            addAttributeModel(route, model, pageNumber);
            return "route";
        }
        routeService.save(route);
        return "redirect:/routes";
    }

    @GetMapping("/routes/{id}/delete")
    String deleteRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/routes";
    }

    @GetMapping("/routes/{id}")
    public String getRouteForEdit(@PathVariable Integer id, Model model,
                                  @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        Route route = routeService.getById(id);
        addAttributeModel(route, model, pageNumber);
        return "route";
    }

    @PostMapping("/routes/{id}")
    public String editRoute(@PathVariable Integer id,
                            @Valid Route route, BindingResult bindingResult, Model model,
                            @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        if (bindingResult.hasErrors()) {
            addAttributeModel(route, model, pageNumber);
            return "route";
        }
        routeService.updateRouteById(route, id);
        return "redirect:/routes";
    }

    private void addAttributeModel(@Valid Route route, Model model, @RequestParam(required = false, name = "pn") Integer pageNumber) {
        Page<Route> routePage = routeService.getRoutesPage(pageNumber);
        List<Route> routes = routePage.toList();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", routePage.getTotalPages());
        model.addAttribute("routes", routes);
        model.addAttribute("route", route);
    }

    private Integer getPageNumber(@RequestParam(required = false, name = "pn") Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        } else {
            pageNumber -= 1;
        }
        return pageNumber;
    }
}
