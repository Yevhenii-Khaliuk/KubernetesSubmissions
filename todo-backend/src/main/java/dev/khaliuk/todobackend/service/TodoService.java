package dev.khaliuk.todobackend.service;

import dev.khaliuk.todobackend.entity.Todo;
import dev.khaliuk.todobackend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<String> getAllTodos() {
        return todoRepository.findAll()
            .stream()
            .map(Todo::getTask)
            .toList();
    }

    public Todo createTodo(String todo) {
        System.out.println("Adding a new todo element: " + todo);
        var newTodo = Todo.builder().task(todo).build();
        return todoRepository.save(newTodo);
    }
}
