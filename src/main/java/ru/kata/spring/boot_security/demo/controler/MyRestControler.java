package ru.kata.spring.boot_security.demo.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyRestControler {
    private final UserService userServicel;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userServicel.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getId(@PathVariable("id") Long id) {
        try {
            User user = userServicel.userById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchUserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        try {
            userServicel.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchUserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> add(@RequestBody User user) {
        userServicel.saveMethod(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userServicel.update(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
