package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
//    void printUser(User user);
}
