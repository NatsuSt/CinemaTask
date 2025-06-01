package ua.nure.cinematask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.nure.cinematask.model.*;
import ua.nure.cinematask.repository.UserRepository;
import ua.nure.cinematask.security.JwtTokenProvider;
import ua.nure.cinematask.service.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({AuthController.class, MovieController.class, ScreeningController.class, TicketController.class})
class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private MovieService movieService;

    @MockBean
    private ScreeningService screeningService;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    // ---------------- AUTH ------------------

    @Test
    @DisplayName("POST /api/auth/register - success")
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    @DisplayName("POST /api/auth/login - success")
    void testLoginSuccess() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        User dbUser = new User();
        dbUser.setUsername("testuser");
        dbUser.setPassword("encoded");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(dbUser));
        when(passwordEncoder.matches("password", "encoded")).thenReturn(true);
        when(jwtTokenProvider.generateToken("testuser")).thenReturn("dummy-token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("dummy-token"));
    }

    // ---------------- MOVIES ------------------

    @Test
    void testGetMovies() throws Exception {
        when(movieService.getMovies()).thenReturn(List.of(new Movie(1L, "Interstellar", "Sci-fi", 169, 12)));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Interstellar"));
    }

    // ---------------- SCREENINGS ------------------

    @Test
    void testGetScreenings() throws Exception {
        when(screeningService.getScreening()).thenReturn(List.of(new Screening()));

        mockMvc.perform(get("/screenings"))
                .andExpect(status().isOk());
    }

    // ---------------- TICKETS ------------------

    @Test
    void testCreateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setCustomerName("John Doe");

        when(ticketService.createTicket(any(Ticket.class))).thenReturn(ticket);

        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }
}
