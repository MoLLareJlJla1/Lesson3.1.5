package ru.kata.spring.boot_security.demo.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserRolesInitializer {
    private final UserService userService;
    private final RoleService roleService;

    @PostConstruct
    public void createUserWithRoles() {
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        Role role2 = new Role();
        role2.setName("ROLE_USER");

        role1 = roleService.saveRole(role1);
        role2 = roleService.saveRole(role2);
        Set<Role> set1 = new HashSet<>();
        set1.add(role1);
        Set<Role> set2 = new HashSet<>();
        set2.add(role2);

        User user1 = new User();
        user1.setName("Sergei");
        user1.setLastName("Safronov");
        user1.setAge(30);
        user1.setEmail("e");
        user1.setPassword("123");
        user1.setRoles(set1);

        User user2 = new User();
        user2.setName("Igor");
        user2.setLastName("Brizgunov");
        user2.setAge(31);
        user2.setEmail("w");
        user2.setPassword("123");
        user2.setRoles(set2);

        userService.save(user1);
        userService.save(user2);
    }
}