package com.interactions.thefeed.services;

import com.interactions.thefeed.model.User;
import com.interactions.thefeed.repository.UserRepository;
import com.interactions.thefeed.requestobjects.UserRequest;
import com.interactions.thefeed.responseobjects.GeneralResponseObject;
import com.interactions.thefeed.responseobjects.UserResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public ResponseEntity<?> register(@NotNull UserRequest userRequest) {

        Optional<User> optUser = userRepo.findOneUserByEmail(userRequest.getEmail());

        if (!optUser.isEmpty()) {
            GeneralResponseObject generalResponseObject = new GeneralResponseObject("fail", "Email address " + userRequest.getEmail() + " existed", userRequest);
            return new ResponseEntity<>(generalResponseObject, HttpStatus.BAD_REQUEST);
        }

        try {
            User user = new User(
                    userRequest.getUsername(),
                    userRequest.getEmail(),
                    userRequest.getPassword(),
                    userRequest.getFirstname(),
                    userRequest.getLastname(),
                    userRequest.getPhone(),
                    "user"
            );
            userRepo.save(user);
            UserResponse userResponseObject = new UserResponse(userRequest.getUsername(),
                    userRequest.getEmail(),
                    userRequest.getFirstname(),
                    userRequest.getLastname(),
                    userRequest.getPhone());

            return new ResponseEntity<>(userResponseObject, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<?> getAllUsers() {

        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getOneUser(@NotNull UserRequest userRequest) {

        try {
            if (userRequest.getEmail() != null) {
                Optional<User> user = userRepo.findOneUserByEmail(userRequest.getEmail());
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else if (userRequest.getUsername() != null) {
                User user = userRepo.findOneUserByName(userRequest.getUsername());
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(userRequest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity<?> Login(@NotNull UserRequest userRequest){
//
//        Optional<User> usersOptional = userRepo.findOneUserByEmail(userRequest.getEmail());
//
//    }


}
