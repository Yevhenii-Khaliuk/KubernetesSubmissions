package dev.khaliuk.logoutput.service;

import dev.khaliuk.logoutput.client.PingPongClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MessageService {
    private static final String uuid = UUID.randomUUID().toString();

    private final PingPongClient pingPongClient;

    public MessageService(PingPongClient pingPongClient) {
        this.pingPongClient = pingPongClient;
    }

    public String getLogWithPingPong() {
        var pings = pingPongClient.getPings();
        return "%s.\n%s".formatted(getLogMessage(), pings);
    }

    public String getLogMessage() {
        return "%s: %s".formatted(LocalDateTime.now(), uuid);
    }
}
