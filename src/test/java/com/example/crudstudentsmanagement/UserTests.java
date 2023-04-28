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
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void createUserTest() {
		// Arrange
		String userName = "testUser1";
		String password = "123";
		UserModel newUser = new UserModel(userName, password);

		// Act
		this.userService.saveUser(newUser);

		// Assert
		UserModel user = userService.getUserById(newUser.getId());
		assertNotNull(user);
		assertEquals(userName, user.getUsername());
		assertEquals(password, user.getPassword());
	}

	@Test
	void encodePasswordTest(){
		// Arrange
		String userName = "testUser2";

		// Act
		String password = this.encoder.encode("4566");
		UserModel newUser = new UserModel(userName, password);
		this.userService.saveUser(newUser);

		// Assert
		assertEquals(newUser.getPassword(), this.userService.getUserById(newUser.getId()).getPassword());
	}

}
