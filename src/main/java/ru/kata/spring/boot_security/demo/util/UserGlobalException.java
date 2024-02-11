package ru.kata.spring.boot_security.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalException {
    @ExceptionHandler
    private ResponseEntity<UserErrorException> userNot(UserNotFoundException e) {
        UserErrorException userIncorrectData = new UserErrorException("User with this id wasn't found");
        return new ResponseEntity<>(userIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorException> userNotFound(BadJsonException e) {
        UserErrorException userIncorrectData = new UserErrorException("JSON request does not match");
        return new ResponseEntity<>(userIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorException> userFound(UserFoundException e) {
        UserErrorException userIncorrectData = new UserErrorException("This user already exists");
        return new ResponseEntity<>(userIncorrectData, HttpStatus.BAD_REQUEST);
    }
}
