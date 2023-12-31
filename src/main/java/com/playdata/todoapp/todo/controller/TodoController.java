package com.playdata.todoapp.todo.controller;

import com.playdata.todoapp.todo.domain.request.TodoRequest;
import com.playdata.todoapp.todo.domain.request.TodoUpdateRequest;
import com.playdata.todoapp.todo.domain.response.TodoResponse;
import com.playdata.todoapp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/title")
    public List<TodoResponse> findByTitle(
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        return todoService.findByTitle(title, page);
    }

    @GetMapping()
    public List<TodoResponse> searchByContent(
            @RequestParam(value = "content", required = false, defaultValue = "") String content,
            @RequestParam(value = "isDone", required = false) Boolean isDone,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        return todoService.searchByContent(content, isDone, page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody TodoRequest todoRequest){
        return todoService.save(todoRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(
            @PathVariable("id") Long id,
            @RequestBody TodoUpdateRequest todoUpdateRequest){
        todoService.update(id, todoUpdateRequest);
    }

    @PostMapping("/{todoId}/like/{memberId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void like(
            @PathVariable("todoId") Long todoId,
            @PathVariable("memberId") Long memberId){
        todoService.like(todoId, memberId);
    }

    @PutMapping("/{todoId}/check/{memberId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void complete(
            @PathVariable("todoId") Long todoId,
            @PathVariable("memberId") Long memberId){
        todoService.complete(todoId, memberId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id){
        todoService.delete(id);
    }
}
