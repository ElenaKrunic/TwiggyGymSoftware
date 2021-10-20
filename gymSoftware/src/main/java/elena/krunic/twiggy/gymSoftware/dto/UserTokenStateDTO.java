package elena.krunic.twiggy.gymSoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserTokenStateDTO {
	
	private String accessToken; 
	private Long expiresIn; 
	
	public UserTokenStateDTO(String accessToken, long expiresIn) {
		this.accessToken = accessToken; 
		this.expiresIn = expiresIn;
	}
	

}
