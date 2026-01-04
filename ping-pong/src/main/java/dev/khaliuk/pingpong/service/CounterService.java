package dev.khaliuk.pingpong.service;

import dev.khaliuk.pingpong.constants.JpaEntityConstants;
import dev.khaliuk.pingpong.entity.Ping;
import dev.khaliuk.pingpong.repository.PingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterService {
    private static final Long INITIAL_COUNTER = 0L;

    private final PingRepository pingRepository;

    @Transactional
    public String incrementAndGetPongCounter() {
        var counter = pingRepository.incrementAndGet(JpaEntityConstants.COUNTER_ID);
        var result = "Ping / Pong: " + counter;
        System.out.println("Result returned: " + result);
        return result;
    }

    public String getPongCounter() {
        var counter = pingRepository.findById(JpaEntityConstants.COUNTER_ID)
            .map(Ping::getCounter)
            .orElse(INITIAL_COUNTER);
        var result = "Ping / Pong: " + counter;
        System.out.println("Result returned: " + result);
        return result;
    }
}
