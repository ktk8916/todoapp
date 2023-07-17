package com.playdata.todoapp.todo.controller;

import com.playdata.todoapp.todo.domain.request.TodoRequest;
import com.playdata.todoapp.todo.domain.request.TodoUpdateRequest;
import com.playdata.todoapp.todo.domain.response.TodoResponse;
import com.playdata.todoapp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> findByTitle(
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        return todoService.findByTitle(title, page);
    }

    @GetMapping
    public List<TodoResponse> searchTodoByContent(
            @RequestParam(value = "content", required = false, defaultValue = "") String content,
            @RequestParam(value = "isDone", required = false) Boolean isDone,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        return todoService.searchTodoByContent(content, isDone, page);
    }

    @PostMapping
    public Long save(@RequestBody TodoRequest todoRequest){
        return todoService.save(todoRequest);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable("id") Long id,
            @RequestBody TodoUpdateRequest todoUpdateRequest){
        todoService.update(id, todoUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        todoService.delete(id);
    }
}
