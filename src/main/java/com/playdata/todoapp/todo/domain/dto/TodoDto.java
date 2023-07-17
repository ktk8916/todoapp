package com.playdata.todoapp.todo.domain.dto;

import com.playdata.todoapp.todo.domain.entity.Todo;

import java.util.List;

public record TodoDto(Long id, String title, String content, Boolean isDone, Integer likeCount) {
    public static TodoDto from(Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getIsDone(),
                todo.getLikeCount()
        );
    }
}
