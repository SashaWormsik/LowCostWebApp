package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.service.iterface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    //TICKET
    @GetMapping("/admin/ticket")
    public String getAllTicket(Model model) {
        List<Ticket> tickets = ticketService.getAll();
        model.addAttribute("tickets", tickets);
        return "ticket";
    }

    @PostMapping("/admin/ticket/add-ticket")
    public String addTicket(@ModelAttribute Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/admin/ticket";
    }

    @GetMapping("/admin/ticket/{id}/delete")
    String deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
        return "redirect:/admin/ticket";
    }
}
