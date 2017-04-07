package by.htp.web.dao.impl;

import static by.htp.web.dao.impl.DaoConstants.DB_CONNECTION;
import static by.htp.web.dao.impl.DaoConstants.DB_DRIVER;
import static by.htp.web.dao.impl.DaoConstants.DB_PASSWORD;
import static by.htp.web.dao.impl.DaoConstants.DB_USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.web.dao.BookDao;
import by.htp.web.dao.BookDaoException;
import by.htp.web.domain.Book;

public class BookDaoImpl implements BookDao {

	private static final String QUERY_ALL_BOOKS_SELECT = "SELECT * FROM book";
	private static final String QUERY_BOOK_INSERT = "INSERT INTO book (`title`, `author`, `price`) VALUES (?,?,? )";

	@Override
	public List<Book> read() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Book> books = new ArrayList<Book>();
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			st = con.createStatement();
			rs = st.executeQuery(QUERY_ALL_BOOKS_SELECT);

			while (rs.next()) {
				String title = rs.getString("title");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				Book book = new Book(title, author, price);
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
				}
			}
		}
		return books;
	}

	@Override
	public void add(String title, String author, double price) throws BookDaoException {

		try (Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				PreparedStatement statement = dbConnection.prepareStatement(QUERY_BOOK_INSERT)) {
			statement.setString(1, title);
			statement.setString(2, author);
			statement.setDouble(3, price);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new BookDaoException("Exception with DB!", e);
		}
	}
}
