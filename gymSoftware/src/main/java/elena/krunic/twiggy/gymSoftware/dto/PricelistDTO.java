package elena.krunic.twiggy.gymSoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PricelistDTO {

	private Long id; 
	private String trainingType; 
	private double price; 
	private Integer numberOfTrainings; 
}
