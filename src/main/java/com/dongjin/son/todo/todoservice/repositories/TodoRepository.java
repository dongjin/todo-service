package com.dongjin.son.todo.todoservice.repositories;

import com.dongjin.son.todo.todoservice.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findById(long id);
    List<Todo> findByUsername(String username);
}
