package com.sampleproject.springbootmongodb.Service;

import com.sampleproject.springbootmongodb.Model.TodoDTO;
import com.sampleproject.springbootmongodb.exception.TodoCollectionException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.SimpleTimeZone;

public interface TodoService {

    public void createTodo(TodoDTO todoDto) throws ConstraintViolationException,TodoCollectionException;
    public List<TodoDTO> getTodos();

    public TodoDTO getTodoById(String id) throws TodoCollectionException;

    public void updateTodo(String id,TodoDTO todoDTO) throws ConstraintViolationException,TodoCollectionException;

    public void deleteTodoById(String id) throws TodoCollectionException;
}
