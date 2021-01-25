package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserConvector convector;

    @GetMapping("/register")
    public String registration(UserDTO user, Model model) {
        if (user == null) {
            user = new UserDTO();
        }
        model.addAttribute("userDTO", user);
        return "register";
    }


    @PostMapping("/register/new")
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "register";
        }
        userInfoService.saveUser(userDTO);
        return "redirect:/login";
    }

}
