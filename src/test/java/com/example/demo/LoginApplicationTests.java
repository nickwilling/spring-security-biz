package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleDAO;
import com.example.demo.mapper.UserDAO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserRoleDAO;
import com.example.demo.models.RoleEnum;
import com.example.demo.utils.IdGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class LoginApplicationTests {
	@Autowired
	UserDAO userDAO;
	@Autowired
	UserRoleDAO userRoleDAO;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	AttemptDAO attemptDAO;
	@Test
	void IdGenerator() {
		System.out.println(IdGenerator.nextId());
	}
    @Test
	void user() {
		Optional<User> user = userDAO.getUserByUserName("13888888882");
		System.out.println(user);
	}

	@Test
	void role() {
//		List<UserRole> roleList = userRoleDAO.getUserRoleByUserId(452400545296458L);
////				.orElseThrow(() -> new UsernameNotFoundException("User Role Not Found with username: "));;
//		System.out.println(roleList);
//		HashSet<Role> roles = new HashSet<>();
//		roles.add(new Role(452400545296441L, RoleEnum.USER,0));
//		roles.add(new Role(452400545296442L, RoleEnum.ADMIN,0));
//		userRoleDAO.saveList(roles,452400545296451L);
		Role userRole = roleDAO.findByName(RoleEnum.USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	}

	@Test
	void saveUser(){
		User user = new User();
		user.setUserId(1L);
		user.setUserName("wwl");
		user.setEmail("111@");
		user.setPhone("1866666666");
		user.setPassword("123445");
		userDAO.saveUser(user);
	}

	@Test
	void attempt() {
		attemptDAO.save(new Attempt(IdGenerator.nextId(),"username"));
	}
}
