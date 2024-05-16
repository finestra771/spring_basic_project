package org.sparta.spring_basic_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sparta.spring_basic_project.entity.Todo;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class TodoResponseDto {
    private int id;
    private String title;
    private String content;
    private String manager;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.manager = todo.getManager();
    }
}
