package dev.khaliuk.logoutput.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MessageService {
    private static final String uuid = UUID.randomUUID().toString();
    private static final Path FILE_PATH = Paths.get("/opt/app/logs/log_output.txt");

    public String getLogWithPingPong() throws IOException {
        return "%s.\n%s".formatted(getLogMessage(), getPingPong());
    }

    public String getLogMessage() {
        return "%s: %s".formatted(LocalDateTime.now(), uuid);
    }

    private String getPingPong() throws IOException {
        return Files.readString(FILE_PATH);
    }
}
