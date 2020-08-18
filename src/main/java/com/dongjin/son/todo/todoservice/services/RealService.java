package com.dongjin.son.todo.todoservice.services;

import com.dongjin.son.todo.todoservice.models.Todo;
import com.dongjin.son.todo.todoservice.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;

@Component
public class RealService {

    @Autowired
//    TestService testService;
    TestInterface testService;
    
    @Autowired
    TodoRepository todoRepository;


    String name = "real service";

    public void runRealService() {

//        testService.runTestService();
        testService.test();

        System.out.println("running real service!");


        Iterator<Todo> iterator = todoRepository.findAll().iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

//        Todo testTodo = new Todo("dj","study h2", "h2 study desc", new Date(), false);
//        System.out.println("testTodo = " + testTodo);
//        Todo savedTodo = todoRepository.save(testTodo);
//        System.out.println("savedTodo = " + savedTodo);

        
    }

}
