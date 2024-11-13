package com.sampleproject.springbootmongodb.Service;

import com.sampleproject.springbootmongodb.Model.TodoDTO;
import com.sampleproject.springbootmongodb.Repository.TodoRepository;
import com.sampleproject.springbootmongodb.exception.TodoCollectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoService todoService;
    @Override
    public  void createTodo(TodoDTO todoDTO) throws ConstraintViolationException,TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findBytodo(todoDTO.getTodo());
        if(todoDTOOptional.isPresent()){
            throw  new TodoCollectionException((TodoCollectionException.TodoAlreadyExists()));
        }else {
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
        }
    }

    @Override
    public List<TodoDTO> getTodos(){
        List<TodoDTO> todoDTOList = todoRepository.findAll();
        if(todoDTOList.size()>0){
            return todoDTOList;
        }else {
            return new ArrayList<TodoDTO>();
        }
    }

    @Override
    public TodoDTO getTodoById(String id) throws TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findById(id);
        if(!todoDTOOptional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }else{
            return todoDTOOptional.get();
        }
    }

    @Override
    public void updateTodo(String id,TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findById(id);
        Optional<TodoDTO>todoDTOOptional1=todoRepository.findBytodo(todoDTO.getTodo());

        if(todoDTOOptional.isPresent()){
            if(todoDTOOptional1.isPresent()&&todoDTOOptional1.get().getId().equals(id)){
                throw new TodoCollectionException((TodoCollectionException.TodoAlreadyExists()));
            }
            TodoDTO todoUpdate=todoDTOOptional.get();
            todoUpdate.setTodo(todoDTO.getTodo());
            todoUpdate.setDescription(todoDTO.getDescription());
            todoUpdate.setCompleted(todoDTO.getCompleted());
            todoUpdate.setUpdateAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoUpdate);
        }else {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findById(id);
        if(todoDTOOptional.isPresent()){
            todoRepository.deleteById(id);
        }else {
            throw  new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }

    }
}
