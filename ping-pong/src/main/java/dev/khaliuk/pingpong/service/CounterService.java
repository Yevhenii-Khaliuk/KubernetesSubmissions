package dev.khaliuk.pingpong.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class CounterService {
    private static final AtomicLong COUNTER = new AtomicLong();

    public String getAndIncrementPongCounter() {
        var result = "Ping / Pong: " + COUNTER.incrementAndGet();
        System.out.println("Result returned: " + result);
        return result;
    }

    public String getPongCounter() {
        var result = "Ping / Pong: " + COUNTER.get();
        System.out.println("Result returned: " + result);
        return result;
    }
}
