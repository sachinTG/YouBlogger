package login;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AuthenticationServer extends HttpServlet {

	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
    }
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService (request, response);
	}
	
	public void doService (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String user = request.getRemoteUser();
        request.setAttribute("user", user);
        response.sendRedirect(request.getHeader("referer"));
	}
}