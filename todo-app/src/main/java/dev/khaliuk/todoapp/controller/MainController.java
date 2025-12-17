package dev.khaliuk.todoapp.controller;

import dev.khaliuk.todoapp.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private final ImageService imageService;

    public MainController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public String getResponse() {
        imageService.prepareImage();
        return "index";
    }
}
