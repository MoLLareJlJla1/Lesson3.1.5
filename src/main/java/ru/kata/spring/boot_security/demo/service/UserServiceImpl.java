package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public void update(User user) {
        System.out.println(user.getPassword());
        if(!user.getPassword().startsWith("$2a$")){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        System.out.println(user.getPassword());
        userRepo.save(user);
    }


    @Override
    public void saveMethod(User user) {
        if (userRepo.findByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.saveAndFlush(user);
        }
    }

    @Override
    public User userById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }
}
