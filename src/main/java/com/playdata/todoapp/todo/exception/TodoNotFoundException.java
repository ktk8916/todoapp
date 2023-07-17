package com.playdata.todoapp.todo.exception;

public class TodoNotFoundException extends NullPointerException{
    public TodoNotFoundException(String s) {
        super(s);
    }

    public TodoNotFoundException() {
        super("TODOS_NOT_FOUND");
    }
}
