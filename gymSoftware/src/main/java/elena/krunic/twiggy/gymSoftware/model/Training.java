package elena.krunic.twiggy.gymSoftware.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="training")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Training implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Long id; 

	@Column(name="name", nullable=false)
	private String name; 
	
	@Column(name="duration", nullable=false)
	private Date duration;
	
	@Column(name="reserved", nullable=false)
	private boolean reserved=true;
	
	@ManyToOne
	@JoinColumn(name="coach")
	private User coach;
	
	@ManyToOne
	@JoinColumn(name="client")
	private User client;
	
	
}
