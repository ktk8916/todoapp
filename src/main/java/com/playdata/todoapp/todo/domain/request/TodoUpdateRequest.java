package com.playdata.todoapp.todo.domain.request;

public record TodoUpdateRequest(String title, String content, Boolean isDone) {
}
