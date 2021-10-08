package elena.krunic.twiggy.gymSoftware.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import elena.krunic.twiggy.gymSoftware.dto.TrainingDTO;
import elena.krunic.twiggy.gymSoftware.model.Training;
import elena.krunic.twiggy.gymSoftware.model.User;
import elena.krunic.twiggy.gymSoftware.repository.TrainingRepository;
import elena.krunic.twiggy.gymSoftware.repository.UserRepository;
import elena.krunic.twiggy.gymSoftware.service.EmailService;
import elena.krunic.twiggy.gymSoftware.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	EmailService emailService; 
	
	@Override
	public String scheduleTraining(TrainingDTO dto, String name) throws Exception {
		
		User client = userRepository.getById(dto.getClientID());
		User coach = userRepository.getById(dto.getCoachID()); 
		
		if(client == null || coach==null) {
			throw new Exception("Client/Coach does not exist!"); 
		}
		
		Training training = new Training(); 
		training.setClient(client);
		training.setCoach(coach);
		//training.setDuration(duration);
		training.setName(dto.getName());
		training.setReserved(dto.isReserved());
		
		//staviti od - do 
		String duration = dto.getDuration().replace("T", " "); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time = formatter.parse(duration);
		training.setDuration(time);
		
		trainingRepository.save(training); 
		//posalji mejl klijentu za zakazan trening 
		emailService.scheduleTraining(dto.getDuration(), training.getCoach().toString(), name, client.getEmail());
		
		return "Successfully saved training!"; 
		
	}

	@Override
	public String doTraining(Long id, String string) throws Exception {
		User user = userRepository.getById(id); 
		
		if(user == null) {
			throw new Exception("User ne postoji!"); 
		}
		
		Optional<Training> trainingOpt = trainingRepository.findById(id); 
		
		if(trainingOpt == null) {
			throw new Exception("Trening ne postoji!"); 
		}
		
		Training training = trainingOpt.get(); 
		
		training.setClient(user);
		trainingRepository.save(training); 
		emailService.scheduleTraining(training.getDuration().toString(), training.getCoach().getFirstname() , training.getName(), user.getEmail());
		
		return "Pogledaj mejl";
	}

}
