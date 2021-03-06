package posts;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SavePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static int RANGE = 10000;
	static ArrayList<String> postList = new ArrayList<String>();
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	
	public void doService (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random rand = new Random();
		String fileName = null;
		BufferedWriter writer = null;
		String title = request.getParameter("Post_Title");
		String content = request.getParameter("content");
		String origin = "../webapps/YouBlogger/Posts/";
		
		while (true) {
			fileName = "Post" + rand.nextInt(RANGE);
			
			if (!(postList.contains(fileName))) {
				synchronized (postList) {
					postList.add(fileName);
				}
				break;
			}
		}
		String fullPath = origin + fileName + ".yblg";
		String appCommentPath = "../webapps/YouBlogger/Comments/ApprovedComments/" + fileName + ".ybcm";
		String pndCommentPath = "../webapps/YouBlogger/Comments/PendingComments/" + fileName + "ybcm";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fullPath))));
			writer.write(title + "\n");
			writer.write(content);
			writer.close();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(appCommentPath))));
			writer.write("");
			writer.close();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(pndCommentPath))));
			writer.close();
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
		    request.setAttribute("title", title);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/posted.jsp");
		    dispatcher.forward(request, response);
		}
	}
}