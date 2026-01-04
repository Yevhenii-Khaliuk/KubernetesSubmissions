package dev.khaliuk.pingpong.config;

import dev.khaliuk.pingpong.constants.JpaEntityConstants;
import dev.khaliuk.pingpong.repository.PingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbCounterInitializer implements ApplicationRunner {

    private final PingRepository pingRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        pingRepository.initIfMissing(JpaEntityConstants.COUNTER_ID);
    }
}
