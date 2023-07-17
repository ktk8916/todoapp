package com.playdata.todoapp.member.domain.dto;

import com.playdata.todoapp.member.domain.entitiy.Member;

public record MemberDto(Long id, String name, Integer age) {

    public static MemberDto from(Member member){
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getAge());
    }
}
