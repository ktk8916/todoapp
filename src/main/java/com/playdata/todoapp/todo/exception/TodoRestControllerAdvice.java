package com.playdata.todoapp.todo.exception;

import com.playdata.todoapp.member.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TodoRestControllerAdvice {
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String runtimeExceptionHandler(TodoNotFoundException e){
        return e.getMessage();
    }
}
