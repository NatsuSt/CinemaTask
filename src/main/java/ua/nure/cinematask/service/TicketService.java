package ua.nure.cinematask.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.cinematask.model.Screening;
import ua.nure.cinematask.model.Ticket;
import ua.nure.cinematask.repository.ScreeningRepository;
import ua.nure.cinematask.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ScreeningRepository screeningRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ScreeningRepository screeningRepository) {
        this.ticketRepository = ticketRepository;
        this.screeningRepository = screeningRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        Screening screening = screeningRepository.findById(ticket.getScreeningId().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Screening ID not found"));
        ticket.setScreeningId(screening);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByCustomer(String customerName) {
        return ticketRepository.findAllByCustomerName(customerName);
    }
}
