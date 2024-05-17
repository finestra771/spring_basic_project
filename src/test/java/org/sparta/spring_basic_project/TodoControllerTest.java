package org.sparta.spring_basic_project;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sparta.spring_basic_project.controller.TodoController;
import org.sparta.spring_basic_project.dto.TodoRequestDto;
import org.sparta.spring_basic_project.dto.TodoResponseDto;
import org.sparta.spring_basic_project.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService; // TodoService에 대한 MockBean을 주입합니다.

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() throws Exception {
        TodoRequestDto todoRequestDto = new TodoRequestDto(1, "title", "content", "test@test.com", "1234");
        TodoResponseDto todoResponseDto = new TodoResponseDto();

        when(todoService.createTodo(any(TodoRequestDto.class))).thenReturn(todoResponseDto);

        mockMvc.perform(post("/todo/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(todoResponseDto)));
    }

    @Test
    public void testView() throws Exception {
        int id = 1;
        TodoResponseDto todoResponseDto = new TodoResponseDto();

        when(todoService.getTodoById(id)).thenReturn(todoResponseDto);

        mockMvc.perform(get("/todo/view/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(todoResponseDto)));
    }

    @Test
    public void testViewAll() throws Exception {
        List<TodoResponseDto> todos = Collections.singletonList(new TodoResponseDto());

        when(todoService.getAllTodos()).thenReturn(todos);

        mockMvc.perform(get("/todo/view"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(todos)));
    }

    @Test
    public void testUpdateTitle() throws Exception {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto(1, "title", "content", "test@test.com", "1234");
        int updateCount = 1;

        when(todoService.updateTodoTitle(eq(id), any(TodoRequestDto.class))).thenReturn(updateCount);

        mockMvc.perform(put("/todo/update/title")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(updateCount)));
    }

    @Test
    public void testUpdateContent() throws Exception {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto(1, "title", "content", "test@test.com", "1234");
        String password="1234";
        int updateCount = 1;

        when(todoService.updateTodoContent(eq(id), any(TodoRequestDto.class))).thenReturn(updateCount);

        mockMvc.perform(put("/todo/update/content")
                        .param("id", String.valueOf(id))
                        .param("password", password)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(updateCount)));
    }

    @Test
    public void testUpdateManager() throws Exception {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto(1, "title", "content", "test@test.com", "1234");
        int updateCount = 1;

        when(todoService.updateTodoManager(eq(id), any(TodoRequestDto.class))).thenReturn(updateCount);

        mockMvc.perform(put("/todo/update/manager")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(updateCount)));
    }

    @Test
    public void testDelete() throws Exception {
        int id = 1;
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        int deleteCount = 1;

        when(todoService.deleteTodo(eq(id), any(TodoRequestDto.class))).thenReturn(deleteCount);

        mockMvc.perform(delete("/todo/delete")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(deleteCount)));
    }
}
