package com.example.ToDoListManager.todoList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="api/v1/tododatabase")
public class TodoListController {
    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService){
        this.todoListService = todoListService;
    }

    @PostMapping("/create-task")
    public ResponseEntity<String> registerNewTask(@RequestBody Task task, @RequestParam("userName") String reqUserName, @RequestParam("userKey") String reqKey ){
        if(reqUserName.isEmpty() || reqKey.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Login");
        }
        else {
            return todoListService.addNewTask(task, reqUserName, reqKey);
        }
    }
    @PostMapping("/create-user")
    public ResponseEntity<String> registerNewUser(@RequestBody Users user) {
        return todoListService.addNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<CustomResponse<String>> loginUser(@RequestBody Users user){
        CustomResponse<String> cr = new CustomResponse<>();
        return todoListService.fetchUserKey(user);
    }

    @GetMapping("get-all-tasks")
    public ResponseEntity<CustomResponse<List<Task>>>  getAllTasks(@RequestParam("userName") String reqUserName){
        System.out.println("hey");
        return todoListService.getAllTasks(reqUserName);
    }

}
