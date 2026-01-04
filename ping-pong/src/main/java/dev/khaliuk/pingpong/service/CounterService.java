package dev.khaliuk.pingpong.service;

import dev.khaliuk.pingpong.entity.Ping;
import dev.khaliuk.pingpong.repository.PingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterService {
    private static final Long COUNTER_ID = 1L;
    private static final Long INITIAL_COUNTER = 0L;

    private final PingRepository pingRepository;

    @Transactional
    public String getAndIncrementPongCounter() {
        var counter = pingRepository.findById(COUNTER_ID)
                .map(this::incrementAndSaveCounter)
                .map(Ping::getCounter)
                .orElseGet(this::initAndIncrementCounter);
        var result = "Ping / Pong: " + counter;
        System.out.println("Result returned: " + result);
        return result;
    }

    @Transactional
    public String getPongCounter() {
        var counter = pingRepository.findById(COUNTER_ID)
                .map(Ping::getCounter)
                .orElseGet(this::initCounter);
        var result = "Ping / Pong: " + counter;
        System.out.println("Result returned: " + result);
        return result;
    }

    private Ping incrementAndSaveCounter(Ping pingEntity) {
        pingEntity.setCounter(pingEntity.getCounter() + 1);
        return pingRepository.save(pingEntity);
    }

    private Long initCounter() {
        return createAndSaveCounter(INITIAL_COUNTER + 1);
    }

    private Long initAndIncrementCounter() {
        return createAndSaveCounter(INITIAL_COUNTER + 1);
    }

    private Long createAndSaveCounter(Long counter) {
        var pingEntity = Ping.builder()
                .id(COUNTER_ID)
                .counter(counter)
                .build();
        pingRepository.save(pingEntity);
        return counter;
    }
}
