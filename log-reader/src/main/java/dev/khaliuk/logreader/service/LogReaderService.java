package dev.khaliuk.logreader.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LogReaderService {
    private static final Path FILE_PATH = Paths.get("/opt/app/logs/log_output.txt");

    public String getLogOutput() throws IOException {
        return Files.readString(FILE_PATH);
    }
}
