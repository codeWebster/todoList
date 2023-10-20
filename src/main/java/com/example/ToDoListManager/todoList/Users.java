package com.example.ToDoListManager.todoList;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table //(name = "users")
public class Users {

    @Id
    @SequenceGenerator(
            name="user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    private int id;
    @Column(unique = true)
    private String userName;
    private String password;
    @Transient
    private String key;

    public int getUserId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return (this.userName + (this.password.length()/this.userName.length()));
    }



    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    @OneToMany (targetEntity = Task.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks ;//= new ArrayList<TodoList>();


}

