package org.sparta.spring_basic_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.sparta.spring_basic_project.entity.Todo;

@Setter
@Getter
public class TodoResponseDto {
    private int id;
    private String title;
    private String content;
    private String manager;
    @JsonIgnore
    private String password;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.manager = todo.getManager();
        this.password = todo.getPassword();
    }
}
