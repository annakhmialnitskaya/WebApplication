package by.htp.web.command;

import static by.htp.web.util.Permanents.PAGE_INDEX;
import static by.htp.web.util.Permanents.SESSION_PARAM_USER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.setAttribute(SESSION_PARAM_USER, null);
		return PAGE_INDEX;
	}

}
