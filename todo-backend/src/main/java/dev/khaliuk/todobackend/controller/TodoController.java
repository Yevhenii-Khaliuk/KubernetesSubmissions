package dev.khaliuk.todobackend.controller;

import dev.khaliuk.todobackend.dto.ErrorResponse;
import dev.khaliuk.todobackend.dto.TodoResponse;
import dev.khaliuk.todobackend.service.TodoService;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<String> getTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTodo(@RequestParam @Size(max = 140) String todo) {
        var createdTodo = todoService.createTodo(todo);
        log.info("Todo has been added: {}", createdTodo);
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .header(HttpHeaders.LOCATION, "/")
            .build();
    }

    @RequestMapping("/random")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoResponse> createRandomTodo() {
        var createdTodo = todoService.createRandomTodo();
        log.info("Random Todo has been added: {}", createdTodo);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createdTodo);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(HandlerMethodValidationException ex) {
        var errors = new HashMap<String, String>();

        for (var error : ex.getAllErrors()) {
            var field = extractFieldName(error);
            var message = error.getDefaultMessage();
            errors.put(field, message);
        }

        var body = ErrorResponse.ofErrors(errors);
        log.error(body.toString());
        return ResponseEntity.badRequest().body(body);
    }

    private String extractFieldName(MessageSourceResolvable error) {
        var arguments = error.getArguments();

        if (arguments == null) {
            return "unknown";
        }

        return Stream.of(arguments)
            .filter(argument -> argument instanceof DefaultMessageSourceResolvable)
            .findFirst()
            .map(argument -> ((DefaultMessageSourceResolvable) argument).getDefaultMessage())
            .orElse("unknown");
    }
}
