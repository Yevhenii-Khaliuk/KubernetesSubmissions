package dev.khaliuk.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class TodoAppConfig {
    @Bean
    public ExecutorService executorService() {
        var threadCount = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(threadCount);
    }
}
