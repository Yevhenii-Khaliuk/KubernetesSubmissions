package dev.khaliuk.logoutput.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MessageService {
    private static final String uuid = UUID.randomUUID().toString();

    public String getLogMessage() {
        return "%s: %s".formatted(LocalDateTime.now(), uuid);
    }
}
