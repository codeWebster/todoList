package com.example.ToDoListManager.todoList;

import org.springframework.expression.spel.ast.NullLiteral;

public class CustomResponse <T>{
    public String status;
    public T data;

    public CustomResponse(String status, T data ){
        this.status = status;
        this.data = data;
    }
    public CustomResponse(){};
}
