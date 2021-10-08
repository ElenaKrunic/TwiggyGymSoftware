package elena.krunic.twiggy.gymSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elena.krunic.twiggy.gymSoftware.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

}
