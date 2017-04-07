package by.htp.web.service;

import java.util.List;

import by.htp.web.domain.Book;

public interface BookService {

	public List<Book> listBooks();

	public void addBook(String title, String author, String price) throws BookServiceException;

}
