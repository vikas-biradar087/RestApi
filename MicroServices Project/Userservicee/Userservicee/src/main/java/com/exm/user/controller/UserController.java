package com.exm.user.controller;

import com.exm.user.entity.User;
import com.exm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUser(){

        List<User> getAllUserr = userService.getAllUser();
        return ResponseEntity.ok(getAllUserr);

    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){

        User user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

}
