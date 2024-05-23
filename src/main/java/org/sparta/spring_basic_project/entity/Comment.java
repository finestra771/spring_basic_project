package org.sparta.spring_basic_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.sparta.spring_basic_project.dto.CommentRequestDto;

@Entity
@Table(name="comment")
@Getter
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String content;
    private String authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="todo_id")
    private Todo todo;

    public Comment(CommentRequestDto commentRequestDto, Todo todo){
        this.content=commentRequestDto.getContent();
        this.authorId=commentRequestDto.getAuthorId();
        this.todo=todo;
    }

    public Comment() {
    }
}
