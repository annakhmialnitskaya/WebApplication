package by.htp.web.dao;

import by.htp.web.domain.User;

public interface UserDao {
	
	public User read(String login, String password);

}
