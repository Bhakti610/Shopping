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


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
private UserDao dao = new UserDaoImpl2();
private HttpSession session = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection connection = ConnectionManager2.getConnection();
		//response.getWriter().println("In URN");
		
		String un= request.getParameter("username");
		String pw = request.getParameter("pswd");
		User user = new User(un,pw);
		
	int retval = dao.insertUser(user);
	
	if (retval==1)
		{
		System.out.println("Retval="+retval);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			//response.sendRedirect("login");
		}
		else
		{
			System.out.println("Retval else="+retval);
			RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
			rd.forward(request, response);
			//response.sendRedirect("error");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
	}

}
