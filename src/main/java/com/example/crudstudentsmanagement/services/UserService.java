package com.example.crudstudentsmanagement.services;

import com.example.crudstudentsmanagement.models.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserModel getUserById(Long id);
    UserModel getUserByUsername(String username);
    void saveUser(UserModel user);
}
