package ru.kata.spring.boot_security.demo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
        return user;
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
            userRepo.save(user);
        }
    }

    @Override
    public User userById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }
}
