package com.playdata.todoapp.todo.domain.response;

import com.playdata.todoapp.member.domain.dto.MemberDto;
import com.playdata.todoapp.todo.domain.entity.Todo;

public record TodoResponse(Long id, String title, String content, Boolean isDone, Integer likeCount, MemberDto member) {

    public static TodoResponse from(Todo todo){
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getIsDone(),
                todo.getLikeCount(),
                MemberDto.from(todo.getMember())
        );
    }
}
