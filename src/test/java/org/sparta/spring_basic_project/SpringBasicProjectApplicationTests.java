package org.sparta.spring_basic_project;



import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sparta.spring_basic_project.controller.TodoController;
import org.sparta.spring_basic_project.dto.TodoRequestDto;
import org.sparta.spring_basic_project.dto.TodoResponseDto;
import org.sparta.spring_basic_project.error.TodoNotFoundException;
import org.sparta.spring_basic_project.repository.TodoRepository;
import org.sparta.spring_basic_project.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class SpringBasicProjectApplicationTests {

    @InjectMocks
    private TodoController todoController;
    private WebTestClient webTestClient;
    private MockMvc mockMvc;
    @Mock
    private TodoService todoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this); // Mockito의 @Mock 어노테이션 처리를 위해 사용
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    void todoSuccess() throws Exception {
        TodoRequestDto request=todoRequest();
        TodoResponseDto response=todoResponse();

        doReturn(response).when(todoService).createTodo(any());
        String json = new Gson().toJson(request);
        System.out.println(json);
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/todo/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request)) // 요청 본문에 TodoRequestDto 객체 추가
        );

        // 응답의 구조와 값이 올바른지 확인하는 코드는 그대로 유지
        MvcResult mvcResult = resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value(response.getTitle()))
                .andExpect(jsonPath("content").value(response.getContent()))
                .andExpect(jsonPath("manager").value(response.getManager()))
                .andReturn();
    }

    @Test
    void todoNotFound() throws Exception {
        int nonExistingTodoId = 9999; // 존재하지 않는 Todo 항목의 ID
        doThrow(new TodoNotFoundException("Todo not found with id: " + nonExistingTodoId))
                .when(todoService).getTodoById(nonExistingTodoId);

        mockMvc.perform(MockMvcRequestBuilders.get("/todo/{id}", nonExistingTodoId))
                .andExpect(status().isNotFound());
    }
    private TodoRequestDto todoRequest(){
        return TodoRequestDto.builder()
                .title("test1")
                .content("testcontent")
                .manager("test@example.com")
                .password("test")
                .build();
    }

    private TodoResponseDto todoResponse(){
        return TodoResponseDto.builder()
                .title("test1")
                .content("testcontent")
                .manager("test@example.com")
                .build();
    }

}
