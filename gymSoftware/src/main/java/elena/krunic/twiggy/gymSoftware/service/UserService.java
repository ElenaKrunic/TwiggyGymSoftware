package elena.krunic.twiggy.gymSoftware.service;

import elena.krunic.twiggy.gymSoftware.dto.UserDTO;

public interface UserService {

	String registerCoach(UserDTO userDTO) throws Exception;

	String registerClient(UserDTO userDTO) throws Exception;

	String registerAdmin(UserDTO userDTO) throws Exception;

}
