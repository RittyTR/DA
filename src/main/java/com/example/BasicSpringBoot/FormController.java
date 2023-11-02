package com.example.BasicSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

    @Autowired
    private UserDataRepository userRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userData", new UserData());
        model.addAttribute("userList", userRepository.findAll());
        return "index";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute UserData userData) {
        userRepository.save(userData);
        return "redirect:/";
    }
}
