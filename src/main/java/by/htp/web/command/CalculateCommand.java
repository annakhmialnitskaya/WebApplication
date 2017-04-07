package by.htp.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateCommand implements Command {
	public static final String INPUT_TERM1_ID = "incorrect term!";
	private static final String INPUT_TERM2_ID = "term2";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String val1 = request.getParameter("val1");
		String val2 = request.getParameter("val2");

		if (validate(val1, val2)) {
			int result = 0;
			try {
				result = calculate(val1, val2);
			} catch (NumberFormatException e) {
				request.setAttribute("ERROR", "incorrect term!");
				return "/error.jsp";
			}
			request.setAttribute("result", result);
			return "/calculateResult.jsp";
		}
		request.setAttribute("ERROR", "term is empty!");
		return "/error.jsp";
	}

	private int calculate(String val1, String val2) throws NumberFormatException {
		int a = Integer.parseInt(val1);
		int b = Integer.parseInt(val2);
		int c = a + b;
		return c;
	}

	private boolean validate(String val1, String val2) {
		if (val1 != null && !val1.isEmpty() && val2 != null && !val2.isEmpty()) {
			return true;
		}
		return false;
	}

}
