package com.example.crudstudentsmanagement.services;

import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.repositories.UserRepository;
import com.example.crudstudentsmanagement.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserModel getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(UserModel user) {
        // Encode user pw before save the user in the db
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = this.userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User " + username + " not found ");
        }

        return new UserSecurity(user);
    }
}
