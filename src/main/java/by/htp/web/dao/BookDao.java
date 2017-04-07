package by.htp.web.dao;

import java.util.List;

import by.htp.web.domain.Book;

public interface BookDao {

	public List<Book> read();

	public void add(String title, String author, double price) throws BookDaoException;

}
