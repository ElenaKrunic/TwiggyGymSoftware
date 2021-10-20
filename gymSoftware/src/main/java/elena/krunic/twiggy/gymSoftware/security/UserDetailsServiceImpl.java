package elena.krunic.twiggy.gymSoftware.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import elena.krunic.twiggy.gymSoftware.model.Role;
import elena.krunic.twiggy.gymSoftware.model.User;
import elena.krunic.twiggy.gymSoftware.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User with username does not exist '%s'.", email));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for(Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}

}
