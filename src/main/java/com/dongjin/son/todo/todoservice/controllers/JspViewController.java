package com.dongjin.son.todo.todoservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

// Cannot use @RestController for JspView mapping
//@RestController
@Controller
public class JspViewController {

    @GetMapping("/")
//    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "index";
    }

    @GetMapping("/next")
//    @RequestMapping("/next")
    public String next(Map<String, Object> model) {
        model.put("message", "You are in new page !!");
        return "next";
    }
}
