package org.sparta.spring_basic_project.error;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String s) {
        super(s);
    }
    public TodoNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
