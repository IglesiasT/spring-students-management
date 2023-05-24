package com.example.crudstudentsmanagement;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.repositories.UserRepository;
import com.example.crudstudentsmanagement.services.UserServiceImpl;
import com.example.crudstudentsmanagement.utils.exceptions.EmptyAuthoritiesException;
import com.example.crudstudentsmanagement.utils.exceptions.EmptyPasswordException;
import com.example.crudstudentsmanagement.utils.exceptions.EmptyUsernameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void userCannotSignUpWithEmptyUsername() {
        // Arrange
        UserModel user1 = new UserModel("", "password1", List.of(AuthorityModel.Admin()));
        UserModel user2 = new UserModel(" ", "password2", List.of(AuthorityModel.Student()));

        // Act and Assert
        assertThrows(EmptyUsernameException.class, () -> userService.saveUser(user1));
        assertThrows(EmptyUsernameException.class, () -> userService.saveUser(user2));
    }

    @Test
    public void userCannotSignUpWithEmptyPassword() {
        // Arrange
        UserModel user = new UserModel("username", "", List.of(AuthorityModel.Student()));

        // Act and Assert
        assertThrows(EmptyPasswordException.class, () -> userService.saveUser(user));

    }

    @Test
    public void userCannotSignUpWithEmptyAuthority() {
        // Arrange
        UserModel user = new UserModel("username", "password", Collections.emptyList());

        // Act and Assert
        assertThrows(EmptyAuthoritiesException.class, () -> userService.saveUser(user));

    }
}

