package dev.khaliuk.todoapp.controller;

import dev.khaliuk.todoapp.client.TodoBackendClient;
import dev.khaliuk.todoapp.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Controller
@RequestMapping("/")
public class MainController {
    private final ImageService imageService;
    private final TodoBackendClient todoBackendClient;
    private final ExecutorService executorService;

    public MainController(
            ImageService imageService,
            TodoBackendClient todoBackendClient,
            ExecutorService executorService
    ) {
        this.imageService = imageService;
        this.todoBackendClient = todoBackendClient;
        this.executorService = executorService;
    }

    @GetMapping
    public String getResponse(Model model) {
        var imageFuture = CompletableFuture.runAsync(imageService::prepareImage, executorService);

        var todos = todoBackendClient.getAllTodos();
        model.addAttribute("todos", todos);

        imageFuture.join();

        return "index";
    }
}
