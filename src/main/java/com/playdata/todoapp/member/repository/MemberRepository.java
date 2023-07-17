package com.playdata.todoapp.member.repository;

import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.todo.domain.entity.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByEmailAndPassword(String email, String password);
    @Query("select m from Member m left join fetch m.todos")
    List<Member> findAllFetch(Pageable pageable);
}
