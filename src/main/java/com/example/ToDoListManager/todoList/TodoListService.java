package com.example.ToDoListManager.todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TodoListService {
    private final TaskRepository taskRepository;
    private final UserListRepository userListRepository;

    @Autowired
    public TodoListService(TaskRepository taskRepository, UserListRepository userListRepository){
        this.taskRepository = taskRepository;
        this.userListRepository = userListRepository;
    }


    public ResponseEntity<String> addNewTask(Task task, String reqUserName, String reqKey) {
        Optional<Users> reqUser = userListRepository.findUserByUserName(reqUserName);
        if(reqUser.isPresent()) {
            if(reqKey.equals(reqUser.get().getUserName()  + (reqUser.get().getPassword().length()/reqUser.get().getUserName().length()))) {
                task.setUser(reqUser.get());
                taskRepository.save(task);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task added");
            }
            else{
                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Bad URL");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Bad URL");
        }

    }

    public ResponseEntity<String> addNewUser(Users user) {
        //System.out.print("%s",user.getUserName());
        try{
            userListRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User register. Please login");
        }
        catch(DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already taken");
        }

    }
    public ResponseEntity<CustomResponse<String>> fetchUserKey(Users user){
        Optional<Users> reqUser = userListRepository.findUserByUserName(user.getUserName());
        CustomResponse<String> cr = new CustomResponse<>();
        if(reqUser.isPresent()){
            if(user.getPassword().equals(reqUser.get().getPassword()) ){
                cr.data =  reqUser.get().getKey();
                cr.status = "You are logged in";
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(cr);
            }
            else{
                cr.status = "Wrong Password";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cr);
            }
        }
        else{
            cr.status = "user not present" ;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cr);
        }

    }

    public ResponseEntity<CustomResponse<List<Task>>> getAllTasks(String reqUserKey) {
        Optional<Users> reqUser = userListRepository.findUserByUserName(reqUserKey);
        CustomResponse<List<Task>> cr = new CustomResponse<>();
        if(reqUser.isPresent()) {
            List<Task> taskList = taskRepository.findAllByUserId(reqUser.get().getUserId());
            cr.status = "List found";
            cr.data = taskList;
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(cr);
        }
        else{
            cr.status = "Bad URL";
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(cr);
        }
    }
}
