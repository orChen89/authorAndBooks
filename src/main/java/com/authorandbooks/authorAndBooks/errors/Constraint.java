package com.authorandbooks.authorAndBooks.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Constraint {

    INVALID_DATE("The inserted date of current coupon is invalid"),

    ENTITY_NOT_EXISTS(" is not exists!"),

    ENTITY_ALREADY_EXISTS(" is already exists!");

    @Getter
    private final String errorMsg;
}
