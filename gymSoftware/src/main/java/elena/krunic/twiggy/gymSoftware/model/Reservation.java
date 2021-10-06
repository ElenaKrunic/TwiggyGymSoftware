package elena.krunic.twiggy.gymSoftware.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Long id; 
	
	@Column(name="reserved", nullable=false)
	private boolean reserved; 
	
	@OneToMany(mappedBy="reservation")
	private List<User> users; 

}
