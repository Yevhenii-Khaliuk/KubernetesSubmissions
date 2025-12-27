package dev.khaliuk.todobackend.service;

import dev.khaliuk.todobackend.repository.TodoStorage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoStorage todoStorage;

    public TodoService(TodoStorage todoStorage) {
        this.todoStorage = todoStorage;
    }

    public List<String> getAllTodos() {
        return todoStorage.getAll();
    }

    public String createTodo(String todo) {
        System.out.println("Adding a new todo element: " + todo);
        return todoStorage.add(todo);
    }
}
