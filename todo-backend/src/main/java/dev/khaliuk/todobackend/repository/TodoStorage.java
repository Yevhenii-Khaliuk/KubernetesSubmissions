package dev.khaliuk.todobackend.repository;

import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class TodoStorage {
    private static final Set<String> TODOS = new LinkedHashSet<>();

    public List<String> getAll() {
        return TODOS.stream().toList();
    }

    public String add(String todo) {
        TODOS.add(todo);
        return todo;
    }
}
