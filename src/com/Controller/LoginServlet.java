package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ltil.dao.UserDao;

import com.ltil.dao.UserDaoImpl2;
import com.ltil.model.User;
import com.ltil.utilities.ConnectionManager2;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private Connection connection=null;
	private static final long serialVersionUID = 1L;
    private UserDao dao = new UserDaoImpl2();
    private RequestDispatcher rd = null;
    private HttpSession session=null;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		connection=ConnectionManager2.getConnection();
		response.getWriter().println(connection);
		
		String un= request.getParameter("username");
		String pw = request.getParameter("pswd");
		User user = new User(un,pw);
		
	 
		if(dao.validateUser(user))
		{
		session=request.getSession();
		session.setAttribute("user", user);
		
		rd = request.getRequestDispatcher("Profile.jsp");
		rd.forward(request,response);
		}
		else
		{			
			rd = request.getRequestDispatcher("Error.jsp");
			rd.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}


}
