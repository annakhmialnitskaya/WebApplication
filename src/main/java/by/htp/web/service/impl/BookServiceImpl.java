package by.htp.web.service.impl;

import java.util.List;

import by.htp.web.dao.BookDao;
import by.htp.web.dao.BookDaoException;
import by.htp.web.dao.impl.BookDaoImpl;
import by.htp.web.domain.Book;
import by.htp.web.service.BookService;
import by.htp.web.service.BookServiceException;

public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	{
		bookDao = new BookDaoImpl();
	}

	@Override
	public List<Book> listBooks() {
		List<Book> books = bookDao.read();
		return books;
	}

	@Override
	public void addBook(String title, String author, String priceString) throws BookServiceException {
		double price = Double.parseDouble(priceString);
		try {
			bookDao.add(title, author, price);
		} catch (BookDaoException e) {
			throw new BookServiceException();
		}

	}
}
