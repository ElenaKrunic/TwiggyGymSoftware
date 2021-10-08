package elena.krunic.twiggy.gymSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elena.krunic.twiggy.gymSoftware.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String name);

}
