package elena.krunic.twiggy.gymSoftware.service;

import java.util.List;

import elena.krunic.twiggy.gymSoftware.dto.TrainingDTO;

public interface TrainingService {

	String scheduleTraining(TrainingDTO dto, String name) throws Exception;

	String doTraining(Long id, String string) throws Exception;

	List<TrainingDTO> getTrainingsForCoach(String name) throws Exception;

	List<TrainingDTO> getTrainingsForClient(String name) throws Exception;

}
