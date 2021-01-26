package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.service.iterface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    //TICKET
    @GetMapping("/admin/ticket")
    public String getAllTicket(Model model,
                               @RequestParam(required = false, name = "pn") Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        } else {
            pageNumber -= 1;
        }
        Page<Ticket> ticketPage = ticketService.getAllPage(pageNumber);
        List<Ticket> tickets = ticketPage.toList();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", ticketPage.getTotalPages());
        model.addAttribute("tickets", tickets);
        return "ticket";
    }

    @GetMapping("/admin/ticket/{id}/delete")
    String deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
        return "redirect:/admin/ticket";
    }


    /*
    @PostMapping("/admin/ticket/add-ticket")
    public String addTicket(@ModelAttribute Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/admin/ticket";
    }*/
}
