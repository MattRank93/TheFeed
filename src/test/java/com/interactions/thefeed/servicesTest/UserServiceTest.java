package com.interactions.thefeed.servicesTest;
import static org.junit.Assert.assertTrue;

import com.interactions.thefeed.model.User;
import com.interactions.thefeed.requestobjects.UserRequest;
import com.interactions.thefeed.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userservice;


    @Test void testGetAllUsers() {

        ResponseEntity<?> allUsers = userservice.getAllUsers();
        List<User> users = (List<User>) allUsers.getBody();
        Assertions.assertThat(users).isNotNull()
                .isNotEmpty();

    }

    @Test void testGetOneUserBylastname() {

        UserRequest userRequest = new UserRequest(null,"5minrspp","dick@rank.com",null,"rank",null,null);

        User newUser = new User(
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getPhone(),
                "user"
        );

        ResponseEntity<?> UserResponse = userservice.getOneUser(userRequest);
        User user = (User) UserResponse.getBody();
        assert user != null;
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getLastname()).isEqualTo(newUser.getLastname());

    }



}
