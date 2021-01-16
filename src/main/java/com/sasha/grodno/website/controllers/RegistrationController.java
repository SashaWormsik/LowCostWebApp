package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserConvector convector;

    @GetMapping("/register")
    public String registration() {
        return "register";
    }


    @PostMapping("/register/new")
    public String registerUser(@ModelAttribute UserDTO userDTO){
        userInfoService.saveUser(userDTO);
        return "redirect:/login";
    }

}
