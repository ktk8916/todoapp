package com.playdata.todoapp.todo.service;

import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.member.exception.MemberNotFoundException;
import com.playdata.todoapp.member.repository.MemberRepository;
import com.playdata.todoapp.todo.domain.entity.Todo;
import com.playdata.todoapp.todo.domain.request.TodoRequest;
import com.playdata.todoapp.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    public Long save(TodoRequest todoRequest){
        Member member = memberRepository
                .findById(todoRequest.memberId())
                .orElseThrow(() -> new MemberNotFoundException("id 그런 멤버 없다"));
        Todo todo = Todo.createTodo(todoRequest.title(), todoRequest.content());
        Todo saveTodo = todoRepository.save(todo);
        return saveTodo.getId();
    }
}
