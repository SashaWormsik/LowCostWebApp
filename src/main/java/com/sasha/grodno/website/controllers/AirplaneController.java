package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.service.iterface.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.List;

@Controller
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    // AIRPLANE
    @GetMapping("/admin/airplane")
    public String getAllAirplane(Airplane airplane, Model model) {
        List<Airplane> airplanes = airplaneService.getAll();
        if (airplane == null) {
            airplane = new Airplane();
        }
        model.addAttribute("airplane", airplane);
        model.addAttribute("airplanes", airplanes);
        return "airplane";
    }

    @PostMapping("/admin/airplane/add-airplane")
    public String addAirplane(@Valid Airplane airplane, BindingResult bindingResult, Model model) {
        List<Airplane> airplanes = airplaneService.getAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("airplane", airplane);
            model.addAttribute("airplanes", airplanes);
            return "airplane";
        }
        airplaneService.save(airplane);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/admin/airplane/{id}/delete")
    public String deleteAirplane(@PathVariable Integer id) {
        airplaneService.deleteById(id);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/admin/airplane/{id}/edit")
    public String getAirplaneForEdit(@PathVariable Integer id, Model model) {
        Airplane airplane = airplaneService.getById(id);
        List<Airplane> airplanes = airplaneService.getAll();
        model.addAttribute("airplane", airplane);
        model.addAttribute("airplanes", airplanes);
        return "airplane";
    }

    @PostMapping("/admin/airplane/{id}/update")
    public String editAirplane(@PathVariable Integer id, Model model,
                               @Valid Airplane airplane, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("airplanes", airplaneService.getAll());
            model.addAttribute("airplane", airplane);
            return "airplane";
        }
        airplaneService.updateAirplaneById(airplane, id);
        return "redirect:/admin/airplane";
    }


}
