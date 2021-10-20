package elena.krunic.twiggy.gymSoftware.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

}
