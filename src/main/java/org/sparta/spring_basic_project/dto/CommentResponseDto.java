package org.sparta.spring_basic_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sparta.spring_basic_project.entity.Comment;
import org.sparta.spring_basic_project.entity.Todo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private int commentId;
    private String content;
    private String authorId;
    private int todoId;

    public CommentResponseDto(Comment comment) {
        this.commentId=comment.getId();
        this.content=comment.getContent();
        this.authorId=comment.getAuthorId();
    }
}
