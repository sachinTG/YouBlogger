package comments;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) {
		doService(request, response);
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) {
		doService(request, response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) {
		
	}

}
