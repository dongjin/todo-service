package com.dongjin.son.todo.todoservice.models;

import lombok.Data;

@Data
public class AuthenticationBean {
    String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }
}
