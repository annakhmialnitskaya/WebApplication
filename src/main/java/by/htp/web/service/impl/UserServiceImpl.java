package by.htp.web.service.impl;

import by.htp.web.dao.UserDao;
import by.htp.web.dao.impl.UserDaoImpl;
import by.htp.web.domain.User;
import by.htp.web.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao;
	{
		dao = new UserDaoImpl();
	}

	@Override
	public User authorize(String login, String password) {
		User user = dao.read(login, password);
		return user;
	}

}
