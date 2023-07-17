package com.playdata.todoapp.member.controller;

import com.playdata.todoapp.member.domain.request.LoginRequest;
import com.playdata.todoapp.member.domain.request.SignupRequest;
import com.playdata.todoapp.member.domain.response.LoginResponse;
import com.playdata.todoapp.member.domain.response.MemberResponse;
import com.playdata.todoapp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        return memberService.findAll(page);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest signupRequest){
        memberService.signup(signupRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return memberService.login(loginRequest);
    }
}
