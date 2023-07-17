package com.playdata.todoapp.todo.service;

import com.playdata.todoapp.member.domain.entitiy.Member;
import com.playdata.todoapp.member.exception.MemberNotFoundException;
import com.playdata.todoapp.member.repository.MemberRepository;
import com.playdata.todoapp.todo.domain.entity.Like;
import com.playdata.todoapp.todo.domain.entity.Todo;
import com.playdata.todoapp.todo.domain.request.TodoRequest;
import com.playdata.todoapp.todo.domain.request.TodoUpdateRequest;
import com.playdata.todoapp.todo.domain.response.TodoResponse;
import com.playdata.todoapp.todo.exception.TodoNotFoundException;
import com.playdata.todoapp.todo.repository.LikeRepository;
import com.playdata.todoapp.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    private final LikeRepository likeRepository;
    private final int DEFAULT_PAGE_SIZE = 20;

    public Todo findTodoById(Long id) {
        return todoRepository
                .findById(id)
                .orElseThrow(TodoNotFoundException::new);
    }
    private Member findMemberById(Long id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(MemberNotFoundException::new);
        return member;
    }

    public List<TodoResponse> findByTitle(String title, Integer page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        List<Todo> todos = todoRepository.findAllFetchByTitleContaining("%"+title+"%", pageable);

        return todos
                .stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }
    public List<TodoResponse> searchByContent(String content, Boolean isDone, Integer page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);

        List<Todo> todos = isDone==null ?
                todoRepository.findAllFetchByContentContaining("%"+content+"%", pageable):
                todoRepository.findAllFetchByContentContainingAndIsDone("%"+content+"%", isDone, pageable);

        return todos
                .stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }
    public Long save(TodoRequest todoRequest){
        Member member = findMemberById(todoRequest.memberId());
        Todo todo = Todo.createTodo(todoRequest.title(), todoRequest.content(), member);
        Todo saveTodo = todoRepository.save(todo);
        return saveTodo.getId();
    }
    public void complete(Long todoId, Long memberId){
        //대충 멤버검증 로직
        Todo todo = findTodoById(todoId);
        todo.completeTodo();
    }

    public void update(Long id, TodoUpdateRequest todoUpdateRequest) {
        Todo todo = findTodoById(id);

        todo.updateTodo(
                todoUpdateRequest.title(),
                todoUpdateRequest.content(),
                todoUpdateRequest.isDone());
    }

    public void delete(Long id){
        Todo todo = findTodoById(id);
        todoRepository.delete(todo);
    }


    public void like(Long todoId, Long memberId) {
        Todo todo = findTodoById(todoId);
        Member member = findMemberById(memberId);

        Like like = Like.createLike(todo, member);
        likeRepository.save(like);
    }
}
