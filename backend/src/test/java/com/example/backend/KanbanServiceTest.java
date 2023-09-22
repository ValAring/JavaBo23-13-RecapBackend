package com.example.backend;

import com.example.backend.model.Todo;
import com.example.backend.model.TodoRepository;
import com.example.backend.model.TodoStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KanbanServiceTest {

    TodoRepository todoRepository = mock(TodoRepository.class);
    IdService idService = mock(IdService.class);
    KanbanService kanbanService = new KanbanService(todoRepository,idService);
    Todo t1 = new Todo("123", "myTodo", TodoStatus.OPEN);
    Todo t2 = new Todo("123", "myTodo", TodoStatus.OPEN);

    @BeforeAll
    static void setUp() {
    }

    @Test
    void findAll() {
        //GIVEN
        List<Todo> todoList = List.of(t1);
        when(todoRepository.findAll()).thenReturn(todoList);

        //WHEN
        List<Todo> actual = kanbanService.findAll();

        //THEN
        List<Todo> expected = List.of(new Todo("123", "myTodo", TodoStatus.OPEN));

        verify(todoRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void addTodo() {
        //GIVEN
        when(idService.randomId()).thenReturn("123");
        when(todoRepository.save(t1)).thenReturn(t1);

        //WHEN
        Todo actual = kanbanService.addTodo(t2);

        //THEN
        Todo expected = new Todo("123", "myTodo", TodoStatus.OPEN);
        verify(todoRepository).save(t1);
        verify(idService).randomId();
        assertEquals(expected, actual);
    }

    @Test
    void getTodoById() {
        //GIVEN
        String id = "123";

        //when(kanbanService.getTodoById(id)).thenReturn(t1);
        when(todoRepository.findById(id)).thenReturn(Optional.of(t1));

        //WHEN
        Todo actual = kanbanService.getTodoById(id);

        //THEN
        Todo expected = new Todo("123", "myTodo", TodoStatus.OPEN);
        verify(todoRepository).findById(id);
        assertEquals(expected, actual);
    }

    @Test
    void updateTodo() {
        String todoId = "123";
        Todo updatedTodo = new Todo("123", "Updated Todo", TodoStatus.IN_PROGRESS);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(updatedTodo));

        // WHEN
        kanbanService.updateTodo(updatedTodo);
        Todo actual = todoRepository.findById(todoId).get();

        // THEN
        //verify(todoRepository).findById(todoId); Brauch ich hier ja gar nicht
        verify(todoRepository).save(updatedTodo);
        assertEquals(updatedTodo, actual);
    }

    @Test
    void delete() {
        // GIVEN
        String todoId = "123";

        // WHEN
        kanbanService.delete(todoId);

        // THEN
        verify(todoRepository).deleteById(todoId);
    }
}