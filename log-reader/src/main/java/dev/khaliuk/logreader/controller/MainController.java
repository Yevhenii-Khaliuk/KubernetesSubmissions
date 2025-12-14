package dev.khaliuk.logreader.controller;

import dev.khaliuk.logreader.service.LogReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class MainController {
    private final LogReaderService logReaderService;

    public MainController(LogReaderService logReaderService) {
        this.logReaderService = logReaderService;
    }

    @GetMapping
    public String getLogOutput() throws IOException {
        return logReaderService.getLogOutput();
    }
}
