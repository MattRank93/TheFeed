package com.interactions.thefeed.responseobjects;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
public class UserResponse {

    String username;
    String email;
    String firstname;
    String lastname;
    String phone;


}
