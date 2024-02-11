package ru.kata.spring.boot_security.demo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.util.BadJsonException;
import ru.kata.spring.boot_security.demo.util.UserFoundException;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        if (user == null) {
            throw new UserFoundException();
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        if ((user.getId() == null) && userDao.findByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
        } else {
            throw new UserFoundException();
        }
    }

    @Override
    public void update(Long id, User user) {
        if (user.getId() == id || user.getId() == null) {
            User us = userDao.findById(id);
            us.setId(id);
            us.setName(user.getName());
            us.setLastName(user.getLastName());
            us.setAge(user.getAge());
            us.setPassword(user.getPassword());
            us.setEmail(user.getEmail());
            us.setRoles(user.getRoles());
            userDao.update(us);
        } else {
            throw new BadJsonException();
        }
    }

    @Override
    public void delete(Long id) {
        User user = userDao.findById(id);
        userDao.delete(user);
    }
}
