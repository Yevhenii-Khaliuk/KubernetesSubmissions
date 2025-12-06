package dev.khaliuk.todoapp.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HashService {
    private static final String hash = UUID.randomUUID().toString();

    public String getAppHash() {
        return hash;
    }

    public String getNewHash() {
        return UUID.randomUUID().toString();
    }
}
