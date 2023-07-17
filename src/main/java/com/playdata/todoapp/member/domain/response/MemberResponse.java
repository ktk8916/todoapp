package com.playdata.todoapp.member.domain.response;

import com.playdata.todoapp.member.domain.dto.MemberDto;
import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.todo.domain.dto.TodoDto;

import java.util.List;
import java.util.stream.Collectors;

public record MemberResponse(MemberDto member, List<TodoDto> todos) {

    public static MemberResponse from(Member member){
        return new MemberResponse(
                MemberDto.from(member),
                member.getTodos()
                        .stream()
                        .map(TodoDto::from)
                        .collect(Collectors.toList())
        );
    }
}
