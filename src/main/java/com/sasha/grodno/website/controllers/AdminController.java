package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.*;
import com.sasha.grodno.website.service.iterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping(path = "admin")
public class AdminController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    UserConvector convector;
    @Autowired
    PasswordEncoder passwordEncoder;

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


    @PostMapping("/myAdminInfo/editAdminNames")
    public String editAdminNames(@ModelAttribute UserDTO userDTO, RedirectAttributes red){
        UserInfo admin = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(passwordEncoder.matches(userDTO.getPassword(), admin.getPassword())){
            userInfoService.updateUserNames(userDTO);
            UserInfo adminFromDB = userInfoService.findByLogin(admin.getLogin());
            Authentication authentication = new UsernamePasswordAuthenticationToken(adminFromDB, adminFromDB.getPassword(), adminFromDB.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            red.addFlashAttribute("message", "CHANGES ACCEPTED");
        }else{
            red.addFlashAttribute("message", "THE PASSWORD IS INCORRECT");
        }
        return "redirect:/admin/myAdminInfo";
    }

    @PostMapping("/myAdminInfo/editAdminPassword")
    public  String editAdminPassword(@RequestParam String passwordNew, @RequestParam String passwordNewConfirm,
                                    @RequestParam String passwordOld,RedirectAttributes red){
        UserInfo admin = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(passwordNew.equals(passwordNewConfirm))){
            red.addFlashAttribute("messagePassword", "NEW PASSWORDS DON'T MATCH");
        }

        else if(!passwordEncoder.matches(passwordOld, admin.getPassword())){
            red.addFlashAttribute("messagePassword", "THE OLD PASSWORD IS INCORRECT");

        }else {
            userInfoService.updateUserPassword(admin, passwordNew);
            UserInfo adminFromDB = userInfoService.findByLogin(admin.getLogin());
            Authentication authentication = new UsernamePasswordAuthenticationToken(adminFromDB, adminFromDB.getPassword(), adminFromDB.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            red.addFlashAttribute("messagePassword", "THE PASSWORD IS CHANGED");
        }
        return "redirect:/admin/myAdminInfo";
    }




    //user
    @GetMapping("/work-with-user")
    public String workWithUser(Model model) {
        List<UserInfo> users = userInfoService.findAllUsers();
        model.addAttribute("users", users);
        return "work_with_user";
    }

}
