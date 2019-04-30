package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import entities.User;
import entities.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// Create
	
	@PostMapping("/users")
	User newUser(@RequestBody User newUser) {
		// TODO check if user already exits
		return userRepository.save(newUser);
	}
	
	// Read 
	
	@GetMapping("/users")
	List<User> all() {
		return userRepository.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	User getOne(@PathVariable Long id) {

		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	// Update
	@PutMapping("/users/{id}")
	User updateUser(@PathVariable Long id, @RequestBody User newUser) {
		
		User oldUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		oldUser.setUsername(newUser.getUsername());
		return oldUser;
		
	}
	
	
	// Delete
	
	@DeleteMapping("/user/{id}")
	void delete(@PathVariable Long id) {
		// TODO check if user exits
		userRepository.deleteById(id);
	}
	
}
