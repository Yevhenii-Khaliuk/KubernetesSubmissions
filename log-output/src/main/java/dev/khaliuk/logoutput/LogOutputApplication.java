package dev.khaliuk.logoutput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LogOutputApplication {

    static void main(String[] args) {
        SpringApplication.run(LogOutputApplication.class, args);
    }
}
