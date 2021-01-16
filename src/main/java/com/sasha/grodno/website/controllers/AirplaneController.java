package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.service.iterface.AirplaneService;
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
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    // AIRPLANE
    @GetMapping("/admin/airplane")
    public String getAllAirplane(Model model) {
        List<Airplane> airplanes = airplaneService.getAll();
        model.addAttribute("airplanes", airplanes);
        return "airplane";
    }

    @PostMapping("/admin/airplane/add-airplane")
    public String addAirplane(@ModelAttribute Airplane airplane) {
        airplaneService.save(airplane);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/admin/airplane/{id}/delete")
    public String deleteAirplane(@PathVariable Integer id) {
        airplaneService.deleteById(id);
        return "redirect:/admin/airplane";
    }

    @GetMapping("/admin/airplane/{id}/edit")
    public String getAirplaneForEdit(@PathVariable Integer id, RedirectAttributes red) {
        Airplane editAirplane = airplaneService.getById(id);
        red.addFlashAttribute("editAirplane", editAirplane);
        return "redirect:/admin/airplane";
    }

    @PostMapping("/admin/airplane/{id}/update")
    public String editAirplane(@ModelAttribute Airplane editAirplane, @PathVariable Integer id) {
        airplaneService.updateAirplaneById(editAirplane, id);
        return "redirect:/admin/airplane";
    }


}
