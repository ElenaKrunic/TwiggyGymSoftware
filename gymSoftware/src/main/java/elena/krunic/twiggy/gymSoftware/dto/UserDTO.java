package elena.krunic.twiggy.gymSoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	private Long id; 
	private String firstname; 
	private String lastname; 
	private String password; 
	private String email; 
	

}
