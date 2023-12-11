//package ru.kata.spring.boot_security.demo.controler;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.security.Principal;
//
//@Controller
//@AllArgsConstructor
//@RequestMapping("/admin")
//public class AdminControler {
//    private final UserService userServicel;
//
//    @GetMapping("")
//    public String getAll(Model model, Principal principal) {
//        model.addAttribute("princ", userServicel.loadUserByUsername(principal.getName()));
//        model.addAttribute("allUser", userServicel.getAllUser());
//        return "getAllUser";
//    }
//
//    @GetMapping("/{id}")
//    public String getId(Model model, Principal principal) {
//        model.addAttribute("princ", userServicel.loadUserByUsername(principal.getName()));
//        return "getAllUser";
//    }
//
//    @GetMapping("/f")
//    public String add(Model model, Principal principal) {
//        model.addAttribute("newUser", new User());
//        model.addAttribute("princ", userServicel.loadUserByUsername(principal.getName()));
//        return "getAllUser";
//    }
//
//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute("newUser") User user) {
//        userServicel.save(user);
//        return "redirect:/getAllUser";
//    }
//}
