package org.sparta.spring_basic_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.sparta.spring_basic_project.dto.TodoRequestDto;
import org.sparta.spring_basic_project.entity.Todo;

public class TestRequestDto {

    @Test
    public void testConstructorWithTodoRequestDto() {
        // given
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setTitle("Test Title");
        todoRequestDto.setContent("Test Content");
        todoRequestDto.setManager("Test Manager");
        todoRequestDto.setPassword("Test Password");

        // when
        Todo todo = new Todo(todoRequestDto);

        // then
        assertEquals(todoRequestDto.getTitle(), todo.getTitle());
        assertEquals(todoRequestDto.getContent(), todo.getContent());
        assertEquals(todoRequestDto.getManager(), todo.getManager());
        assertEquals(todoRequestDto.getPassword(), todo.getPassword());
    }
}