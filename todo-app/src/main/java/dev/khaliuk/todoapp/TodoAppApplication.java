package dev.khaliuk.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoAppApplication {

    static void main(String[] args) {
        var context = SpringApplication.run(TodoAppApplication.class, args);
        var port = context.getEnvironment().getProperty("server.port");
        System.out.println("Server started in port " + port);
    }

}
