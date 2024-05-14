package org.sparta.spring_basic_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoRequestDto {
    private String title;
    private String content;
    private String manager;
    private String password;
}
