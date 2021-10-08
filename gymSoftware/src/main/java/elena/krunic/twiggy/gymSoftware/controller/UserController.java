package elena.krunic.twiggy.gymSoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elena.krunic.twiggy.gymSoftware.model.User;
import elena.krunic.twiggy.gymSoftware.repository.UserRepository;
import elena.krunic.twiggy.gymSoftware.service.UserService;

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {

	@Autowired 
	UserService userService; 
	
	@Autowired
	UserRepository userRepository; 
 	
	//get one user 
	
	
	//get all users 
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	//get all client users for coach -> role client 
	
	//create user 
	
	//update user 
	
	//change password 
	
	//delete user 
	
}
