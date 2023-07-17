package com.playdata.todoapp.member.domain.response;

import com.playdata.todoapp.member.domain.entitiy.Member;

public record LoginResponse(Long id, String name, Integer age) {

    public static LoginResponse from(Member member){
        return new LoginResponse(
                member.getId(),
                member.getName(),
                member.getAge()
        );
    }
}
