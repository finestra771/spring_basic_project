package org.sparta.spring_basic_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sparta.spring_basic_project.dto.TodoRequestDto;
import org.sparta.spring_basic_project.dto.TodoResponseDto;
import org.sparta.spring_basic_project.entity.Todo;
import org.sparta.spring_basic_project.repository.TodoRepository;
import org.sparta.spring_basic_project.service.TodoService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private TodoRequestDto todoRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoRequestDto = new TodoRequestDto();
        todoRequestDto.setId(1);
        todoRequestDto.setTitle("Test Title");
        todoRequestDto.setContent("Test Content");
        todoRequestDto.setManager("test@example.com");
        todoRequestDto.setPassword("windows1");
    }

    @Test
    void testCreateTodo() {
        // given
        Todo todo = new Todo();
        when(todoRepository.save(any())).thenReturn(todo);

        // when
        TodoResponseDto responseDto = todoService.createTodo(todoRequestDto);

        // then
        assertNotNull(responseDto);
        // Add more assertions as needed
    }

    @Test
    void testGetTodoById() {
        // given
        Todo todo = new Todo();
        when(todoRepository.findById(any())).thenReturn(Optional.of(todo));

        // when
        TodoResponseDto responseDto = todoService.getTodoById(1);

        // then
        assertNotNull(responseDto);
        // Add more assertions as needed
    }

    @Test
    void testGetAllTodos() {
        // given
        Todo todo = new Todo();
        when(todoRepository.findAllByOrderByCreatedAtDesc()).thenReturn(Collections.singletonList(todo));

        // when
        List<TodoResponseDto> responseDtos = todoService.getAllTodos();

        // then
        assertFalse(responseDtos.isEmpty());
        // Add more assertions as needed
    }
    @Test
    void testUpdateTodoTitleWithCorrectPassword() {
        // given
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setTitle("New Title");
        todoRequestDto.setPassword("Correct Password");

        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        when(todoRepository.save(any())).thenReturn(todo);

        // when
        int updatedId = todoService.updateTodoTitle(id, todoRequestDto);

        // then
        assertEquals(id, updatedId);
        assertEquals(todoRequestDto.getTitle(), todo.getTitle());
    }

    @Test
    void testUpdateTodoTitleWithIncorrectPassword() {
        // given
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setTitle("New Title");
        todoRequestDto.setPassword("Incorrect Password");

        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));

        // when, then
        assertThrows(IllegalArgumentException.class, () -> {
            todoService.updateTodoTitle(id, todoRequestDto);
        });
    }

    @Test
    void testUpdateTodoContentWithCorrectPassword() {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setContent("New Content");
        todoRequestDto.setPassword("Correct Password");
        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo)); //eq(id)
        when(todoRepository.save(any())).thenReturn(todo);
        int updatedId = todoService.updateTodoContent(id, todoRequestDto);
        assertEquals(id, updatedId);
        assertEquals(todoRequestDto.getContent(), todo.getContent());
    }

    @Test
    void testUpdateTodoContentWithIncorrectPassword() {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setContent("New Content");
        todoRequestDto.setPassword("Incorrect Password");
        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        assertThrows(IllegalArgumentException.class, () -> {
            todoService.updateTodoContent(id, todoRequestDto);
        });
    }

    @Test
    void testUpdateTodoManagerWithCorrectPassword() {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setManager("test@example.com");
        todoRequestDto.setPassword("Correct Password");
        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        when(todoRepository.save(any())).thenReturn(todo);
        int updatedId = todoService.updateTodoManager(id, todoRequestDto);
        assertEquals(id, updatedId);
        assertEquals(todoRequestDto.getManager(), todo.getManager());
    }

    @Test
    void testUpdateTodoManagerWithIncorrectPassword() {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setManager("test@example.com");
        todoRequestDto.setPassword("Incorrect Password");
        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        assertThrows(IllegalArgumentException.class, () -> {
            todoService.updateTodoManager(id, todoRequestDto);
        });
    }

    @Test
    void testDeleteTodoWithCorrectPassword() {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setManager("test@example.com");
        todoRequestDto.setPassword("Correct Password");
        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        when(todoRepository.save(any())).thenReturn(todo);
        int deletedId=todoService.deleteTodo(id, todoRequestDto);
        assertEquals(id, deletedId);
    }
    @Test
    void testDeleteTodoWithIncorrectPassword() {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setManager("test@example.com");
        todoRequestDto.setPassword("Incorrect Password");
        Todo todo = new Todo();
        todo.setId(id);
        todo.setPassword("Correct Password");
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        assertThrows(IllegalArgumentException.class, () -> {
            todoService.deleteTodo(id, todoRequestDto);
        });
    }


}
