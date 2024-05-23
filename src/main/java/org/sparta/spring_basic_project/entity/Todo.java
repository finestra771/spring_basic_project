package org.sparta.spring_basic_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sparta.spring_basic_project.dto.TodoRequestDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="todo")
public class Todo extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String manager;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    public Todo(){
        this.id=0;
        this.title="";
        this.content="";
        this.manager="";
        this.password="";
    }

    public Todo(TodoRequestDto todoRequestDto){
        this.title = todoRequestDto.getTitle();
        this.content = todoRequestDto.getContent();
        this.manager = todoRequestDto.getManager();
        this.password = todoRequestDto.getPassword();
    }

}
