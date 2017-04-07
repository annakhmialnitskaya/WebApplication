package by.htp.web.command;

import static by.htp.web.util.Permanents.PAGE_ERROR;
import static by.htp.web.util.Permanents.PAGE_WELCOME_ADMIN;
import static by.htp.web.util.Permanents.PAGE_WELCOME_USER;
import static by.htp.web.util.Permanents.REQUEST_PARAM_USER_LOGIN;
import static by.htp.web.util.Permanents.REQUEST_PARAM_USER_PASS;
import static by.htp.web.util.Permanents.SESSION_PARAM_USER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.web.domain.Book;
import by.htp.web.domain.User;
import by.htp.web.service.BookService;
import by.htp.web.service.UserService;
import by.htp.web.service.impl.BookServiceImpl;
import by.htp.web.service.impl.UserServiceImpl;

public class AuthCommand implements Command {

	private UserService serviceUser;
	private BookService serviceBook;

	{
		serviceUser = new UserServiceImpl();
		serviceBook = new BookServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String page = null;

		String login = request.getParameter(REQUEST_PARAM_USER_LOGIN);
		String password = request.getParameter(REQUEST_PARAM_USER_PASS);

		User user = serviceUser.authorize(login, password);
		if (user != null) {

			HttpSession session = request.getSession();
			session.setAttribute(SESSION_PARAM_USER, user);

			List<Book> books = serviceBook.listBooks();
			request.setAttribute("books", books);

			page = PAGE_WELCOME_USER;

			if (user.isRole()) {
				page = PAGE_WELCOME_ADMIN;
			}

		} else {
			request.setAttribute("ERROR", "wrong user login or password");
			page = PAGE_ERROR;
		}
		return page;
	}

}
