package com.playdata.todoapp.member.service;

import com.playdata.todoapp.member.domain.entitiy.LoginLog;
import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.member.domain.request.LoginRequest;
import com.playdata.todoapp.member.domain.request.SignupRequest;
import com.playdata.todoapp.member.domain.response.LoginResponse;
import com.playdata.todoapp.member.domain.response.MemberResponse;
import com.playdata.todoapp.member.exception.DuplicateEmailException;
import com.playdata.todoapp.member.exception.MemberNotFoundException;
import com.playdata.todoapp.member.exception.NotValidLoginException;
import com.playdata.todoapp.member.repository.LoginLogRepository;
import com.playdata.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final LoginLogRepository loginLogRepository;

    public Member findById(Long id) {
        return memberRepository
                .findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    public void isValidLogin(Long memberId){
        LoginLog loginLog = loginLogRepository
                .findFirstByMemberIdOrderByIdDesc(memberId)
                .orElseThrow(MemberNotFoundException::new);

        if(LocalDateTime.now().isAfter(loginLog.getEndedAt())){
            throw new NotValidLoginException();
        }
    }
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

        LoginLog loginLog = LoginLog.createLoginLog(member);

        loginLogRepository.save(loginLog);

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
