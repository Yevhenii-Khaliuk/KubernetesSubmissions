package dev.khaliuk.logoutput.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final MessageService messageService;

    public LogService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Scheduled(fixedRate = 5000)
    public void logMessage() {
        System.out.println(messageService.getLogMessage());
    }
}
