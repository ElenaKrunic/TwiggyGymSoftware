package elena.krunic.twiggy.gymSoftware.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Long id; 
	
	@Column(name="firstname", nullable=false)
	private String firstname; 
	
	@Column(name="lastname", nullable=false)
	private String lastname; 
	
	@Column(name="email", nullable=false)
	private String email; 
	
	@Column(name = "password", nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="role_has_user", joinColumns= {@JoinColumn(name="user_id")}, inverseJoinColumns = {@JoinColumn(name="role_id")})
	private List<Role> roles;
	
	@ManyToOne
	private Pricelist pricelist; 
	
	@ManyToOne
	private Reservation reservation; 
	
	@OneToMany(mappedBy="coach")
	private List<Training> trainingsForCoach; 
	
	@OneToMany(mappedBy="client")
	private List<Training> trainingsForClient; 
	
	
}
