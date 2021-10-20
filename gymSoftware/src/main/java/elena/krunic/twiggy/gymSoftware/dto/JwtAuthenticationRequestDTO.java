package elena.krunic.twiggy.gymSoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationRequestDTO {
	
	private String username; 
	private String password; 

}
