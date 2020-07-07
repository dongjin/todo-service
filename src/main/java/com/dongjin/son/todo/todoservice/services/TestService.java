package com.dongjin.son.todo.todoservice.services;

import org.springframework.stereotype.Component;

@Component
public class TestService implements TestInterface {

    String name ="test service";

    public void runTestService() {
        System.out.println("running test service!");
    }

    @Override
    public void test() {
        System.out.println("test called!");

    }
}
