package com.playdata.todoapp.todo.domain.entity;

import com.playdata.todoapp.member.domain.entitiy.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Boolean isDone;
    private Integer likeCount;
    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "todo")
    private List<Like> likes;
}