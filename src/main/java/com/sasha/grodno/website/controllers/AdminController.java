package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.DTO.UserDTO;
import com.sasha.grodno.website.convert.UserConvector;
import com.sasha.grodno.website.model.*;
import com.sasha.grodno.website.service.iterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(path = "admin")
public class AdminController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConvector convector;

    // admin
    @GetMapping("/work-with-admin")
    public String workWithAdmin(Model model, UserDTO userDTO,
                                @RequestParam(required = false, name = "pn") Integer pageNumber) {
        if (userDTO == null) {
            userDTO = new UserDTO();
        }
        pageNumber = getPageNumber(pageNumber);
        addAttributeModel(userDTO, model, pageNumber);
        return "work_with_admin";
    }

    @PostMapping("/work-with-admin/add-admin")
    public String addNewAdmin(@Valid UserDTO userDTO, BindingResult bindingResult, Model model,
                              @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        if (bindingResult.hasErrors()) {
            addAttributeModel(userDTO, model, pageNumber);
            return "work_with_admin";
        }
        userInfoService.saveAdmin(userDTO);
        return "redirect:/admin/work-with-admin";
    }


    @GetMapping("/myAdminInfo")
    public String getMyInfo(Model model) {
        UserInfo admin = userInfoService.getUserFromContext();
        UserDTO userDTO = convector.convertToUserDTO(admin);
        model.addAttribute("userDTO", userDTO);
        return "adminInfo";
    }


    @PostMapping("/myAdminInfo/editAdminNames")
    public String editAdminNames(@Valid UserDTO userDTO, BindingResult bindingResult, RedirectAttributes red, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "adminInfo";
        }
        UserInfo admin = userInfoService.getUserFromContext();
        if (passwordEncoder.matches(userDTO.getPassword(), admin.getPassword())) {
            userInfoService.updateUserNamesAndAuthentication(userDTO);
            red.addFlashAttribute("message", "CHANGES ACCEPTED");
        } else {
            red.addFlashAttribute("message", "THE PASSWORD IS INCORRECT");
        }
        return "redirect:/admin/myAdminInfo";
    }

    @PostMapping("/myAdminInfo/editAdminPassword")
    public String editAdminPassword(@RequestParam String passwordNew, @RequestParam String passwordNewConfirm,
                                    @RequestParam String passwordOld, RedirectAttributes red) {
        UserInfo admin = userInfoService.getUserFromContext();
        if (!(passwordNew.equals(passwordNewConfirm))) {
            red.addFlashAttribute("messagePassword", "NEW PASSWORDS DON'T MATCH");
        } else if (!passwordEncoder.matches(passwordOld, admin.getPassword())) {
            red.addFlashAttribute("messagePassword", "THE OLD PASSWORD IS INCORRECT");
        } else {
            userInfoService.updateUserPasswordAndAuthentication(admin, passwordNew);
            red.addFlashAttribute("messagePassword", "THE PASSWORD IS CHANGED");
        }
        return "redirect:/admin/myAdminInfo";
    }


    //user
    @GetMapping("/work-with-user")
    public String workWithUser(Model model, @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        Page<UserInfo> userInfoPage = userInfoService.getUsersPage(pageNumber);
        List<UserInfo> users = userInfoPage.toList();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", userInfoPage.getTotalPages());
        model.addAttribute("users", users);
        return "work_with_user";
    }

    @GetMapping("/work-with-user/{id}/tickets")
    public String getUsersTickets(@PathVariable Integer id, Model model,
                                  @RequestParam(required = false, name = "pn") Integer pageNumber) {
        pageNumber = getPageNumber(pageNumber);
        Page<Ticket> ticketPage = ticketService.getTicketsPageByUserId(id, pageNumber);
        List<Ticket> tickets = ticketPage.toList();
        model.addAttribute("flag", "adminUserTickets");
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", ticketPage.getTotalPages());
        model.addAttribute("tickets", tickets);
        return "ticket";
    }


    private Integer getPageNumber(@RequestParam(required = false, name = "pn") Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        } else {
            pageNumber -= 1;
        }
        return pageNumber;
    }

    private void addAttributeModel(@Valid UserDTO userDTO, Model model, @RequestParam(required = false, name = "pn") Integer pageNumber) {
        Page<UserInfo> userInfoPage = userInfoService.getAdminPage(pageNumber);
        List<UserInfo> admins = userInfoPage.toList();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", userInfoPage.getTotalPages());
        model.addAttribute("admins", admins);
        model.addAttribute("userDTO", userDTO);
    }
}
