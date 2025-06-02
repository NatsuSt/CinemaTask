package ua.nure.cinematask.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.cinematask.model.User;
import ua.nure.cinematask.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ViewController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // 1. Форма реєстрації (GET)
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // templates/register.html
    }

    // 2. Обробка реєстрації (POST)
    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String password) {
        if (userRepository.existsByUsername(username)) {
            return "redirect:/register?error=true";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");

        userRepository.save(user);
        logger.info("New register via form");
        return "redirect:/login?registered=true";
    }

    // 3. Форма логіну (GET)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // templates/login.html
    }

    // 4. Захищена сторінка
    @GetMapping("/home")
    public String showHome(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        logger.info("Show home view");
        return "home"; // templates/home.html
    }


}
