package com.dongjin.son.todo.todoservice.controllers;

import com.dongjin.son.todo.todoservice.models.Todo;
import com.dongjin.son.todo.todoservice.repositories.TodoRepository;
import com.dongjin.son.todo.todoservice.services.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RestController
public class TodoRestController {

    @Autowired
    private TodoHardcodedService todoService;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }

    @Autowired
    private TodoRepository todoRepository;

    //    http://localhost:8080/users/dongjin/todos
    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable("username") String username) {

        return todoService.findAll();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("username") String username, @PathVariable("id") long id) {
        Todo todo = todoService.deleteById(id);
        if (todo != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //    http://localhost:8080/users/dongjin
    @GetMapping("/users/{username}")
    List<Todo> findTodosByUsername(@PathVariable("username") String username) {
        return todoRepository.findByUsername(username);
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable("username") String username, @RequestBody Todo todo) {
        Todo createdTodo = todoRepository.save(todo);
        System.out.println("createdTodo = " + createdTodo);
        return createdTodo;
    }

}
