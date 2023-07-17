package com.playdata.todoapp.todo.domain.entity;

import com.playdata.todoapp.member.domain.entitiy.Member;
import jakarta.persistence.*;
import lombok.*;

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

    public static Todo createTodo(String title, String content){
        Todo todo = new Todo();
        todo.title = title;
        todo.content = content;
        todo.isDone = false;
        todo.likeCount = 0;
        return todo;
    }
}
