package elena.krunic.twiggy.gymSoftware.service;

public interface EmailService {

	void scheduleTraining(String date, String coach, String trainingName, String emailTo);

}
