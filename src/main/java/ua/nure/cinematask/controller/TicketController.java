package ua.nure.cinematask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.cinematask.model.Ticket;
import ua.nure.cinematask.service.TicketService;

import java.util.List;

@RestController
public class TicketController {

    private TicketService ticketService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public void setTicketService(TicketService ticketService) {
        logger.info("setTicketService called");
        this.ticketService = ticketService;
    }

    @PostMapping("/tickets")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        logger.info("createTicket called");
        return ticketService.createTicket(ticket);
    }

    @GetMapping("/tickets/by-customer")
    public List<Ticket> getTicketsByCustomer(@RequestParam String customerName) {
        logger.info("getTicketsByCustomer called");
        return ticketService.getTicketsByCustomer(customerName);
    }

}