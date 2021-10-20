package elena.krunic.twiggy.gymSoftware.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elena.krunic.twiggy.gymSoftware.dto.JwtAuthenticationRequestDTO;
import elena.krunic.twiggy.gymSoftware.dto.StringDTO;
import elena.krunic.twiggy.gymSoftware.dto.UserDTO;
import elena.krunic.twiggy.gymSoftware.dto.UserTokenStateDTO;
import elena.krunic.twiggy.gymSoftware.model.User;
import elena.krunic.twiggy.gymSoftware.repository.UserRepository;
import elena.krunic.twiggy.gymSoftware.security.TokenUtils;
import elena.krunic.twiggy.gymSoftware.service.UserService;

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager; 
	
	@Autowired
	UserDetailsService userDetailsService; 

	@Autowired 
	UserService userService; 
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	TokenUtils tokenUtils; 
	
	
	@PostMapping("/login")
	public ResponseEntity<UserTokenStateDTO> login(@RequestBody JwtAuthenticationRequestDTO request, HttpServletResponse response) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
			
			SecurityContextHolder.getContext().setAuthentication(token);
			
			User user = userRepository.findByEmail(request.getUsername());
			String jwt = tokenUtils.generateToken(user.getEmail()); 
			int expiresIn = tokenUtils.getExpiredIn(); 
			
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(new UserTokenStateDTO());
	}
 	
	@PostMapping("/register-coach")
	public ResponseEntity<?> registerCoach(@RequestBody UserDTO userDTO) {
		try {
			String register = userService.registerCoach(userDTO); 
			return new ResponseEntity<>(new StringDTO(register), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StringDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register-client")
	public ResponseEntity<?> registerClient(@RequestBody UserDTO userDTO) {
		try {
			String register = userService.registerClient(userDTO); 
			return new ResponseEntity<>(new StringDTO(register), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StringDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register-admin")
	public ResponseEntity<?> registerAdmin(@RequestBody UserDTO userDTO) {
		try {
			String register = userService.registerAdmin(userDTO); 
			return new ResponseEntity<>(new StringDTO(register), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StringDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}


	
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
