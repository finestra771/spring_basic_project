package org.sparta.spring_basic_project.service;

import org.sparta.spring_basic_project.dto.CommentRequestDto;
import org.sparta.spring_basic_project.dto.CommentResponseDto;
import org.sparta.spring_basic_project.entity.Comment;
import org.sparta.spring_basic_project.entity.Todo;
import org.sparta.spring_basic_project.repository.CommentRepository;
import org.sparta.spring_basic_project.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Todo todo = findTodoById(commentRequestDto.getTodoId());
        Comment comment = new Comment(commentRequestDto, todo);
        Comment savedComment = commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(savedComment);
        return commentResponseDto;
    }


    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto) {
        Todo todo=findTodoById(commentRequestDto.getTodoId());
        Comment comment = new Comment(commentRequestDto, todo);
        return new CommentResponseDto(commentRepository.save(comment));
    }

    public List<CommentResponseDto> getAllCommentsByTodoId(int todoId) {
        List<Comment> comments = commentRepository.findAllByTodoId(todoId);
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    public Todo findTodoById(int id) {
        return todoRepository.findById(id).orElseThrow(()->
        new IllegalArgumentException("없습니다.")
        );
    }

    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }
}
