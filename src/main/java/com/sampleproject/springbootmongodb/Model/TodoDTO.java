package com.sampleproject.springbootmongodb.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Document(collection = "todos")
public class TodoDTO {
    @Id
    private String id;
    @NotNull(message = "todo cannot be null")
    private String todo;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "completed cannot be null")
    private Boolean completed;
    private Date createdAt;
    private Date updateAt;

    public TodoDTO(String id, String todo, String description, Boolean completed, Date createdAt, Date updateAt) {
        this.id = id;
        this.todo = todo;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
