package com.sampleproject.springbootmongodb.Controller;

import com.sampleproject.springbootmongodb.Model.TodoDTO;
import com.sampleproject.springbootmongodb.Repository.TodoRepository;
import com.sampleproject.springbootmongodb.Service.TodoService;
import com.sampleproject.springbootmongodb.Service.TodoServiceImpl;
import com.sampleproject.springbootmongodb.exception.TodoCollectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private  TodoRepository todoRepository;
    @GetMapping("/todos")

    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> todoDTOList = todoService.getTodos();
          return new ResponseEntity<>(todoDTOList, !todoDTOList.isEmpty() ?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }
    @PostMapping("/todos")
    public  ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try {
            todoService.createTodo(todoDTO);
            return new ResponseEntity<>(todoDTO,HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/todos/{id}")
    public  ResponseEntity<?> getTodoById(@PathVariable("id") String id )throws TodoCollectionException{
        try{
            TodoDTO todoDTO=todoService.getTodoById(id);
            return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.OK);
        }catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodoById(@PathVariable("id") String id,@RequestBody TodoDTO todoDTO) throws TodoCollectionException {
       try {
           todoService.updateTodo(id,todoDTO);
           return new ResponseEntity<>("Updated todo with id "+id,HttpStatus.OK);
       }catch (ConstraintViolationException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
       }catch (TodoCollectionException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }

    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") String id){
        try {
           todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted with id "+id,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
