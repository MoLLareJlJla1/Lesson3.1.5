package ru.kata.spring.boot_security.demo.controler;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class UserControler {
    @GetMapping("/users")
    public String user(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("princ", user);
        return "getAllUser";
    }
}
