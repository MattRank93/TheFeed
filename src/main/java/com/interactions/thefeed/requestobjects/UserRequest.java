package com.interactions.thefeed.requestobjects;


import com.interactions.thefeed.model.Role;
import lombok.Value;

import java.util.ArrayList;
import java.util.Set;
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
    Set<String> roles;


}
