package dev.khaliuk.todobackend.repository;

import dev.khaliuk.todobackend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
