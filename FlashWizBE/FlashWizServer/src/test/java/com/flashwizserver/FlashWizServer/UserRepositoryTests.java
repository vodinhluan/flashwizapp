package com.flashwizserver.FlashWizServer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.flashwizserver.model.Role;
import com.flashwizserver.model.User;
import com.flashwizserver.repository.UserRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUserWithOneRole()
	{
		Role roleStaff = entityManager.find(Role.class, 1);
		User userTest = new User("tranphuluan@gmail.com","tranphuluan123", "Tran Phu Luan");
		userTest.addRole(roleStaff);

		User savedUser = userRepository.save(userTest);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void addUser() {
		User user = new User();
		user.setId(5);
		user.setEmail("testabbc@gmail.com");
		user.setName("John");
		user.setPassword("123");
		userRepository.save(user);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = userRepository.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

}