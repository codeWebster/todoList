package com.example.ToDoListManager.todoList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserListRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findUserByUserName(String userName);

}
