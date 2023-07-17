package com.playdata.todoapp.todo.repository;

import com.playdata.todoapp.todo.domain.entity.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t left join fetch t.member m where t.title like :title")
    List<Todo> findAllFetchByTitleContaining(@Param("title") String name, Pageable pageable);
}
