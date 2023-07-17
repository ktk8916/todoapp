package com.playdata.todoapp.member.domain.response;

import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.todo.domain.dto.TodoDto;

import java.util.List;
import java.util.stream.Collectors;

public record MemberResponse(Long id, String name, Integer age, List<TodoDto> todos) {

    public static MemberResponse from(Member member){
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getTodos()
                        .stream()
                        .map(TodoDto::from)
                        .collect(Collectors.toList())
        );
    }
}
