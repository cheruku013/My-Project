package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/sellerBookDelete")

public class SellerBookDelete extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{		
		System.out.println("inside delete constructor do get"+request.getParameter("bookId"));
		
		
		String bookId = request.getParameter("bookId");
		
		
		Connection connection = null;
		java.sql.Statement stmt = null;
		try{
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String deleteQuery = "delete from booktable where bookid_pk =?";
	         PreparedStatement prepStmt = connection.prepareStatement(deleteQuery);
	        
	         prepStmt.setInt(1, Integer.parseInt(bookId));

	         prepStmt.executeUpdate();
			
			
			connection.close();
			
			response.sendRedirect("sellerBookAlter");
			
		}catch (Exception e)
		{
			e.printStackTrace();			
		}

	                                           		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{		
		System.out.println("inside delete constructor do post");
		
		//String buyerId = request.getParameter("bookId");
	                                           		
	}
}
