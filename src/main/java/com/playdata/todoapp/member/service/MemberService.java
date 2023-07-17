package com.playdata.todoapp.member.service;

import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.member.domain.request.LoginRequest;
import com.playdata.todoapp.member.domain.request.SignupRequest;
import com.playdata.todoapp.member.domain.response.LoginResponse;
import com.playdata.todoapp.member.domain.response.MemberResponse;
import com.playdata.todoapp.member.exception.DuplicateEmailException;
import com.playdata.todoapp.member.exception.MemberNotFoundException;
import com.playdata.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(SignupRequest signupRequest){
        try {
            memberRepository.save(signupRequest.toEntity());
        } catch (Exception e){
            //catch 머로 잡냐
            throw new DuplicateEmailException();
        }
    }

    public LoginResponse login(LoginRequest loginRequest){
        Member member = memberRepository
                .findMemberByEmailAndPassword(
                    loginRequest.email(),
                    loginRequest.password())
                .orElseThrow(MemberNotFoundException::new);

        return LoginResponse.from(member);
    }

    public List<MemberResponse> findAll(Integer page){
        final int DEFAULT_PAGE_SIZE = 10;
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        List<Member> members = memberRepository.findAllFetch(pageable);
       return  members
               .stream()
               .map(MemberResponse::from)
               .collect(Collectors.toList());
    }
}
