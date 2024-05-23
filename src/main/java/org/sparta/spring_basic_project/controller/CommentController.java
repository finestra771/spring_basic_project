package org.sparta.spring_basic_project.controller;

import org.sparta.spring_basic_project.dto.CommentRequestDto;
import org.sparta.spring_basic_project.dto.CommentResponseDto;
import org.sparta.spring_basic_project.entity.Todo;
import org.sparta.spring_basic_project.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/{todoId}")
    public List<CommentResponseDto> getAllCommentsByTodoId(@PathVariable int todoId) {
        return commentService.getAllCommentsByTodoId(todoId);
    }

    @PostMapping("/comment/{id}")
    public CommentResponseDto createComment(@PathVariable int id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto);
    }

    @PutMapping("/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable int commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(commentRequestDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable int commentId) {
       commentService.deleteComment(commentId);
    }
}
