package org.sparta.spring_basic_project.service;

import org.sparta.spring_basic_project.dto.TodoRequestDto;
import org.sparta.spring_basic_project.dto.TodoResponseDto;
import org.sparta.spring_basic_project.entity.Todo;
import org.sparta.spring_basic_project.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        Todo todo = new Todo(todoRequestDto);

        Todo savedTodo = todoRepository.save(todo);

        TodoResponseDto todoResponseDto = new TodoResponseDto(savedTodo);

        return todoResponseDto;
    }

    public TodoResponseDto getTodoById(int id) {
        Todo todo=findTodo(id);
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
        return todoResponseDto;
    }

    public List<TodoResponseDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDto> todoResponseDtos = new ArrayList<>();
        for (Todo todo : todos) {
            TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
            todoResponseDtos.add(todoResponseDto);
        }
        return todoResponseDtos;
    }

    @Transactional
    public int updateTodoTitle(Integer id, TodoRequestDto todoRequestDto) {
        Todo todo=findTodo(id);
        if(todo.getPassword().equals(todoRequestDto.getPassword())) {
            todo.setTitle(todoRequestDto.getTitle());
            todoRepository.save(todo);
            return todo.getId();
        }
        else{
            throw new IllegalArgumentException("Wrong password");
        }
    }

    @Transactional
    public int updateTodoContent(int id, TodoRequestDto todoRequestDto) {
        Todo todo=findTodo(id);
        if(todo.getPassword().equals(todoRequestDto.getPassword())) {
            todo.setContent(todoRequestDto.getContent());
            todoRepository.save(todo);
            return todo.getId();
        }
        else{
            throw new IllegalArgumentException("Wrong password");
        }
    }
    @Transactional
    public int updateTodoManager(int id, TodoRequestDto todoRequestDto) {
        Todo todo=findTodo(id);
        if(todoRequestDto.getPassword().equals(todo.getPassword())) {
            todo.setManager(todoRequestDto.getManager());
            todoRepository.save(todo);
            return todo.getId();
        }
        else{
            throw new IllegalArgumentException("Wrong password");
        }
    }
    public int deleteTodo(Integer id, TodoRequestDto todoRequestDto) {
        Todo todo=findTodo(id);
        if(todo.getPassword().equals(todoRequestDto.getPassword())) {
            todoRepository.delete(todo);
            return id;
        }
        else{
            throw new IllegalArgumentException("Wrong password");
        }
    }

    public Todo findTodo(Integer id) {
        Todo todo=todoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("선택한 객체가 존재하지 않습니다."));
        return todo;
    }

}
