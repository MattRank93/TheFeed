package com.interactions.thefeed.responseobjects;

import lombok.Value;

@Value
public class GeneralResponseObject {

    String status;
    String message;
    Object payload;


}
