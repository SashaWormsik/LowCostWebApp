package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String editUserNames(@ModelAttribute UserDTO userDTO, RedirectAttributes red){
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
            userInfoService.updateUserNames(userDTO);
            UserInfo userFromDB = userInfoService.findByLogin(user.getLogin());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userFromDB, userFromDB.getPassword(), userFromDB.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            red.addFlashAttribute("message", "CHANGES ACCEPTED");
        }else{
            red.addFlashAttribute("message", "THE PASSWORD IS INCORRECT");
        }
        return "redirect:/myUserInfo";
    }

    @PostMapping("/user/editUserPassword")
    public  String editUserPassword(@RequestParam String passwordNew, @RequestParam String passwordNewConfirm,
                                    @RequestParam String passwordOld,RedirectAttributes red){
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(passwordNew.equals(passwordNewConfirm))){
            red.addFlashAttribute("messagePassword", "NEW PASSWORDS DON'T MATCH");
        }

        else if(!passwordEncoder.matches(passwordOld, user.getPassword())){
            red.addFlashAttribute("messagePassword", "THE OLD PASSWORD IS INCORRECT");

        }else {
            userInfoService.updateUserPassword(user, passwordNew);
            UserInfo userFromDB = userInfoService.findByLogin(user.getLogin());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userFromDB, userFromDB.getPassword(), userFromDB.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            red.addFlashAttribute("messagePassword", "THE PASSWORD IS CHANGED");
        }
        return "redirect:/myUserInfo";
    }
}
