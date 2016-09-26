package com.antonsyzko.service;

/**
 * Created by Admin on 16.09.2016.
 */

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }
}