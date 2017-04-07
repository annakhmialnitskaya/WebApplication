package by.htp.web.command;

import static by.htp.web.util.Permanents.PAGE_ERROR;
import static by.htp.web.util.Permanents.PAGE_WELCOME_ADMIN;
import static by.htp.web.util.Permanents.REQUEST_PARAM_BOOK_AUTHOR;
import static by.htp.web.util.Permanents.REQUEST_PARAM_BOOK_PRICE;
import static by.htp.web.util.Permanents.REQUEST_PARAM_BOOK_TITLE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.web.service.BookService;
import by.htp.web.service.BookServiceException;
import by.htp.web.service.impl.BookServiceImpl;

public class AddBookCommand implements Command {

	private BookService serviceBook;

	{
		serviceBook = new BookServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String page = null;
		String title = request.getParameter(REQUEST_PARAM_BOOK_TITLE);
		String author = request.getParameter(REQUEST_PARAM_BOOK_AUTHOR);
		String price = request.getParameter(REQUEST_PARAM_BOOK_PRICE);

		try {
			serviceBook.addBook(title, author, price);
		} catch (BookServiceException e) {
			request.setAttribute("ERROR", "Unable to create book!");
			page = PAGE_ERROR;
			return page;
		}
		page = PAGE_WELCOME_ADMIN;
		request.setAttribute("MESSAGE", "Book was successfuly created!");
		return page;
	}

}
