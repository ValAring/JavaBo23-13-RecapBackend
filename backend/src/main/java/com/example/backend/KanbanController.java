package com.example.backend;

import com.example.backend.model.Todo;
import com.example.backend.model.TodoStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class KanbanController {

private final KanbanService kanbanService;
    public KanbanController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @GetMapping()
    public List<Todo> getAllTodos(){
        return this.kanbanService.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo newTodo){
        return kanbanService.addTodo(newTodo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id){
        return this.kanbanService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public void putTodo(@RequestBody Todo updateTodo){
        this.kanbanService.updateTodo(updateTodo);
    }

    /*@DeleteMapping("/{id}")
    public void deleteTodo(@RequestBody Todo deleteTodo){
        this.kanbanService.deleteTodo(deleteTodo);
    }*/
}
