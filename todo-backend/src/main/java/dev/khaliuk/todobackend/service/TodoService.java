package dev.khaliuk.todobackend.service;

import dev.khaliuk.todobackend.client.WikipediaClient;
import dev.khaliuk.todobackend.dto.TodoResponse;
import dev.khaliuk.todobackend.entity.Todo;
import dev.khaliuk.todobackend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final WikipediaClient wikipediaClient;

    public List<String> getAllTodos() {
        return todoRepository.findAll()
            .stream()
            .map(Todo::getTask)
            .toList();
    }

    public Todo createTodo(String todo) {
        System.out.println("Adding a new todo element: " + todo);
        return saveNewTodo(todo);
    }

    public TodoResponse createRandomTodo() {
        var randomArticleUrl = wikipediaClient.getRandomArticleUrl();
        var task = "Read " + randomArticleUrl;
        var savedTodo = saveNewTodo(task);
        return new TodoResponse(savedTodo.getTask());
    }

    private Todo saveNewTodo(String task) {
        var newTodo = Todo.builder().task(task).build();
        return todoRepository.save(newTodo);
    }
}
