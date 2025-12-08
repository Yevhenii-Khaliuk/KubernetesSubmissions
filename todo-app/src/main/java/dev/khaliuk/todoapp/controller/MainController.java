package dev.khaliuk.todoapp.controller;

import dev.khaliuk.todoapp.service.HashService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    private final HashService hashService;

    public MainController(HashService hashService) {
        this.hashService = hashService;
    }

    @GetMapping
    public String getResponse() {
        var appHash = hashService.getAppHash();
        var requestHash = hashService.getNewHash();
        return "Application: %s; request: %s.".formatted(appHash, requestHash);
    }
}
