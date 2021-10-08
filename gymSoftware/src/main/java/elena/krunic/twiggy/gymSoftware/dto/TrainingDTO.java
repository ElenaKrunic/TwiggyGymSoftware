package elena.krunic.twiggy.gymSoftware.dto;

import java.util.Date;

import elena.krunic.twiggy.gymSoftware.model.Training;
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
	private double price; 
	//private UserDTO user; 
	private Long coachID;
	private Long clientID;

	public TrainingDTO(Training training) {
		this.id = training.getId();
		this.name = training.getName(); 
		this.duration = training.getDuration().toString(); 
		this.reserved = training.isReserved();
		this.price = training.getPrice(); 
		this.coachID = training.getCoach().getId(); 
	}
}
