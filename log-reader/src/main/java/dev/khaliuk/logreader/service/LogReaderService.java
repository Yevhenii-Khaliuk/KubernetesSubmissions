package dev.khaliuk.logreader.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class LogReaderService {
    private static final String FILE_NAME = "/opt/app/logs/log_output.txt";

    public String getLogOutput() throws IOException {
        var path = Paths.get(FILE_NAME);
        return String.join("\n", Files.readAllLines(path));
    }
}
