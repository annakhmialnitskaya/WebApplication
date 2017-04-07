package by.htp.web.service;

import by.htp.web.domain.User;

public interface UserService {
	
	public User authorize(String login, String password);

}
