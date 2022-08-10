package com.interactions.thefeed.requestobjects;


import lombok.Value;

import java.util.ArrayList;
import java.util.UUID;

@Value
public class UserRequest {
    String username;
    String password;
    String email;
    String firstname;
    String lastname;
    String phone;
    UUID id;


}
