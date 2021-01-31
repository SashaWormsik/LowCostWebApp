package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.service.iterface.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    // AIRPLANE
    @GetMapping("/admin/airplane") // /airplanes
    public String getAllAirplane(Airplane airplane, Model model,
                                 @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        if (airplane == null) {
            airplane = new Airplane();
        }
        addAttributeModel(airplane, model, pageNumber);
        return "airplane";
    }

    @PostMapping("/admin/airplane/add-airplane") // /airplanes
    public String addAirplane(@Valid Airplane airplane, BindingResult bindingResult, Model model,
                              @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        if (bindingResult.hasErrors()) {
            addAttributeModel(airplane, model, pageNumber);
            return "airplane";
        }
        airplaneService.save(airplane);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/admin/airplane/{id}/delete") // @DeleteMapping("/{id}")
    public String deleteAirplane(@PathVariable Integer id) {
        airplaneService.deleteById(id);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/admin/airplane/{id}/edit") // /airplanes/{id}
    public String getAirplaneForEdit(@PathVariable Integer id, Model model,
                                     @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        Airplane airplane = airplaneService.getById(id);
        addAttributeModel(airplane, model, pageNumber);
        return "airplane";
    }

    @PostMapping("/admin/airplane/{id}/update") // /airplanes/{id} лучше использовать PUT
    public String editAirplane(@PathVariable Integer id, Model model,
                               @Valid Airplane airplane, BindingResult bindingResult,
                               @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        if (bindingResult.hasErrors()) {
            addAttributeModel(airplane, model, pageNumber);
            return "airplane";
        }
        airplaneService.updateAirplaneById(airplane, id);
        return "redirect:/admin/airplane";
    }


    private Integer getPageNumber(@RequestParam(required = false, name = "pn") Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        } else {
            pageNumber -= 1;
        }
        return pageNumber;
    }

    private void addAttributeModel(Airplane airplane, Model model, Integer pageNumber) {
        Page<Airplane> airplanePage = airplaneService.getAirplanesPage(pageNumber);
        List<Airplane> airplanes = airplanePage.toList();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", airplanePage.getTotalPages());
        model.addAttribute("airplane", airplane);
        model.addAttribute("airplanes", airplanes);
    }
}
