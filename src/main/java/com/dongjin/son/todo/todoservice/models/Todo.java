package com.dongjin.son.todo.todoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String title;
    private String description;
    private Date targetDate;
    private boolean isDone;

    public Todo(){}
    public Todo(long id, String username, String title, String description, Date targetDate, boolean isDone) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public Todo(String username, String title, String description, Date targetDate, boolean isDone) {
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }
}
