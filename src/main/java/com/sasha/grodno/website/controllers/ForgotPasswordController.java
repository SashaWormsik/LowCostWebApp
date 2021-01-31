package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ForgotPasswordController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/forgot_password")
    public String showForgotPassword() {
        return "forgot_password";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(@RequestParam String email, Model model) {
        UserInfo userInfo = userInfoService.findByEmail(email);
        if (userInfo == null) {
            model.addAttribute("message", "THE USER WITH THIS EMAIL WAS NOT FOUND.");
        } else {
            userInfoService.updateUserToken(userInfo);
            userInfoService.sendForgotPasswordEmail(userInfo);
            model.addAttribute("message", "WE HAVE SENT A RESET PASSWORD LINK TO YOUR EMAIL. PLEASE CHECK.");
        }
        return "forgot_password";
    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        UserInfo userInfo = userInfoService.findByToken(token);
        model.addAttribute("token", token);
        if (userInfo == null) {
            model.addAttribute("message", "INVALID TOKEN. USER NOT FOUND");
        }
        return "reset_password";
    }


    @PostMapping("/reset_password")
    public String processResetPassword(@RequestParam String token, @RequestParam String password,
                                       @RequestParam String passwordConfirm, Model model, RedirectAttributes red) {
        UserInfo userInfo = userInfoService.findByToken(token);
        if (userInfo == null) {
            model.addAttribute("message", "INVALID TOKEN. USER NOT FOUND");
            return "reset_password";
        } else if (!passwordConfirm.equals(password)) {
            model.addAttribute("token", token);
            model.addAttribute("message", "NEW PASSWORDS DON'T MATCH");
            return "reset_password";
        } else {
            userInfoService.updateUserPassword(userInfo, password);
            red.addFlashAttribute("message", "A NEW PASSWORD HAS BEEN SUCCESSFULLY ADOPTED");
            return "redirect:/login";
        }
    }

}
