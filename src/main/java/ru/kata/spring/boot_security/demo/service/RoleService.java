package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
    Role saveRole(Role role);
    Role findById(long l);
}
