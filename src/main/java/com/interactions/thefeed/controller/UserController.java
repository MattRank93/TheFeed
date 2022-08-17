package com.interactions.thefeed.controller;

import com.interactions.thefeed.requestobjects.UserRequest;
import com.interactions.thefeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get-one-user")
    public ResponseEntity<?> getOneUser(@RequestParam UserRequest inputUser) {
        return userService.getOneUser(inputUser);
    }

    @GetMapping("/get-one-user-param")
    public ResponseEntity<?> getOneUserParam( UserRequest inputUser) {
        return userService.getOneUser(inputUser);
    }


}
