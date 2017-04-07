package by.htp.web.controller;

import static by.htp.web.util.Permanents.REQUEST_PARAM_ACTION;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.web.command.Command;
import by.htp.web.command.CommandChooser;

public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public MainServlet() {
        super();
        System.out.println("servlet constructor");
    }

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("servlet init method");
	}

	public void destroy() {
		System.out.println("servlet destroy method");
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet service method");
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet doGet method");
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet doPost method");
		processRequest(request, response);
	}
	
	public void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException{
		RequestDispatcher disp =request.getRequestDispatcher(page);
		disp.forward(request, response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter(REQUEST_PARAM_ACTION);
		Command command = CommandChooser.chooseCommand(action);
		String page = command.execute(request, response);
		forward(request, response, page);
	}

}
