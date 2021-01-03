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



}
