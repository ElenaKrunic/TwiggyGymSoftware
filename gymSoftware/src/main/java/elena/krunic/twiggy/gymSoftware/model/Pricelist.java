package elena.krunic.twiggy.gymSoftware.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pricelist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Long id; 
	
	@Column(name="trainingType", nullable=false)
	private String trainingType; 
	
	@Column(name="price", nullable=false)
	private double price; 
	
	@Column(name="numberOfTrainings", nullable=false)
	private Integer numberOfTrainings; 
	
	@OneToMany(mappedBy="pricelist")
	private List<User> users; 
}
