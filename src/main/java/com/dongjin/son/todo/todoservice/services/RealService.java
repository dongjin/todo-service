package com.dongjin.son.todo.todoservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RealService {

    @Autowired
//    TestService testService;
    TestInterface testService;


    String name = "real service";

    public void runRealService() {

//        testService.runTestService();
        testService.test();

        System.out.println("running real service!");
    }

}
