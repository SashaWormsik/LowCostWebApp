package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @PostMapping("/register")
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model, RedirectAttributes red) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "register";
        }
        red.addFlashAttribute("message", "WE HAVE SENT THE ACTIVATION LINK TO YOUR EMAIL");
        userInfoService.saveUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/activate_user")
    public String activateUser(@RequestParam String token, RedirectAttributes red) {
        UserInfo userInfo = userInfoService.findByToken(token);
        if (userInfo == null) {
            red.addFlashAttribute("message", "INVALID ACTIVATION TOKEN");
        } else {
            userInfoService.activateUser(userInfo);
            red.addFlashAttribute("message", "YOU HAVE SUCCESSFULLY ACTIVATED YOUR PASSWORD");
        }
        return "redirect:/login";
    }

}
