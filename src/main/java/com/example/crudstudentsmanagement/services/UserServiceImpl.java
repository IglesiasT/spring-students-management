package com.example.crudstudentsmanagement.services;

import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.repositories.UserRepository;
import com.example.crudstudentsmanagement.security.UserSecurity;
import com.example.crudstudentsmanagement.utils.exceptions.EmptyUsernameException;
import com.example.crudstudentsmanagement.utils.exceptions.EmptyPasswordException;
import com.example.crudstudentsmanagement.utils.exceptions.UserAlreadyExistsException;
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
    public void saveUser(UserModel user) throws UserAlreadyExistsException {
        // Check if username is empty
        if(user.getUsername().isBlank()){
            throw new EmptyUsernameException("Username can't be empty");
        }

        // Check if password is empty
        if(user.getPassword().isBlank()){
            throw new EmptyPasswordException("Password can't be empty");
        }

        // Check if user is already registered
        if(this.userRepository.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistsException("User already exists for this username");
        }

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
