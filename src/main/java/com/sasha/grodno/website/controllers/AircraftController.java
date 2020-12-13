package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Aircraft;
import com.sasha.grodno.website.service.iterface.AircraftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;


    @GetMapping("/aircraft")
    public String getAllAircraft(Model model) {
        List<Aircraft> aircrafts = aircraftService.getAll();
        model.addAttribute("aircrafts", aircrafts);
        return "aircrafts";
    }

    @PostMapping("/aircraft/add-aircraft")
    public String addAircraft(@ModelAttribute("Aircraft") Aircraft aircraft){
        aircraftService.save(aircraft);
        return "redirect:/aircraft";
    }

    @GetMapping("/aircraft/{id}/delete")
    String deleteAircraft(@PathVariable Integer id){
        aircraftService.deleteById(id);
        return "redirect:/aircraft";
    }

}
