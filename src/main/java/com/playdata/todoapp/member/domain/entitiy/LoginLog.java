package com.playdata.todoapp.member.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class LoginLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;

    public static LoginLog createLoginLog(Member member){
        LoginLog loginLog = new LoginLog();
        loginLog.member = member;
        loginLog.createdAt = LocalDateTime.now();
        loginLog.endedAt = loginLog.createdAt.plusMinutes(10);
        return loginLog;
    }
}
