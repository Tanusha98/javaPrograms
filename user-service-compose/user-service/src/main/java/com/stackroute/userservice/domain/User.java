package com.stackroute.userservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity             //values @Value are also there like entities
public class User {

    @Id
    private int userId;
    private String userName;
    private int userAge;

    public User() {
    }

    public User(int userId, String userName, int userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
