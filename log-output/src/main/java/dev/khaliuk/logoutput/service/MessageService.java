package dev.khaliuk.logoutput.service;

import dev.khaliuk.logoutput.client.PingPongClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MessageService {
    private static final String uuid = UUID.randomUUID().toString();

    private final PingPongClient pingPongClient;

    public MessageService(PingPongClient pingPongClient) {
        this.pingPongClient = pingPongClient;
    }

    public String getCombinedMessage() {
        var fileContent = "file content: " + readConfigMapFile();
        var envVarValue = "env variable: MESSAGE=" + System.getenv("MESSAGE");
        var logMessage = getLogMessage() + ".";
        var pings = pingPongClient.getPings();
        return String.join("\n", fileContent, envVarValue, logMessage, pings);
    }

    public String getLogMessage() {
        return "%s: %s".formatted(LocalDateTime.now(), uuid);
    }

    private String readConfigMapFile() {
        try {
            return Files.readString(Paths.get("/config/information.txt")).trim();
        } catch (IOException e) {
            System.err.println("Error during file read: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
