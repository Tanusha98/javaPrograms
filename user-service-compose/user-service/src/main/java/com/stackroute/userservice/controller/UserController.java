package com.stackroute.userservice.controller;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.UserService;
import com.stackroute.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        User savedUser=userService.addUser(user);
        ResponseEntity responseEntity=new ResponseEntity<User>(savedUser, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("user")
    public ResponseEntity<?> getAllUsers(){
        List<User> userList=userService.getAllUsers();
        ResponseEntity responseEntity=new ResponseEntity< List<User> >(userList, HttpStatus.OK);
        return responseEntity;
    }
}
