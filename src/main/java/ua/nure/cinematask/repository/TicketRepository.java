package ua.nure.cinematask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.cinematask.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByCustomerName(String customerName);
}
