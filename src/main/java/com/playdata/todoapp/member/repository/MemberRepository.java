package com.playdata.todoapp.member.repository;

import com.playdata.todoapp.member.domain.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByEmailAndPassword(String email, String password);
}
