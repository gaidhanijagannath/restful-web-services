package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	// public User save(User user)
	// public User findOne(int id)

	// UserDaoService > Static List
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 0;

	static {

		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));

	}

	// public List<User> findAll()
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		
		user.setId(++usersCount);
		users.add(user);
		return user;
		
	}

	
	public User findOne(int id) {
		

		Predicate<? super User> predicate = user -> user.getId() == id; 
		// return users.stream().filter(predicate).findFirst().get(); // its throwing some random whitelabel error so how can we improve it ?
		
		return users.stream().filter(predicate).findFirst().orElse(null);

		/*
		 * for (User user : users) {
		 *
		 * if(user.getId() == id) { return user; } } return null;
		 */

	}

	public void deleteById(int id) {
		

		Predicate<? super User> predicate = user -> user.getId() == id; 
		// return users.stream().filter(predicate).findFirst().get(); 
		// its throwing some random whitelabel error so how can we improve it ?
		
		users.removeIf(predicate);
		
	}
	 

}
