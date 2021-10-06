package elena.krunic.twiggy.gymSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elena.krunic.twiggy.gymSoftware.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
