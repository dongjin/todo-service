package com.dongjin.son.todo.todoservice.services;

import com.dongjin.son.todo.todoservice.models.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardcodedService {

    private static List<Todo> todoList = new ArrayList<>();
    private static int idCounter = 0;

    static {
        todoList.add(new Todo(++idCounter, "dongjin", "study Angular", "study Angular", new Date(), false));
        todoList.add(new Todo(++idCounter, "dongjin", "study microservice", "study microservice", new Date(), false));
        todoList.add(new Todo(++idCounter, "dongjin", "study SwiftUI", "study SwiftUI", new Date(), false));
        todoList.add(new Todo(++idCounter, "dongjin", "exercise", "exercise", new Date(), false));
    }

    public List<Todo> findAll() {
        return todoList;
    }

    public Todo deleteById(long id) {
        Todo todo = findById(id);
        if (todo != null && todoList.remove(todo)) {
            return todo;
        }
        return null;
    }

    public Todo findById(long id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }
}
