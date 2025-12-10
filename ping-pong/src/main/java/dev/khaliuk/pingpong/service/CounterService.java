package dev.khaliuk.pingpong.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class CounterService {
    private static final AtomicLong counter = new AtomicLong();

    public String getPongCounter() {
        return "pong " + counter.getAndIncrement();
    }
}
