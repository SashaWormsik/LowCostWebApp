package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.model.UserInfo;
import com.sasha.grodno.website.service.iterface.TicketService;
import com.sasha.grodno.website.service.iterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TicketService ticketService;

    @Autowired
    private UserConvector convector;

    @GetMapping("/user")
    public String getMyInfo(Model model) {
        UserInfo user = userInfoService.getUserFromContext();
        UserDTO userDTO = convector.convertToUserDTO(user);
        model.addAttribute("userDTO", userDTO);
        return "userInfo";
    }

    @PostMapping("/user/editUserNames")
    public String editUserNames(@Valid UserDTO userDTO, BindingResult bindingResult,
                                RedirectAttributes red, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "userInfo";
        }
        UserInfo user = userInfoService.getUserFromContext();
        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            userInfoService.updateUserNamesAndAuthentication(userDTO);
            red.addFlashAttribute("message", "CHANGES ACCEPTED");
        } else {
            red.addFlashAttribute("message", "THE PASSWORD IS INCORRECT");
        }
        return "redirect:/user";
    }

    @PostMapping("/user/editUserPassword")
    public String editUserPassword(@RequestParam String passwordNew, @RequestParam String passwordNewConfirm,
                                   @RequestParam String passwordOld, RedirectAttributes red) {
        UserInfo user = userInfoService.getUserFromContext();
        if (!(passwordNew.equals(passwordNewConfirm))) {
            red.addFlashAttribute("messagePassword", "NEW PASSWORDS DON'T MATCH");
        } else if (!passwordEncoder.matches(passwordOld, user.getPassword())) {
            red.addFlashAttribute("messagePassword", "THE OLD PASSWORD IS INCORRECT");
        } else {
            userInfoService.updateUserPasswordAndAuthentication(user, passwordNew);
            red.addFlashAttribute("messagePassword", "THE PASSWORD IS CHANGED");
        }
        return "redirect:/user";
    }

    @GetMapping("/user/myTicket")
    public String showMyTicket(Model model,
                               @RequestParam(required = false, name = "pn") Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        } else {
            pageNumber -= 1;
        }
        UserInfo user = userInfoService.getUserFromContext();
        Page<Ticket> ticketPage = ticketService.getTicketsPageByUser(user, pageNumber);
        List<Ticket> tickets = ticketPage.toList();
        model.addAttribute("flag", "userTickets");
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", ticketPage.getTotalPages());
        model.addAttribute("tickets", tickets);
        return "ticket";
    }
}
