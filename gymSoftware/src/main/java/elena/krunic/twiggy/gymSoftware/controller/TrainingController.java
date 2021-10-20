package elena.krunic.twiggy.gymSoftware.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elena.krunic.twiggy.gymSoftware.dto.StringDTO;
import elena.krunic.twiggy.gymSoftware.dto.TrainingDTO;
import elena.krunic.twiggy.gymSoftware.model.Training;
import elena.krunic.twiggy.gymSoftware.model.User;
import elena.krunic.twiggy.gymSoftware.repository.ReservationRepository;
import elena.krunic.twiggy.gymSoftware.repository.TrainingRepository;
import elena.krunic.twiggy.gymSoftware.repository.UserRepository;
import elena.krunic.twiggy.gymSoftware.service.ReservationService;
import elena.krunic.twiggy.gymSoftware.service.TrainingService;

@RestController
@RequestMapping("api/trainings")
public class TrainingController {

	@Autowired
	ReservationRepository reservationRepository; 
	
	@Autowired
	ReservationService reservationService; 
	
	@Autowired
	TrainingRepository trainingRepository; 
	
	@Autowired 
	TrainingService trainingService; 
	
	@Autowired 
	UserRepository userRepository; 
	

	@GetMapping(value="/{id}")
	public ResponseEntity<TrainingDTO> getOne(@PathVariable("id") Long id) {
		Training training = trainingRepository.getById(id); 
		
		if(training == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<TrainingDTO>(new TrainingDTO(training), HttpStatus.OK);
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<List<TrainingDTO>> getAll() {
		List<Training> trainings = trainingRepository.findAll(); 
		List<TrainingDTO> dtos = new ArrayList<>();
		
		for(Training training : trainings) {
			dtos.add(new TrainingDTO(training)); 
		}
		
		return new ResponseEntity<List<TrainingDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/trainingsForCoach")
    @PreAuthorize("hasAuthority('COACH')")
	public ResponseEntity<?> trainingsForCoach(Principal principal) {
		try {
			//coach je krunicele@gmail.com 
			//List<TrainingDTO> dtos = trainingService.getTrainingsForCoach(principal.getName()); 
			List<TrainingDTO> dtos = trainingService.getTrainingsForCoach("krunicele@gmail.com"); 
			return new ResponseEntity<>(dtos, HttpStatus.OK); 
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StringDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/trainingsForClient")
    @PreAuthorize("hasAuthority('CLIENT')")
	public ResponseEntity<?> trainingsForClient(Principal principal) {
		try {
			//List<TrainingDTO> dtos = trainingService.getTrainingsForClient(principal.getName()); 
			List<TrainingDTO> dtos = trainingService.getTrainingsForClient("lelekrunic1@gmail.com"); 
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StringDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/priceForTraining/{id}")
    @PreAuthorize("hasAuthority('COACH') || hasAuthority('CLIENT') || hasAuthority('ADMIN')")
	public ResponseEntity<?> getPriceForTraining(@PathVariable("id") Long id) {
		try {
			Training training = trainingRepository.getById(id); 
			
			if(training == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
			}
			
			double price = training.getPrice(); 
			
			return new ResponseEntity<>("Price for training is " + price, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//client reserve training and gets an email about training reservation - client 
	@PostMapping(consumes="application/json", value="/scheduleTraining/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
	public ResponseEntity<?> doTraining(@PathVariable("id") Long id, Principal principal) {
		try {
			String mess = trainingService.doTraining(id, "lelekrunic1@gmail.com"); 
			return new ResponseEntity<>(new StringDTO(mess), HttpStatus.OK); 
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	
	@PostMapping(consumes="application/json")
    @PreAuthorize("hasAuthority('COACH') || hasAuthority('ADMIN')")
	public ResponseEntity<TrainingDTO> saveTraining(@RequestBody TrainingDTO dto, Principal principal) throws Exception {
		
		//User user = userRepository.findByEmail(principal.getName()); 
		User user = userRepository.findByEmail("krunicele@gmail.com"); 
		
		if(user == null) {
			throw new Exception("User not found"); 
		}
		
		Training training = new Training(); 
		
		String timeExamination = dto.getDuration().replace("T", " ");
		SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time=formatter6.parse(timeExamination);
		training.setDuration(time);
		
		training.setName(dto.getName());
		training.setPrice(dto.getPrice());
		training.setReserved(dto.isReserved());
		training.setCoach(user);
		
		trainingRepository.save(training); 
		
		return new ResponseEntity<TrainingDTO>(new TrainingDTO(training), HttpStatus.OK);
		
	}
	
	@PutMapping(consumes="application/json")
    @PreAuthorize("hasAuthority('COACH') || hasAuthority('ADMIN')")
	public ResponseEntity<TrainingDTO> editTraining(@RequestBody TrainingDTO dto, Principal principal) throws Exception {
		
		//User user = userRepository.findByEmail(principal.getName()); 
		User user = userRepository.findByEmail("krunicele@gmail.com"); 
		
		if(user == null) {
			throw new Exception("User not found"); 
		}
		
		Training training = trainingRepository.getById(dto.getId()); 
		
		String timeExamination = dto.getDuration().replace("T", " ");
		SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time=formatter6.parse(timeExamination);
		training.setDuration(time);
		
		training.setName(dto.getName());
		training.setPrice(dto.getPrice());
		training.setReserved(dto.isReserved());
		training.setCoach(user);
		
		trainingRepository.save(training); 
		
		return new ResponseEntity<TrainingDTO>(new TrainingDTO(training), HttpStatus.OK);
		
	}
	
	
	@DeleteMapping(value="/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteTraining(@PathVariable("id") Long id) {
		Training training = trainingRepository.getById(id); 
		
		if(training != null) {
			trainingRepository.delete(training);
			return new ResponseEntity<Void>(HttpStatus.OK); 
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	 
} 
