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

import by.htp.web.dao.UserDao;
import by.htp.web.domain.User;

public class UserDaoImpl implements UserDao {

	private static final String QUERY_USER_LOGIN_PASSWORD_SELECT = "select * from user where user.login =? and user.password =?";

	@Override
	public User read(String login, String password) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			st = con.prepareStatement(QUERY_USER_LOGIN_PASSWORD_SELECT);
			st.setString(1, login);
			st.setString(2, password);
			rs = st.executeQuery();

			while (rs.next()) {
				String lg = rs.getString("login");
				String pw = rs.getString("password");
				boolean role = rs.getBoolean("role");
				user = new User(lg, pw, role);
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
		return user;
	}

}
