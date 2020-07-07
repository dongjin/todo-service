package com.dongjin.son.todo.todoservice;

import com.dongjin.son.todo.todoservice.services.RealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Apprunner implements ApplicationRunner {

    @Autowired
    RealService realService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running App Runner");
        realService.runRealService();
    }
}
