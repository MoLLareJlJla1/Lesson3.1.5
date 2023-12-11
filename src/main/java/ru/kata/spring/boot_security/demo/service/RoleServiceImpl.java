package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepo;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
private final RoleRepo roleRepo;

    @Override
    public void saveRole(Role role) {
        roleRepo.save(role);
    }
}
