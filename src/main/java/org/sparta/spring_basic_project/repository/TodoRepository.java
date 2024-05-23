package org.sparta.spring_basic_project.repository;

import org.sparta.spring_basic_project.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByOrderByCreatedAtDesc();
    Todo findTodoById(int id);
}
