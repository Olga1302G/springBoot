package com.example.dao;



import com.example.model.User;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User getUser(long id);
    void addUser(User user);
    void deleteUser(User user);
    void editUser(User user);


}