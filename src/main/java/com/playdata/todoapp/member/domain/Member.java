package com.playdata.todoapp.member.domain;

import com.playdata.todoapp.todo.domain.Todo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private Integer age;
    @OneToMany(mappedBy = "member")
    private List<Todo> todos;
}
