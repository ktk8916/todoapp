package com.playdata.todoapp.member.service;

import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.member.domain.request.LoginRequest;
import com.playdata.todoapp.member.domain.request.SignupRequest;
import com.playdata.todoapp.member.domain.response.LoginResponse;
import com.playdata.todoapp.member.exception.MemberNotFoundException;
import com.playdata.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(SignupRequest signupRequest){
        memberRepository.save(signupRequest.toEntity());
    }

    public LoginResponse login(LoginRequest loginRequest){
        Member member = memberRepository
                .findMemberByEmailAndPassword(
                    loginRequest.email(),
                    loginRequest.password())
                .orElseThrow(() -> new MemberNotFoundException("그런 멤버 없다"));

        return LoginResponse.from(member);
    }
}
