package org.sparta.spring_basic_project.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.validation.Valid;
import org.sparta.spring_basic_project.dto.TodoRequestDto;
import org.sparta.spring_basic_project.dto.TodoResponseDto;
import org.sparta.spring_basic_project.repository.TodoRepository;
import org.sparta.spring_basic_project.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("/register")
    public TodoResponseDto register(@RequestBody @Valid TodoRequestDto todoRequestDto){
        return todoService.createTodo(todoRequestDto);
    }

    @GetMapping("/view/{id}")
    public TodoResponseDto view(@PathVariable int id){
        return todoService.getTodoById(id);
    }

    @GetMapping("/view")
    public List<TodoResponseDto> viewAll(){
        return todoService.getAllTodos();
    }

    @PutMapping("/update/title/{id}")
    public int updateTitle(@PathVariable int id, @RequestBody @Valid TodoRequestDto todoRequestDto){
        return todoService.updateTodoTitle(id, todoRequestDto);
    }

    @PutMapping("/update/content/{id}")
    public int updateContent(@PathVariable int id, @RequestBody @Valid TodoRequestDto todoRequestDto){
        return todoService.updateTodoContent(id, todoRequestDto);
    }

    @PutMapping("/update/manager/{id}")
    public int updateManager(@PathVariable int id, @RequestBody @Valid TodoRequestDto todoRequestDto){
        return todoService.updateTodoManager(id, todoRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int id, @RequestBody TodoRequestDto todoRequestDto){
        return todoService.deleteTodo(id, todoRequestDto);
    }
}
