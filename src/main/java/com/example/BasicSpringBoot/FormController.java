package com.example.BasicSpringBoot;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // Import the Controller annotation
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller // Use @Controller instead of @RestController
@RequestMapping("/") // Use @RequestMapping to define the base URL for this controller
public class FormController {

    @Autowired
    private UserDataRepository userRepository;
    private final WebClient webClient;

    @Autowired
    public FormController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

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

    @GetMapping("/callApi")
    @ResponseBody

    public Map<String, Object> callCatFactsApi() {
        // Cat Facts API URL
        Map<String, Object> responseMap = new LinkedHashMap<>();

        ClientResponse response = webClient.get()
                .uri("/posts/1") // Example endpoint from JSONPlaceholder
                .exchange()
                .block();

        HttpStatus statusCode = (HttpStatus) response.statusCode();
        HttpHeaders headers = response.headers().asHttpHeaders();
        String responseBody = response.bodyToMono(String.class).block();

        responseMap.put("statusCode", statusCode.value());
        responseMap.put("statusMessage", statusCode.getReasonPhrase());
        responseMap.put("headers", headers);
        responseMap.put("body", responseBody);

        return responseMap;
    }
}
/*
Author - Rity Tharakkal Raphel
Date - 17.11.2023
Exercise 2 - Calling API's
Matriculation No: 1459915
 */