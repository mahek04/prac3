package com.example.database_practical_3;

import com.example.database_practical_3.entity.User;
import com.example.database_practical_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
public class SpringbotMapPractical3Application {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String home() {
		return "Hello World! Database Integration is working. Total users: " + userService.getTotalUsers();
	}

	// Get all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// Get user by ID
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Create new user
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// Update user
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		try {
			User updatedUser = userService.updateUser(id, userDetails);
			return ResponseEntity.ok(updatedUser);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Delete user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Search users by name
	@GetMapping("/users/search")
	public List<User> searchUsers(@RequestParam String name) {
		return userService.searchUsersByName(name);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbotMapPractical3Application.class, args);
	}

}
