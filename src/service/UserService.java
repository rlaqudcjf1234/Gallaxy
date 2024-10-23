package service;

import dto.UserDTO;

public interface UserService {

	public UserDTO selectUser(String userId);
	
	public int insertUser(UserDTO dto);

	public int updateUser(UserDTO dto);
}
