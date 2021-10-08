package elena.krunic.twiggy.gymSoftware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elena.krunic.twiggy.gymSoftware.dto.TrainingDTO;
import elena.krunic.twiggy.gymSoftware.model.Training;
import elena.krunic.twiggy.gymSoftware.model.User;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

	List<Training> findAllByCoach(User user);

}
