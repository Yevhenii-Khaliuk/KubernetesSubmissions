package dev.khaliuk.pingpong.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class CounterService {
    private static final Path FILE_PATH = Paths.get("/opt/app/logs/log_output.txt");

    private static Long counter = 0L;

    public String getPongCounter() {
        var result = getResultAndSaveToFile();
        System.out.println("Result returned: " + result);
        return result;
    }

    private synchronized String getResultAndSaveToFile() {
        var result = "Ping / Pong: " + counter++;

        try (var fileStream = Files.newOutputStream(FILE_PATH, StandardOpenOption.CREATE)) {
            fileStream.write(result.getBytes());
        } catch (IOException e) {
            var errorMessage = "Error when saving result to file: %s.".formatted(e.getMessage());
            System.out.println(errorMessage);
        }

        return result;
    }
}
