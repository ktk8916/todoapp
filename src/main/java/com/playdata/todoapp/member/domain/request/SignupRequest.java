package com.playdata.todoapp.member.domain.request;

import com.playdata.todoapp.member.domain.entitiy.Member;

public record SignupRequest(String email, String name, String password, Integer age) {
    public Member toEntity(){
        return Member
                .builder()
                .email(email)
                .name(name)
                .password(password)
                .age(age)
                .build();
    }
}
