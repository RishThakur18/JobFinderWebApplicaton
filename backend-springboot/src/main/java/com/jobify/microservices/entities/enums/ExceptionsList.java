package com.jobify.microservices.entities.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionsList {
    InvalidObject(1,"Either object or its required field(s) is/are null ");

    private int code;
    private String message;

}
