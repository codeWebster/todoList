package com.example.ToDoListManager.todoList;

import jakarta.persistence.*;

@Entity
@Table
public class Task {
    @Id
    @SequenceGenerator(
            name="task_seq",
            sequenceName = "task_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_seq"
    )
    private int id;
    ///change
    private String taskName;
    private String taskDesc;
    private int category;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user ;

    public Task() {
    }

    public Task(String taskName, String taskDesc, int category) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.category = category;
    }


    public void setUser(Users user) {
        this.user = user;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


}
