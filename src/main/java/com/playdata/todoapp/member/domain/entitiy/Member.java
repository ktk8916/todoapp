package com.playdata.todoapp.member.domain.entitiy;

import com.playdata.todoapp.todo.domain.entity.Todo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private Integer age;
    @OneToMany(mappedBy = "member")
    private List<Todo> todos = new ArrayList<>();
}
