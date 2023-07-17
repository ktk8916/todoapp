package com.playdata.todoapp.member.repository;

import com.playdata.todoapp.member.domain.entitiy.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

    Optional<LoginLog> findFirstByMemberIdOrderByIdDesc(Long memberId);
}
