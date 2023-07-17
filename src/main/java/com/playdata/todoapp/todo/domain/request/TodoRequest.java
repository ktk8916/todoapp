package com.playdata.todoapp.todo.domain.request;

import com.playdata.todoapp.todo.domain.entity.Todo;

public record TodoRequest(String title, String content, Long memberId) {

}
