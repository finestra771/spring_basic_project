package org.sparta.spring_basic_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sparta.spring_basic_project.dto.TodoRequestDto;

@Entity
@Getter
@Setter
@Table(name="todo")
@NoArgsConstructor
public class Todo extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(unique = true, nullable = false)
    private String content;
    @Column(unique = true, nullable = false)
    private String manager;
    @Column(unique = true, nullable = false)
    private String password;

    public Todo(TodoRequestDto todoRequestDto){
        this.title = todoRequestDto.getTitle();
        this.content = todoRequestDto.getContent();
        this.manager = todoRequestDto.getManager();
        this.password = todoRequestDto.getPassword();
    }

}
