package com.dongjin.son.todo.todoservice.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest() {
    }
}
