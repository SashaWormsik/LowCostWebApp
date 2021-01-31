package com.sasha.grodno.website.controllers;

import com.sasha.grodno.website.model.Ticket;
import com.sasha.grodno.website.service.PDFGenerator;
import com.sasha.grodno.website.service.iterface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class PDFController {

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    TicketService ticketService;

    @GetMapping("/user/myTicket/{id}/toPDF") // перенести в тикет контроллер /tickets/{id}/pdf
    public void createPDFTicket(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Ticket ticket = ticketService.getById(id);
        String name = ticket.getPassengerFirstName() + ticket.getPassengerLastName();
        pdfGenerator.createPDF("PDFticket", name, ticket);
        response.setContentType("application/pdf");
        String filepath = System.getProperty("user.home") + File.separator + name + ".pdf";
        InputStream inputStream = new FileInputStream(new File(filepath));
        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }
    }
}
