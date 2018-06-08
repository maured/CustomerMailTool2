package gde.example.simplerestexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/User")
public class UserController {

	private final UserDAO userDAO;
	
	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@GetMapping
	public Collection<User> listUsers(@RequestBody User user) {
		return userDAO.list();
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return userDAO.add(user);
	}
	
}
