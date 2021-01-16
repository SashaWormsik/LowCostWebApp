package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.*;
import com.sasha.grodno.website.service.iterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "admin")
public class AdminController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    UserConvector convector;

    // admin
    @GetMapping("/work-with-admin")
    public String workWithAdmin(Model model) {
        List<UserInfo> admins = userInfoService.findAllAdmins();
        model.addAttribute("admins", admins);
        return "work_with_admin";
    }

    @PostMapping("/work-with-admin/add-admin")
    public String addNewAdmin(@ModelAttribute UserDTO userDTO) {
        userInfoService.saveAdmin(userDTO);
        return "redirect:/admin/work-with-admin";
    }

    @GetMapping("/myAdminInfo")
    public String getMyInfo(Model model){
        UserInfo admin = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", admin);
        return "adminInfo";
    }

    //user
    @GetMapping("/work-with-user")
    public String workWithUser(Model model) {
        List<UserInfo> users = userInfoService.findAllUsers();
        model.addAttribute("users", users);
        return "work_with_user";
    }

}
