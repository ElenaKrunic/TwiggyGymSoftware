package elena.krunic.twiggy.gymSoftware.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elena.krunic.twiggy.gymSoftware.dto.StringDTO;
import elena.krunic.twiggy.gymSoftware.dto.TrainingDTO;
import elena.krunic.twiggy.gymSoftware.repository.ReservationRepository;
import elena.krunic.twiggy.gymSoftware.repository.TrainingRepository;
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
	
	//client reserve training
	//posalji mejl o tom da je trening rezervisan 
	//staviti svoj test mejl za slanje mejla 
	
	/*
	@PostMapping("/scheduleTraining/{id}")
	public ResponseEntity<?> scheduleTraining(@RequestBody TrainingDTO dto, Principal principal) {
		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>  Uslo u metodu  <<<<<<<<<<<<<<<<<<<<<<<<<<<<"); 
			String message = trainingService.scheduleTraining(dto, "lelekrunic1@gmail.com");
			return new ResponseEntity<>(new StringDTO(message), HttpStatus.OK); 
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StringDTO(e.getMessage()), HttpStatus.OK); 
		}
	}
	*/
	
	@PostMapping(consumes="application/json", value="/scheduleTraining/{id}")
	public ResponseEntity<?> doTraining(@PathVariable("id") Long id, Principal principal) {
		try {
			String mess = trainingService.doTraining(id, "lelekrunic1@gmail.com"); 
			return new ResponseEntity<>(new StringDTO(mess), HttpStatus.OK); 
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	//get one 
	
	//get all 
	
	//get trainings for coach 
	
	//get trainings for client 
	
	//create training  - admin 
	
	
	//update training - admin/coach 
	
	//delete training 
	 
}
