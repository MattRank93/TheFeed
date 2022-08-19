package com.interactions.thefeed.controller;

import com.interactions.thefeed.requestobjects.UserRequest;
import com.interactions.thefeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

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
    public ResponseEntity<?> getOneUserParam( UserRequest inputUser) {
        return userService.getOneUser(inputUser);
    }

    //Todo:Implement roll restriction
    @GetMapping("/get-one-user-delete")
    public ResponseEntity<?> getOneUserAndDelete( UserRequest inputUser) {
        return userService.getOneUserAndDelete(inputUser);
    }


}
