package elena.krunic.twiggy.gymSoftware.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainingDTO {

	private Long id; 
	private String name; 
	private String duration; 
	private boolean reserved; 
	//private UserDTO user; 
	private Long coachID;
	private Long clientID;
	
}
