package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/myUserInfo")
    public String getMyInfo(Model model){
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "userInfo";
    }

    @PostMapping("/user/editUserNames")
    public String editUser(@ModelAttribute UserDTO userDTO, RedirectAttributes red){
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String passwordUserDTO = passwordEncoder.encode(userDTO.getPassword());
        if(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
            userInfoService.updateUserNames(userDTO);
        }else{
            red.addFlashAttribute("message", "THE PASSWORD IS INCORRECT");
        }
        return "redirect:/myUserInfo";
    }
}
