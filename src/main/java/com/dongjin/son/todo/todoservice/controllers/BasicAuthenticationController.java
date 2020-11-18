package com.dongjin.son.todo.todoservice.controllers;

import com.dongjin.son.todo.todoservice.models.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(maxAge = 3600)

@RestController
public class BasicAuthenticationController {

    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicAuthenticate(@RequestHeader("Authorization") String authorization) {

        System.out.println("Authorization: " + authorization);

        byte[] decodedBytes = Base64.getDecoder().decode(authorization.substring("Basic ".length()));
        String decodedString = new String(decodedBytes);
        System.out.println("decodedString = " + decodedString);

        return new AuthenticationBean("You are authenticated!");
    }
}
