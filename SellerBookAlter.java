package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/sellerBookAlter")

public class SellerBookAlter extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		
		
		if(sessionName!=null && sessionId!= null)
		{
		
		
		ArrayList<ArrayList<String>> bookRecords = new ArrayList<ArrayList<String>>();
		
		Connection connection = null;
		java.sql.Statement stmt = null;
		
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
		System.out.println("Opened database successfully");

		stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery("select * from booktable where userid_fk ='"+sessionId+"';");

		for(int x=0; rs.next();x++ ) 
		{
			ArrayList<String> bookDetails = new ArrayList<String>();
			bookDetails.add(0,rs.getString(1));
			bookDetails.add(1,rs.getString(3));
			bookDetails.add(2,rs.getString(4));
			bookDetails.add(3,rs.getString(5));
			bookDetails.add(4,rs.getString(6));
			bookDetails.add(5,rs.getString(8));
			bookRecords.add(bookDetails);
		}
		
		connection.close();
		request.setAttribute("bookRecords", bookRecords);
		request.setAttribute("loggedInName",sessionName);
		request.getRequestDispatcher("/WEB-INF/views/bookAlter.jsp").forward(request, response);
		}catch (Exception e)
		{
			e.printStackTrace();

		}
		
		}
	}
	
}
