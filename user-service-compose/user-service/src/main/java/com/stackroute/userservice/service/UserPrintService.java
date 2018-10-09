package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserPrintService {
    public void printUser(User user){
        System.out.println(user);
    }
}
