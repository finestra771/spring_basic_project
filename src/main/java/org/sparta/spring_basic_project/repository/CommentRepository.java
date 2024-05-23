package org.sparta.spring_basic_project.repository;

import org.sparta.spring_basic_project.dto.CommentResponseDto;
import org.sparta.spring_basic_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<CommentResponseDto> findAllById(int postId);
    Comment save(Comment comment);

    List<Comment> findAllTodoById(int id);

    List<Comment> findAllByTodoId(int todoId);
}
