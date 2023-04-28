package com.example.crudstudentsmanagement;

import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserTests {

	@Autowired
	private UserService userService;


}
