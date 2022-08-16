package com.interactions.thefeed.controller;

import com.interactions.thefeed.config.JwtTokenUtil;
import com.interactions.thefeed.model.User;
import com.interactions.thefeed.requestobjects.JwtRequest;
import com.interactions.thefeed.requestobjects.UserRequest;
import com.interactions.thefeed.responseobjects.GeneralResponseObject;
import com.interactions.thefeed.responseobjects.JwtResponse;
import com.interactions.thefeed.responseobjects.UserResponse;
import com.interactions.thefeed.services.JwtUserDetailsService;
import com.interactions.thefeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest inputUser) {
        return userService.register(inputUser);
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

    @PatchMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        userService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

}
