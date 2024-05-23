package org.sparta.spring_basic_project.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.sparta.spring_basic_project.entity.Comment;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {
    private int commentId;
    private String content;
    private String authorId;
    private int todoId;
}
