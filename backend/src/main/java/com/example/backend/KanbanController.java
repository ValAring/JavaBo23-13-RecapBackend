package com.example.backend;

import com.example.backend.model.Todo;
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
    public Todo addTodo(@RequestBody Todo newTodo){
        return kanbanService.addTodo(newTodo);
    }
}
