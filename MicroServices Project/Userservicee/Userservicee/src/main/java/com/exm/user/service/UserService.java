package com.exm.user.service;

import com.exm.user.entity.User;

import java.util.List;

public interface UserService {

    //create user
    User createUser(User user);

    // get all user
    List<User> getAllUser();

    //get single user
    User getUser(String userId);

}
