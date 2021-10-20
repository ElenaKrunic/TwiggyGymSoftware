package elena.krunic.twiggy.gymSoftware.service.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import elena.krunic.twiggy.gymSoftware.dto.UserDTO;
import elena.krunic.twiggy.gymSoftware.model.User;
import elena.krunic.twiggy.gymSoftware.repository.UserRepository;
import elena.krunic.twiggy.gymSoftware.security.SecurityConfiguration;
import elena.krunic.twiggy.gymSoftware.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	SecurityConfiguration configuration; 

	@Override
	public String registerCoach(UserDTO userDTO) throws Exception {
		User user = userRepository.findByEmail(userDTO.getEmail()); 
		
		if(user == null) {
			throw new Exception("User already exists!"); 
		}
		
		user = new User(); 
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(configuration.passwordEncoder().encode(userDTO.getPassword()));
		userRepository.save(user); 
		
		return "Coach added";
	}

	@Override
	public String registerClient(UserDTO userDTO) throws Exception {
	User user = userRepository.findByEmail(userDTO.getEmail()); 
		
		if(user == null) {
			throw new Exception("User already exists!"); 
		}
		
		user = new User(); 
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(configuration.passwordEncoder().encode(userDTO.getPassword()));
		
		userRepository.save(user); 
		
		return "Client added";
	}

	@Override
	public String registerAdmin(UserDTO userDTO) throws Exception {
		User user = userRepository.findByEmail(userDTO.getEmail()); 
		
		if(user == null) {
			throw new Exception("User already exists!"); 
		}
		
		user = new User(); 
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(configuration.passwordEncoder().encode(userDTO.getPassword()));
		
		userRepository.save(user); 
		
		return "Coach added";
	}

	@Override
	public String editProfile(UserDTO userDTO, String name) throws Exception {
		User user = userRepository.findByEmail(name);
		
		if(user == null) {
			throw new Exception("user does not exist");
		}
		
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(configuration.passwordEncoder().encode(userDTO.getPassword()));
		
		userRepository.save(user);
		return "User info updated"; 
	}

	@Override
	public UserDTO myProfile(String name) throws Exception {
		User user = userRepository.findByEmail(name); 
		
		if(user == null) {
			throw new Exception("User does not exist"); 
		}
		
		UserDTO tmp = new UserDTO(); 
		tmp.setId(user.getId());
		user.setEmail(user.getEmail());
		user.setFirstname(user.getFirstname());
		user.setLastname(user.getLastname());
		user.setPassword(user.getPassword());
		
		return tmp; 
	}



}
