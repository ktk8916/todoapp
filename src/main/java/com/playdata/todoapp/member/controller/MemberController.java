package com.playdata.todoapp.member.controller;

import com.playdata.todoapp.member.domain.request.LoginRequest;
import com.playdata.todoapp.member.domain.request.SignupRequest;
import com.playdata.todoapp.member.domain.response.LoginResponse;
import com.playdata.todoapp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sighup")
    public void signup(@RequestBody SignupRequest signupRequest){
        memberService.signup(signupRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return memberService.login(loginRequest);
    }
}
