package com.sampleproject.springbootmongodb.Repository;

import com.sampleproject.springbootmongodb.Model.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO,String> {
    @Query("{'todo':?0}")
    Optional<TodoDTO> findBytodo(String todo);

}
