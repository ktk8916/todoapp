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
            @RequestParam("title") String title,
            @RequestParam("page") Integer page){
        return todoService.findByTitle(title, page);
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
