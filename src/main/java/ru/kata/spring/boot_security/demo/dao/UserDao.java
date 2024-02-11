package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findByEmail(String user);

    void save(User user);

    User findById(Long id);

    void update(User user);

    void delete(User id);
}
