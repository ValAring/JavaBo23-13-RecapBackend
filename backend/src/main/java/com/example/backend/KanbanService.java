package com.example.backend;

import com.example.backend.model.Todo;
import com.example.backend.model.TodoRepository;
import com.example.backend.model.TodoStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KanbanService {
    private final TodoRepository todoRepository;
    private final IdService idService;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo newTodo) {

        Todo todo = new Todo(
                idService.randomId(),
                newTodo.getDescription(),
                newTodo.getStatus()
        );
        return todoRepository.save(todo);
    }

    public Todo getTodoById(String id){
        return todoRepository.findById(id).get();
    }

    public void updateTodo(Todo thisTodo){
        Todo updateTodo = getTodoById(thisTodo.getId());
        updateTodo.setDescription(thisTodo.getDescription());
        updateTodo.setStatus(thisTodo.getStatus());

        todoRepository.save(updateTodo);
    }

    public void delete(String id){
        todoRepository.deleteById(id);
    }
}
