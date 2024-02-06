package ru.kata.spring.boot_security.demo.testCreateUserByDb;

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
public class firstTest {
    private final UserService userService;
    private final RoleService roleService;

    @PostConstruct
    public void createUserWithRoles() {
        Role role1 = new Role(1L, "ROLE_ADMIN");
        Role role2 = new Role(2L, "ROLE_USER");

        roleService.saveRole(role1);
        roleService.saveRole(role2);

        Set<Role> set1=new HashSet<>();
        set1.add(role1);
        Set<Role> set2=new HashSet<>();
        set2.add(role2);

        User user1=new User(1L,"Sergei","Safronov",30,"e","123",set1);
        User user2=new User(2L,"Igor","Brizgunov",31,"w","123",set2);

        userService.saveMethod(user1);
        userService.saveMethod(user2);


    }
}
//    private final RoleService roleService;
//    private final UserService userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @PostConstruct
//    public void run() {
//        Role roleUser = new Role();
//        roleUser.setName("ROLE_USER");
//        Role roleAdmin = new Role();
//        roleAdmin.setName("ROLE_ADMIN");
//
//        roleService.saveRole(roleUser);
//        roleService.saveRole(roleAdmin);
//        User admin = new User("Sergei", "Safronov", 30, "admin", "123", Set.of(roleAdmin, roleUser));
//        User user = new User("Igor", "Brizgunov", 31, "user", "123", Set.of(roleUser));
//        userService.saveUser(admin);
//        userService.saveUser(user);
//
//    }
