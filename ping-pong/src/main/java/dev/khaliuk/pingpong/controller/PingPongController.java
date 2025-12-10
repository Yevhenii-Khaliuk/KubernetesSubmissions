package dev.khaliuk.pingpong.controller;

import dev.khaliuk.pingpong.service.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PingPongController {
    private final CounterService counterService;

    public PingPongController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping
    public String getPong() {
        return counterService.getPongCounter();
    }
}
