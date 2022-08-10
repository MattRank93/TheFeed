package com.interactions.thefeed.controllersTest;

import com.interactions.thefeed.controller.UserController;
import com.interactions.thefeed.model.User;
import com.interactions.thefeed.requestobjects.UserRequest;
import com.interactions.thefeed.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    MockMvc mockMvc;


    @MockBean
    UserService userService;


    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.userController).build();
        UserRequest userRequest = new UserRequest(null,"5minrspp","dick@rank.com",null,"rank",null,null);
        User userOne = new User(
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getPhone(),
                "user"
        );

        User userTwo = new User(
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getPhone(),
                "user"
        );
        List<User> users = new ArrayList<>();
        users.add(userOne);
        users.add(userTwo);

    }

    @Test void testGetAllUsers() {


    }

    @Test void testGetOneUserBylastname() {

//        UserRequest userRequest = new UserRequest(null,"5minrspp","dick@rank.com",null,"rank",null,null);
//
//        User newUser = new User(
//                userRequest.getUsername(),
//                userRequest.getEmail(),
//                userRequest.getPassword(),
//                userRequest.getFirstname(),
//                userRequest.getLastname(),
//                userRequest.getPhone(),
//                "user"
//        );
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_JSON);
//        ResponseEntity<?> responseEntity = new ResponseEntity<>(
//                newUser,
//                header,
//                HttpStatus.OK
//        );
//
//        doReturn(responseEntity).when(userService.getOneUser(userRequest));
//
//        ResponseEntity<?> UserResponse = userService.getOneUser(userRequest);
//        User user = (User) UserResponse.getBody();
//        assert user != null;
//        Assertions.assertThat(user).isNotNull();
//        Assertions.assertThat(user.getLastname()).isEqualTo(newUser.getLastname());

    }



}
